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
		User user = service.selectUserById(2);
		
		assertTrue(user.getUserName().equals("evan"));
	}
}
