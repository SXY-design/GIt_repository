package com.itheima.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.domain.User;

public interface UserDao {

	void save(User user);

	User login(User user);

	List<User> findAll(DetachedCriteria detachedCriteria);

}
