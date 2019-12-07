package com.itheima.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.domain.LinkMan;

public interface LinkManDao {

	Integer findTotalCount(DetachedCriteria detachedCriteria);

	List<LinkMan> findAllByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);

	void save(LinkMan linkMan);

	LinkMan findById(DetachedCriteria detachedCriteria);

	void update(LinkMan linkMan);

	LinkMan findByLkm_id(Long lkm_id);

	void delete(LinkMan linkMan);

}
