package com.itheima.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.dao.LinkManDao;
import com.itheima.domain.LinkMan;

public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao {

	@Override
	public Integer findTotalCount(DetachedCriteria detachedCriteria) {
		DetachedCriteria criteria = detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list.size() > 0){
			Integer totalCount = list.get(0).intValue();
			return totalCount;
		}
		return null;
	}

	@Override
	public List<LinkMan> findAllByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		detachedCriteria.setProjection(null);
		List<LinkMan> list = (List<LinkMan>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
		return list;
	}

	@Override
	public void save(LinkMan linkMan) {
		this.getHibernateTemplate().save(linkMan);
		
	}

	@Override
	public LinkMan findById(DetachedCriteria detachedCriteria) {
		List<LinkMan> list = (List<LinkMan>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		return list.get(0);
	}

	@Override
	public void update(LinkMan linkMan) {
		this.getHibernateTemplate().update(linkMan);
	}

	@Override
	public LinkMan findByLkm_id(Long lkm_id) {
		return this.getHibernateTemplate().get(LinkMan.class, lkm_id);
	}

	@Override
	public void delete(LinkMan linkMan) {
		this.getHibernateTemplate().delete(linkMan);
		
	}
	
}
