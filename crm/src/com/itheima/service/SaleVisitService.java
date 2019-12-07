package com.itheima.service;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.domain.PageBean;
import com.itheima.domain.SaleVisit;

public interface SaleVisitService {

	PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize);

	void save(SaleVisit saleVisit);

}
