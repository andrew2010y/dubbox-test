package com.nova.app.user.service;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public User get(String name) {
		User user= userDao.get(name);
		return user;
	}

	@Override
	public void remove(String name) {
		userDao.delete(name);
	}

	@Override
	public void add(User user) {
		userDao.insert(user);
		System.out.println("userId=" + user.getUserId());
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users= userDao.getAllUsers();
		return users;
	}

}
