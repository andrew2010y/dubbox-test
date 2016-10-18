package com.nova.app.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.session.web.http.SessionRepositoryFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nova.app.user.service.UserService;

/**
 * 功能概要：UserController
 * 
 * @author juwenguang
 */
@Controller
@RequestMapping("")
public class LoginController {
	@Resource
	private UserService userService;
	@Resource
	private StringRedisTemplate redisTemplate;
	
    @RequestMapping(value="/login", method={RequestMethod.GET})      
    public String addUser(HttpServletRequest request, Model model){  
    	 Subject subject = SecurityUtils.getSubject();
         if (subject.getPrincipal() != null) {
        	    return "redirect:/user/list"; 
         }
         
        return "login";      
    }   
    
    /**
     * 只有登陆认证失败才会访问到该方法
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String fail(Model model) {
    	/*@RequestParam( MyAuthenticationFilter.DEFAULT_LOGINNAME_PARAM) String userName,
    	model.addAttribute(MyAuthenticationFilter.DEFAULT_LOGINNAME_PARAM, userName);
        model.addAttribute(MyAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, true);
       */
        return "login";
    }
    String key_prex = "spring:session:sessions:";
    
    @RequestMapping(value="/out", method={RequestMethod.GET,RequestMethod.POST})      
    public String out(HttpServletRequest request,Model model){  
    	
    	Subject subject = SecurityUtils.getSubject();  
    	 if (subject.isAuthenticated()) {  
    		 
    		    Cookie [] cs=request.getCookies();
    		     
    		    if(null!=cs){
    		       for(int i=0;i<cs.length;i++){
    		       
    		          Cookie c=cs[i];
    		          String name=c.getName();
    		          if (name.equals("SESSION")) {
    		        	  String value = c.getValue();
    		        	  redisTemplate.delete(key_prex+value);
    		          }
    		       }
    		    }
    		   
 	        subject.logout(); 
 	    }     
    	
        return "login";      
    }   
    
}
