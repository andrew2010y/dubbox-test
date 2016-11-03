package com.nova.app.user.service;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nova.app.user.domain.User;
import com.nova.app.user.service.UserServiceImpl;
import com.nova.app.util.SpringContextUtil;

import junit.framework.TestCase;

public class UserServiceImplTest extends TestCase {

	@Test
	public void testDb(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] {"application.xml"});
		context.start();
		
		UserServiceImpl service = (UserServiceImpl)context.getBean("userServiceImpl");
//		UserServiceImpl service = (UserServiceImpl)SpringContextUtil.getBean("userServiceImpl");
		User user = service.get("evan");
		
		assertTrue(user.getUserPassword().equals("123"));
		
		User user1 = service.get("www");
		System.out.println("User 'www' infor: \n   email:" + user1.getUserEmail() + "  user id:" + user1.getUserId()
			+ "  user name:" + user1.getUserName());
		
		User user2 = service.get("wa1");
		System.out.println("second:\nUser 'wa1' infor: \n   email:" + user2.getUserEmail() + "  user id:" + user2.getUserId()
			+ "  user name:" + user2.getUserName());
	}
}
