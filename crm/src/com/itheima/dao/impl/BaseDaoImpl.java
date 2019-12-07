package com.itheima.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.dao.BaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	private Class clazz;
	//加入无参构造
	public BaseDaoImpl() {
		Class clazz =  this.getClass();    //获取的是调用此构造方法的类。比如继承该baseDaoImpl的子类，当子类new自己的时候，就会new父类的构造方法，从而得到子类的类class。
		Type type = clazz.getGenericSuperclass(); //获取子类继承的类的名称+泛型，即BaseDaoImpl<Customer>
		ParameterizedType pType = (ParameterizedType) type; //将type强制转换成参数化类型
		Type[] types = pType.getActualTypeArguments(); //获取实际类型参数
		this.clazz = (Class) types[0]; //得到Customer。
	}
	
	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public void update(T t) {
	this.getHibernateTemplate().update(t);
	}

	@Override
	public List<T> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		detachedCriteria.setProjection(null);
		List<T> list = (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
		return list;
	}

	@Override
	public T findById(Long cust_id) {
		return (T) this.getHibernateTemplate().get(clazz, cust_id);
	}

	
}
