package com.itheima.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.itheima.domain.Customer;
import com.itheima.domain.PageBean;
import com.itheima.service.CustomerService;
import com.itheima.web.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	private Customer customer = new Customer();
	@Override
	public Customer getModel() {
		return customer;
	}
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	private Integer currentPage = 1;
	public void setCurrentPage(Integer currentPage) {
		if(currentPage == null){
			currentPage = 1;
		}
		this.currentPage = currentPage;
	}
	
	private Integer pageSize = 3;
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	//文件上传需要定义上传项名称，类型，并提供set方法
	private String uploadFileName;    //上传文件名
	private File upload;			  //上传的文件,名字需要跟jsp页面<input type="file" name="upload">一样
	private String uploadContentType; //上传文件的类型
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
	
	public String saveUI(){
		return "saveUI";
	}
	public String save() throws IOException{
		if(upload != null){
			//保存文件
			String path = "C:/upload";
			//将可重复文件名转化为唯一文件名
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			//实现目录分级
			String extendPath = UploadUtils.getUuidPath(uploadFileName);
			String url = path + extendPath;
			File file = new File(url);
			if(! file.exists()){
				file.mkdirs();
			}
			File destFile = new File(url+"/"+uuidFileName);
			FileUtils.copyFile(upload, destFile);
			customer.setCust_image(url+"/"+uuidFileName);
		}
		customerService.save(customer);
		return "saveSuccess";
	}
	public String findAll(){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		if(customer.getCust_name() != null){
			detachedCriteria.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
		}
		if(customer.getBaseDictLevel() != null){
			if(customer.getBaseDictLevel().getDict_id() != null && !"".equals(customer.getBaseDictLevel().getDict_id())){
				detachedCriteria.add(Restrictions.eq("baseDictLevel.dict_id", customer.getBaseDictLevel().getDict_id()));
			}
		}
		if(customer.getBaseDictSource() != null){
			if(customer.getBaseDictSource().getDict_id() != null && !"".equals(customer.getBaseDictSource().getDict_id())){
				detachedCriteria.add(Restrictions.eq("baseDictSource.dict_id", customer.getBaseDictSource().getDict_id()));
			}
		}
		if(customer.getBaseDictIndustry() != null){
			if(customer.getBaseDictIndustry().getDict_id() != null && !"".equals(customer.getBaseDictIndustry().getDict_id())){
				detachedCriteria.add(Restrictions.eq("baseDictIndustry.dict_id", customer.getBaseDictIndustry().getDict_id()));
			}
		}
		PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria,currentPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	public String delete(){
		//先查询后从删除，才能在映射中配置级联删除
		customer = customerService.findById(customer.getCust_id());
		if(customer.getCust_image() != null){
			File file = new File(customer.getCust_image());
			if(file.exists()){
				file.delete();
			}
		}
		customerService.delete(customer);
		return "deleteSuccess";
	}
	public String edit(){
		customer = customerService.findById(customer.getCust_id());
		ActionContext.getContext().getValueStack().push(customer);
		return "editSuccess";
		
	}
	public String update() throws IOException{
		if(upload != null){
			//把原来的图片删除
			File oldImage = new File(customer.getCust_image());
			oldImage.delete();
			//保存新文件
			String path = "C:/upload";
			//将可重复文件名转化为唯一文件名
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			//实现目录分级
			String extendPath = UploadUtils.getUuidPath(uploadFileName);
			String url = path + extendPath;
			File file = new File(url);
			if(! file.exists()){
				file.mkdirs();
			}
			File destFile = new File(url+"/"+uuidFileName);
			FileUtils.copyFile(upload, destFile);
			customer.setCust_image(url+"/"+uuidFileName);
		}
		customerService.update(customer);
		return "updateSuccess";
	}
	public String findAllCustomers() throws IOException{
		System.out.println("findAllCustomers执行了");
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		detachedCriteria.setProjection(null);
		List<Customer> list = customerService.findAll(detachedCriteria);
		JsonConfig jsonConfig = new JsonConfig();
		//linkMans这个属性一定得去掉，因为客户里面有联系人，联系人里面也有客户，如果不去掉会出现死循环
		jsonConfig.setExcludes(new String[] {"linkMans","baseDictSource","baseDictLevel","baseDictIndustry"});
		JSONArray array = JSONArray.fromObject(list, jsonConfig);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(array.toString());//把jsonArray的内容带到页面中，必须写的
		return NONE;
	}
}
