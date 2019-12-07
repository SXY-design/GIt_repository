package com.itheima.web.action;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.itheima.domain.Customer;
import com.itheima.domain.LinkMan;
import com.itheima.domain.PageBean;
import com.itheima.service.CustomerService;
import com.itheima.service.LinkManService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
	private LinkMan linkMan = new LinkMan();
	@Override
	public LinkMan getModel() {
		// TODO Auto-generated method stub
		return linkMan;
	}
	private LinkManService linkManService;
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	private Integer currentPage = 1;
	private Integer pageSize = 3;
	
	public void setCurrentPage(Integer currentPage) {
		if(currentPage == null){
			currentPage = 1;
		}
		this.currentPage = currentPage;
	}

	public void setPageSize(Integer pageSize) {
		if(pageSize==null){
			pageSize = 3;
		}
		this.pageSize = pageSize;
	}

	public String findAll(){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
		//设置条件
		if(linkMan.getLkm_name() != null && !"".equals(linkMan.getLkm_name())){
			detachedCriteria.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		}
		if(linkMan.getLkm_gender() != null && !"".equals(linkMan.getLkm_gender())){
			detachedCriteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
		}
		PageBean<LinkMan> pageBean = linkManService.findAll(detachedCriteria,currentPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAllSuccess";
	}
	public String addUI(){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		List<Customer> list = customerService.findAll(detachedCriteria);
		ActionContext.getContext().getValueStack().set("list",list);
		return "addUI";
	}
	
	public String save(){
		linkManService.save(linkMan);
		return "save";
	}
	public String edit(){
		//根据传过来的联系人的id查询联系人的信息
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
		detachedCriteria.add(Restrictions.eq("lkm_id", linkMan.getLkm_id()));
		LinkMan linkMan = linkManService.findById(detachedCriteria);
		//查询所有客户信息
		DetachedCriteria detachedCriteria1 = DetachedCriteria.forClass(Customer.class);
		List<Customer> list = customerService.findAll(detachedCriteria1);
		ActionContext.getContext().getValueStack().push(linkMan);
		ActionContext.getContext().getValueStack().set("list", list);
		return "edit";
	}
	public String update(){
		linkManService.update(linkMan);
		return "update";
	}
	public String delete(){
		//先查询再删除
		LinkMan linkMan1 = linkManService.findByLkm_id(linkMan.getLkm_id());
		linkManService.delete(linkMan1);
		return "delete";
	}
	
}
