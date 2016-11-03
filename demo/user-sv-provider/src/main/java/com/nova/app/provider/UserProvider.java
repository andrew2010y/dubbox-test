package com.nova.app.provider;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nova.app.user.domain.User;
import com.nova.app.user.service.UserService;
import com.nova.app.util.SpringContextUtil;

public class UserProvider {
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] {"application.xml", "user-provider.xml"});
		context.start();

	    while(true){
	    	try {
				Thread.sleep(1000000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    }
		
//		com.alibaba.dubbo.container.Main.main(args);
	}

}
