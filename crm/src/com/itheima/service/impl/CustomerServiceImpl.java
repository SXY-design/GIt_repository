package com.itheima.service.impl;

import java.util.List;
import java.util.function.DoublePredicate;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;
import com.itheima.domain.PageBean;
import com.itheima.service.CustomerService;
@Transactional
public class CustomerServiceImpl implements CustomerService {
	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
	}
	@Override
	public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currentPage,Integer pageSize) {
		PageBean<Customer> pageBean = new PageBean<>();
		pageBean.setCurrentPage(currentPage);
		pageBean.setPageSize(pageSize);
		//查询总记录数
		Integer totalCount = customerDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		//计算总页数
		Double count = totalCount.doubleValue();
		Double totalPage = Math.ceil(count/pageSize);
		pageBean.setTotalPage(totalPage.intValue());
		//查询当前页返回的list
		Integer begin = (currentPage - 1) * pageSize;
		List<Customer> list = customerDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}
	@Override
	public Customer findById(Long cust_id) {
		return customerDao.findById(cust_id);
	}
	@Override
	public void delete(Customer customer) {
		customerDao.delete(customer);
		
	}
	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
		
	}
	@Override
	public List<Customer> findAll(DetachedCriteria detachedCriteria) {
		return customerDao.findAll(detachedCriteria);
	}
	
}
