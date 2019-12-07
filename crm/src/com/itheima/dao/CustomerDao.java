package com.itheima.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.domain.Customer;

public interface CustomerDao extends BaseDao<Customer>{

	Integer findCount(DetachedCriteria detachedCriteria);

	List<Customer> findAll(DetachedCriteria detachedCriteria);

}
