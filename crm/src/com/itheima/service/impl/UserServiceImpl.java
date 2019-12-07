package com.itheima.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.web.utils.MD5Utils;
@Transactional
public class UserServiceImpl implements UserService {
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void regist(User user) {
		String password = MD5Utils.md5(user.getUser_password());
		user.setUser_password(password);
		user.setUser_state("1");
		userDao.save(user);
	}

	@Override
	public User login(User user) {
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		return userDao.login(user);
	}

	@Override
	public List<User> findAll(DetachedCriteria detachedCriteria) {
		return userDao.findAll(detachedCriteria);
	}

}
