package com.itheima.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao{


	@Override
	public Integer findCount(DetachedCriteria detachedCriteria) { 
		DetachedCriteria criteria = detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list.size() > 0){
			return list.get(0).intValue();
		}
		return null;
	}

	/*@Override
	public List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		detachedCriteria.setProjection(null);  //因为detachedCriteria只能有一个，上面设置了条件，所以必须先setNULL
		return (List<Customer>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
	}*/

	/*@Override
	public Customer findById(Long cust_id) {
		return this.getHibernateTemplate().get(Customer.class, cust_id);
	}*/



	@Override
	public List<Customer> findAll(DetachedCriteria detachedCriteria) {
		return (List<Customer>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

}
