package com.nova.app.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtil implements ApplicationContextAware {  
	  private static ApplicationContext applicationContext;     //Spring应用上下文环境  
	   
	  /** 
	  * 实现ApplicationContextAware接口的回调方法，设置上下文环境    
	  * @param applicationContext 
	  * @throws BeansException 
	  */  
	  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {  
	    SpringContextUtil.applicationContext = applicationContext;  
	  }  
	   
	  /** 
	  * @return ApplicationContext 
	  */  
	  public static ApplicationContext getApplicationContext() {  
	    return applicationContext;  
	  }  
	   
	  /** 
	  * 获取对象    
	  * @param name 
	  * @return Object 一个以所给名字注册的bean的实例 
	  * @throws BeansException 
	  */  
	  public static Object getBean(String name) throws BeansException {  
	    return applicationContext.getBean(name);  
	  }    
}
