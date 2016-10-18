package com.nova.app.user.service;

import java.util.List;

import com.nova.app.user.domain.User;


/**
 * 功能概要：UserService接口类
 * 
 * @author juwenguang
 * @since  2015年9月28日 
 */
public interface UserService {
	User selectUserById(Integer userId);
	User get(String name);
	void remove(String name);
	void add(User user);
	void update(User user);
	List<User> getAllUsers();
}
