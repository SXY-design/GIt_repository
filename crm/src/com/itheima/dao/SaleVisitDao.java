package com.itheima.dao;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.domain.SaleVisit;

public interface SaleVisitDao extends BaseDao<SaleVisit> {

	Integer findCount(DetachedCriteria detachedCriteria);

}
