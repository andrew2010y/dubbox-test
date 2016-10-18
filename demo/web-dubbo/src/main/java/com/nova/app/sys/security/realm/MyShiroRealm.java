package com.nova.app.sys.security.realm;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.nova.app.user.domain.User;
import com.nova.app.user.service.UserService;


public class MyShiroRealm extends AuthorizingRealm {

	private Logger log = Logger.getLogger(this.getClass());
	private  static final String MESSAGE = "message";
	
	@Autowired
	private UserService userService;
	@Resource
	private StringRedisTemplate redisTemplate;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String)principals.getPrimaryPrincipal();
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		if("ppt".equals(username)) {
			info.addStringPermission("access");
			return info;
		}
		if("jpa".equals(username)) {
			info.addStringPermission("access");
			info.addRole("admin");
			return info;
		}
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;  
        String username = token.getUsername();
        String passwrod = null;
        if(token.getPassword() != null) {
        	passwrod = new String(token.getPassword());
        }
        if(username == null || "".equals(username)) {
        	this.setAttribute(MESSAGE, "用户名不能为空");
        	log.info("用户名为空");
        	return null;
        }
        if(passwrod == null || "".equals(passwrod)) {
        	this.setAttribute(MESSAGE, "密码不能为空");
        	log.info("密码为空");
        	return null;
        }
        User user = null;
        if(token.getUsername() != null && !"".equals(token.getUsername())) {
        	user = userService.get(token.getUsername());
        }
        try {
         //   redisTemplate.delete(RedisConstant.ROLE_AUTHORIZATION_LIST_KEY + user.getUserId());
   		//	redisTemplate.delete(RedisConstant.RESOURCE_AUTHORIZATION_LIST_KEY + user.getUserId());
   	    
        	return new SimpleAuthenticationInfo(user.getUserName(),user.getUserPassword(),getName()); 
        } catch(Exception e) {
        	log.info("用户名或密码错误");
        	setAttribute(MESSAGE, "用户名或密码错误");
        	return null;
        }
        
	}
	private void setAttribute(String key, String value) {
		SecurityUtils.getSubject().getSession().setAttribute(key, value);
	}
}
