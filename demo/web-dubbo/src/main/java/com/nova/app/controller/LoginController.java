package com.nova.app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nova.app.sys.cache.Cache;
import com.nova.app.sys.security.realm.EndecryptUtils;
import com.nova.app.user.domain.User;
import com.nova.app.user.service.UserService;
import com.nova.app.vo.TestRedis;

/**
 * 功能概要：LoginController
 * 
 */
@Controller
public class LoginController {
	private static Logger log = Logger.getLogger(LoginController.class);
	
	@Resource
	private UserService userService;
	@Resource
	private Cache cache;
	  
	private void readFile(int flag,HttpSession session) {
		String classPath = this.getClass().getClassLoader().getResource("").getPath(); 
		String configFilePath = classPath + File.separator + "session-test/test2.log.upload"; 
		 
	       InputStream in = null;
	       TestRedis testRedis = new TestRedis();
		   try {
			   in = new FileInputStream(configFilePath);
	            // 一次读多个字节
	            byte[] tempbytes = new byte[100];
	            int byteread = 0;
	            if(flag==1){
	            	in.read(tempbytes);
	            	testRedis.setTempbytes(tempbytes);
	            	session.setAttribute("test1", testRedis);
	            }else if(flag==2){
	            	tempbytes = new byte[5024];
	            	in.read(tempbytes);
	            	testRedis.setTempbytes(tempbytes);
	            	session.setAttribute("test2", testRedis);
	            }else if(flag==3){
		            // 读入多个字节到字节数组中，byteread为一次读入的字节数
		           /* while ((byteread = in.read(tempbytes)) != -1) {
		                System.out.write(tempbytes, 0, byteread);
		            }*/
	            	tempbytes = new byte[150024];
	            	in.read(tempbytes);
	            	testRedis.setTempbytes(tempbytes);
	            	
	            	session.setAttribute("test3", testRedis);
	            }
	        } catch (Exception e1) {
	            e1.printStackTrace();
	        } finally {
	            if (in != null) {
	                try {
	                    in.close();
	                } catch (IOException e1) {
	                }
	            }
	        }
	}
	
	
    @RequestMapping(value="/login", method={RequestMethod.POST})      
    public String login(User curUser,ModelMap model){  
    	 double start = System.currentTimeMillis();
    	 Subject user = SecurityUtils.getSubject();

    	 /**
    	  * 用户密码加密
    	  */
//         UsernamePasswordToken token = new UsernamePasswordToken(curUser.getUserName(),EndecryptUtils.md5Password(curUser.getUserPassword()));
    	 UsernamePasswordToken token = new UsernamePasswordToken(curUser.getUserName(),curUser.getUserPassword());
    	 token.setRememberMe(true);

         try {
             user.login(token);
         }catch (UnknownAccountException e){
             token.clear();
             model.put("message", "用户不存在！");
         }catch (IncorrectCredentialsException e){
             token.clear();
             model.put("message", "用户密码错误！");
         }catch (AuthenticationException e) {
        	 e.printStackTrace();
             token.clear();
             model.put("message", "出错了！");
             return "login";
         }

         model.put("username", curUser.getUserName());
         
         //判断用户是否成功登录
         if(user.isAuthenticated()){
             log.warn("Running time of the method login:"+(System.currentTimeMillis()-start)+"ms");
             return "user/list";
         }else{
        	 double end = System.currentTimeMillis();
             log.warn("Running time of the method login:"+(System.currentTimeMillis()-start)+"ms");
             return "login";
         }
    }   
    
    /**
     * 只有登陆认证失败才会访问到该方法
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String fail(Model model) {
        return "login";
    }
    
    @RequestMapping(value="/out", method={RequestMethod.GET,RequestMethod.POST})      
    public String out(HttpServletRequest request,Model model){  
    	
    	Subject subject = SecurityUtils.getSubject();  
    	 if (subject.isAuthenticated()) {  
 	        subject.logout(); 
 	    }     
    	
        return "login";      
    }   
    
}
