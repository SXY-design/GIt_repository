package com.itheima.web.action;

import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.itheima.domain.PageBean;
import com.itheima.domain.SaleVisit;
import com.itheima.service.SaleVisitService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit>{
	private SaleVisit saleVisit = new SaleVisit();
	@Override
	public SaleVisit getModel() {
		return saleVisit;
	}
	@Resource(name="saleVisitService")
	private SaleVisitService saleVisitService;
	
	private Integer currentPage = 1;
	private Integer pageSize = 3;
	
	public void setCurrentPage(Integer currentPage) {
		if(currentPage == null){
			this.currentPage = 1;
		}
		this.currentPage = currentPage;
	}

	public void setPageSize(Integer pageSize) {
		if(pageSize == null){
			this.pageSize = 3;
		}
		this.pageSize = pageSize;
	}

	//定义访问时间范围的visit_end _time,生成set方法，使action中能够接收；生成get方法，使visit_end_time能放入到值栈中
	private Date visit_end_time;
	public void setVisit_end_time(Date visit_end_time) {
		this.visit_end_time = visit_end_time;
	}

	public String findAll(){
		System.out.println(saleVisit.getVisit_time()+".."+visit_end_time);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
		if(saleVisit.getVisit_time() != null){
			detachedCriteria.add(Restrictions.ge("visit_time", saleVisit.getVisit_time()));
		}
		if(visit_end_time != null){
			detachedCriteria.add(Restrictions.le("visit_time", "visit_end_time"));
		}
		PageBean<SaleVisit> pageBean = saleVisitService.findByPage(detachedCriteria,currentPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}
	
	public String saveUI(){
		return "saveUI";
	}
	public String save(){
		saleVisitService.save(saleVisit);
		return "save";
	}
}
