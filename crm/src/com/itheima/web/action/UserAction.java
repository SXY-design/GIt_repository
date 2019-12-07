package com.itheima.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	private User user = new User();
	@Override
	public User getModel() {
		return user;
	}
	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String regist(){
		userService.regist(user);
		return "login";
	}
	
	public String login(){
		User existUser = userService.login(user);
		if(existUser == null){
			this.addActionError("用户名或密码错误！！！");
			return LOGIN;
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return SUCCESS;
		}
	}
	public String findAllUsers() throws IOException{
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
		List<User> list = userService.findAll(detachedCriteria);
		JSONArray jsonArray = JSONArray.fromObject(list);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}
}
