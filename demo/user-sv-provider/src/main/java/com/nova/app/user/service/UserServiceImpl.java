package com.nova.app.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
		return userDao.get(name);
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
	}

	public void update(User user) {
		userDao.update(user);
	}

	public List<User> getAllUsers() {
		List<User> users= userDao.getAllUsers();
		return users;
	}
	
	public Set getUserRolesSet(String name) {
		return userDao.getUserRolesSet(name);
	}

	public Set getRolePermissionsSet(String roleName) {
		return userDao.getRolePermissionsSet(roleName);
	}

	public User getUserAnthenticaition(String name, String password) {
		Map map = new HashMap();
		map.put("username",name);
		map.put("password",password);
		return userDao.getUserAnthenticaition(map);
	}

	public Set<String> getPermissions(String userName){
		return userDao.getPermissions(userName);
	}

}
