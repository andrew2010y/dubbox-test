package com.nova.app.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nova.app.sys.security.realm.EndecryptUtils;
import com.nova.app.sys.dfs.DfsClient;
import com.nova.app.user.domain.User;
import com.nova.app.user.service.UserService;

/**
 * 功能概要：UserController
 * 
 * @author juwenguang
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	@Resource
	private UserService userService;
	
    /**     
     * 添加新用户     
     * @see 访问/user/add时，GET请求就执行addUser(Model model)方法，POST请求就执行addUser(User user)方法     
     */      
    @RequestMapping(value="/add", method=RequestMethod.GET)      
    public String addUser(Model model){      
        //这里要传给前台一个空对象，否则会报告java.lang.IllegalStateException异常      
        //异常信息为Neither BindingResult nor plain target object for bean name 'user' available as request attribute      
        //并且传过去的key值要与前台modelAttribute属性值相同，即model.addAttribute("user", new User());      
        //我们也可以写成下面这种方式，此时SpringMVC会自动把对象名转换为小写值作为key，即User-->user  
    	double start = System.currentTimeMillis();
    	model.addAttribute(new User()); 
    	log.warn("Running time of the method addUser(GET):"+(System.currentTimeMillis()-start)+"ms");
    	
        return "user/add";      
    }      
    @RequestMapping(value="/add", method=RequestMethod.POST)      
    public String addUser(User user){ //这里参数中的user就应该与add.jsp中的modelAttribute="user"一致了      
    	double start = System.currentTimeMillis();
    	user.setUserPassword(EndecryptUtils.md5Password(user.getUserPassword()));
    	userService.add(user);     
    	log.warn("Running time of the method addUser(POST):"+(System.currentTimeMillis()-start)+"ms");
    	
        return "redirect:/user/list";      
    }      
	     
    /**     
     * 列出所有用户信息     
     */      
    @RequestMapping("/list")      
    public String list(Model model){
    	double start = System.currentTimeMillis();
    	List<User> users = userService.getAllUsers();
        model.addAttribute("users", users); 
    	log.warn("Running time of the method list:"+(System.currentTimeMillis()-start)+"ms");
    	     
        return "user/list";      
    }      
          
    /**     
     * 查询用户信息     
     * @see 访问该方法的路径就应该是"/user/具体的用户名"     
     * @see 这里value="/{username}"的写法，需要格外注意一下，它是一个路径变量，此时用来接收前台的一个资源     
     * @see 这时value="/{username}"就会到方法参数中找@PathVariable String username，并将路径变量值传给username参数     
     */      
    @RequestMapping(value="/view/{myname}", method=RequestMethod.GET)      
    public String show(@PathVariable String myname, Model model){  
    	double start = System.currentTimeMillis();
    	model.addAttribute("user", userService.get(myname));   
    	log.warn("Running time of the method show:"+(System.currentTimeMillis()-start)+"ms");
        return "user/show";      
    }      
          
    /**     
     * 编辑用户信息     
     * @see 访问该方法的路径就应该是"/user/具体的用户名/update"     
     */      
    @RequestMapping(value="/update/{myname}", method=RequestMethod.GET)      
    public String update(@PathVariable String myname, Model model){   
    	double start = System.currentTimeMillis();
    	model.addAttribute("user", userService.get(myname)); 
    	log.warn("Running time of the method update(GET):"+(System.currentTimeMillis()-start)+"ms");
             
        return "user/update";      
    }      
    @RequestMapping(value="/update/{myname}", method=RequestMethod.POST)      
    public String update(User user){     
    	double start = System.currentTimeMillis();
    	userService.update(user);     
    	log.warn("Running time of the method update(POST):"+(System.currentTimeMillis()-start)+"ms");
    	
        return "redirect:/user/list"; //也可以retun "forward:/user/list",此时浏览器地址栏会有不同      
    }      
          
    /**     
     * 删除用户信息     
     */      
    @RequestMapping(value="/delete/{myname}", method=RequestMethod.GET)      
    public String delete(@PathVariable String myname){      
    	double start = System.currentTimeMillis();
    	userService.remove(myname);      
    	log.warn("Running time of the method delete:"+(System.currentTimeMillis()-start)+"ms");
    	
        return "redirect:/user/list"; //删除完成后显示当前存在的所有用户信息      
    }   
    
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public Object upload(@RequestParam MultipartFile file) {
		String path = null;
		try {
			if(file.isEmpty()){
			}else{
				 path = saveFile (file);
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return path;
	}
	
	public static String saveFile (MultipartFile file) throws IOException{
	    byte[] fileBuff = file.getBytes();
	    
	    String resultPath = null;
		try {
			resultPath = DfsClient.getInstance().uploadFile(fileBuff, "jpg");
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} 
	
	    return resultPath;
	}  
 
}
