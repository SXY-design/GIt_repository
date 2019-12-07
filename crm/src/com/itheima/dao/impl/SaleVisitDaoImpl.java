package com.itheima.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import com.itheima.dao.SaleVisitDao;
import com.itheima.domain.SaleVisit;

public class SaleVisitDaoImpl extends BaseDaoImpl<SaleVisit> implements SaleVisitDao{

	@Override
	public Integer findCount(DetachedCriteria detachedCriteria) {
		DetachedCriteria criteria = detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(criteria);
		return list.get(0).intValue();
	}

}

