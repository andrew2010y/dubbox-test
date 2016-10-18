package com.nova.app.provider;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserProvider {

	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] {"application.xml", "user-provider.xml"});
		context.start();

//		com.alibaba.dubbo.container.Main.main(args);
		
		System.in.read();
	}

}
