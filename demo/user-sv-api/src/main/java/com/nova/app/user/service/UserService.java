package com.nova.app.user.service;

import java.util.List;
import java.util.Set;

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
	/**
	 * 根据用户登录名获取该用户的所有的角色
	 * @param name
	 * @return
	 */
	Set getUserRolesSet(String name);
	/**
	 * 根据角色名获取该角色的所有的权限
	 * @param roleName
	 * @return
	 */
	Set getRolePermissionsSet(String roleName);
	/**
	 * 根据用户名和密码获取用户信息
	 * @param name
	 * @param password
	 */
	User getUserAnthenticaition(String name, String password);
	
	Set<String> getPermissions(String userName);
}
