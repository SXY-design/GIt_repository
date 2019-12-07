package com.itheima.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.domain.LinkMan;
import com.itheima.domain.PageBean;

public interface LinkManService {

	PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize);

	void save(LinkMan linkMan);

	LinkMan findById(DetachedCriteria detachedCriteria);

	void update(LinkMan linkMan);

	LinkMan findByLkm_id(Long lkm_id);

	void delete(LinkMan linkMan);

	
	
}
