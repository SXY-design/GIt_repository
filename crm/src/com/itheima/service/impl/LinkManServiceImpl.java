package com.itheima.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.LinkManDao;
import com.itheima.domain.LinkMan;
import com.itheima.domain.PageBean;
import com.itheima.service.LinkManService;
@Transactional
public class LinkManServiceImpl implements LinkManService {
	private LinkManDao linkManDao;
	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}
	@Override
	public PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize) {
		PageBean<LinkMan> pageBean = new PageBean<>();
		pageBean.setCurrentPage(currentPage);
		pageBean.setPageSize(pageSize);
		//设置总记录数
		Integer totalCount = linkManDao.findTotalCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		Double tc = totalCount.doubleValue();
		Double totalPage = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(totalPage.intValue());
		//设置返回的数据集合
		Integer begin = (currentPage - 1)*pageSize;
		List<LinkMan> list = linkManDao.findAllByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}
	@Override
	public void save(LinkMan linkMan) {
		linkManDao.save(linkMan);
		
	}
	@Override
	public LinkMan findById(DetachedCriteria detachedCriteria) {
		return linkManDao.findById(detachedCriteria);
	}
	@Override
	public void update(LinkMan linkMan) {
		linkManDao.update(linkMan);
	}
	@Override
	public LinkMan findByLkm_id(Long lkm_id) {
		return linkManDao.findByLkm_id(lkm_id);
	}
	@Override
	public void delete(LinkMan linkMan) {
		linkManDao.delete(linkMan);
	}
	
}
