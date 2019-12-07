package com.itheima.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.SaleVisitDao;
import com.itheima.domain.PageBean;
import com.itheima.domain.SaleVisit;
import com.itheima.service.SaleVisitService;
@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {
	@Resource(name="saleVisitDao")
	private SaleVisitDao saleVisitDao;

	@Override
	public PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize) {
		PageBean<SaleVisit> pageBean = new PageBean<>();
		pageBean.setCurrentPage(currentPage);
		pageBean.setPageSize(pageSize);
		//查询总记录数
		Integer totalCount = saleVisitDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		//计算总页数
		Double tc = totalCount.doubleValue();
		Double totalPage = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(totalPage.intValue());
		//计算从那一条数据开始查
		Integer begin = (currentPage - 1)*pageSize;
		List<SaleVisit> list = saleVisitDao.findByPage(detachedCriteria, begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public void save(SaleVisit saleVisit) {
		saleVisitDao.save(saleVisit);
		
	}
}
