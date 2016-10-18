package com.nova.app.user.dao;

import java.util.List;

import com.nova.app.user.domain.User;

/**
 * 功能概要：User的DAO类
 * 
 * @author juwenguang
 */
public interface UserDao {

	User selectUserById(Integer userId);
	
	User get(String name);
	
	void insert(User user);
	
	void update(User user);
	
	void delete(String name);
	
	List<User> getAllUsers();

}
