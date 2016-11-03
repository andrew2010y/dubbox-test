package com.nova.app.user.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nova.app.user.dao.UserDao;
import com.nova.app.user.domain.User;


/**
 * 功能概要：UserService实现类
 * 
 * @author juwenguang
 * @since  2015年9月28日 
 */
@Service
public class UserServiceImpl implements UserService{
	@Resource
	private UserDao userDao;

	public User selectUserById(Integer userId) {
		return userDao.selectUserById(userId);
	}

	public User get(String name) {
		User user= userDao.get(name);
		return user;
	}

	public void remove(String name) {
		userDao.delete(name);
	}

	public static String getUUID() {
	    return UUID.randomUUID().toString();  
	}
	 
	public void add(User user) {
		
		user.setUserId(getUUID());
		
		userDao.insert(user);
		
		System.out.println("userId=" + user.getUserId());
	}

	public void update(User user) {
		userDao.update(user);
	}

	public List<User> getAllUsers() {
		List<User> users= userDao.getAllUsers();
		return users;
	}

}
