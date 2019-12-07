package com.itheima.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface BaseDao<T>{
	void save(T t);
	void delete(T t);
	void update(T t);
	List<T> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);
	T findById(Long cust_id);
}
