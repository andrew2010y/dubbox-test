package com.nova.app.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	@Resource
	private UserService userService;
	
//	@RequestMapping("/")  
//    public ModelAndView getIndex(){    
//		ModelAndView mav = new ModelAndView("index"); 
//		User user = userService.selectUserById(2);
//	    mav.addObject("user", user); 
//        return mav;  
//    } 
	
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
        model.addAttribute(new User());      
        return "user/add";      
    }      
    @RequestMapping(value="/add", method=RequestMethod.POST)      
    public String addUser(User user){ //这里参数中的user就应该与add.jsp中的modelAttribute="user"一致了      
    	userService.add(user);      
        return "redirect:/user/list";      
    }      
	     
    /**     
     * 列出所有用户信息     
     */      
    @RequestMapping("/list")      
    public String list(Model model){
    	List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);      
        return "user/list";      
    }      
          
    /**     
     * 查询用户信息     
     * @see 访问该方法的路径就应该是"/user/具体的用户名"     
     * @see 这里value="/{username}"的写法，需要格外注意一下，它是一个路径变量，此时用来接收前台的一个资源     
     * @see 这时value="/{username}"就会到方法参数中找@PathVariable String username，并将路径变量值传给username参数     
     */      
    @RequestMapping(value="/{myname}", method=RequestMethod.GET)      
    public String show(@PathVariable String myname, Model model){      
        model.addAttribute(userService.get(myname));      
        return "user/show";      
    }      
          
    /**     
     * 编辑用户信息     
     * @see 访问该方法的路径就应该是"/user/具体的用户名/update"     
     */      
    @RequestMapping(value="/{myname}/update", method=RequestMethod.GET)      
    public String update(@PathVariable String myname, Model model){      
        model.addAttribute(userService.get(myname));      
        return "user/update";      
    }      
    @RequestMapping(value="/{myname}/update", method=RequestMethod.POST)      
    public String update(User user){      
    	userService.update(user);      
        return "redirect:/user/list"; //也可以retun "forward:/user/list",此时浏览器地址栏会有不同      
    }      
          
    /**     
     * 删除用户信息     
     */      
    @RequestMapping(value="/{myname}/delete", method=RequestMethod.GET)      
    public String delete(@PathVariable String myname){      
    	userService.remove(myname);      
        return "redirect:/user/list"; //删除完成后显示当前存在的所有用户信息      
    }   
    
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public Object upload(@RequestParam MultipartFile file) {
		String path = null;
		try {
			if(file.isEmpty()){
			}else{
				 path = upload2 (file);
				/*String tempFileName = file.getOriginalFilename();
				//fastDFS方式
				ClassPathResource cpr = new ClassPathResource("fdfs_client.conf");
				ClientGlobal.init(cpr.getClassLoader().getResource("fdfs_client.conf").getPath());
				byte[] fileBuff = file.getBytes();
			    String fileId = "";
			    String fileExtName = tempFileName.substring(tempFileName.lastIndexOf("."));
				
			    //建立连接
			    TrackerClient tracker = new TrackerClient();
			    TrackerServer trackerServer = tracker.getConnection();
			    StorageServer storageServer = null;
			    StorageClient1 client = new StorageClient1(trackerServer, storageServer);
			    
			    //设置元信息
			    NameValuePair[] metaList = new NameValuePair[3];
			    metaList[0] = new NameValuePair("fileName", tempFileName);
			    metaList[1] = new NameValuePair("fileExtName", fileExtName);
			    metaList[2] = new NameValuePair("fileLength", String.valueOf(file.getSize()));
			    
			    //上传文件
			    fileId = client.upload_file1(fileBuff, fileExtName, metaList);
			    
			    res.setHead_img(UserConstants.FILE_IMG_URL+fileId);
			    
				res.setRet_code(UserCodeEnum.SUCCESS.getCode());
				res.setRet_msg(UserCodeEnum.SUCCESS.getDesc());*/
			}
			
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return path;
	}
	
	public String upload2 (MultipartFile file) throws IOException{
	    NameValuePair[] meta_list = new NameValuePair[1];  
	    meta_list[0] = new NameValuePair("author", "mzl");  
	   
	    byte[] fileBuff = file.getBytes();
	    
	    String group_name = null;  
	    long startTime = System.currentTimeMillis();  
	   
	    StorageClient storageClient = FastClientUtils.getStorageClient();
	    String[] results = null;
		try {
			results = storageClient.upload_file(fileBuff, "jpg", null);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    System.out.println("upload_file time used: " + (System.currentTimeMillis() - startTime) + " ms");  
	    if (results == null){  
	        System.err.println("upload file fail, error code: " + storageClient.getErrorCode());  
	        return null;  
	    }  
	    group_name = results[0];  
	    String remote_filename = results[1];  
	    System.err.println("group_name: " + group_name + ", remote_filename: " + remote_filename);  
	
	    return remote_filename;
	}  
 
}
