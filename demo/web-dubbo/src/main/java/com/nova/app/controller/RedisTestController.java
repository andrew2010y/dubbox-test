package com.nova.app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nova.app.sys.cache.Cache;
import com.nova.app.vo.TestRedis;


@RestController
@RequestMapping("/test/redis")
public class RedisTestController {
	@Resource
	private Cache cacheTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(RedisTestController.class);
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String test(HttpServletRequest request, Model model){
		String dataFlag = request.getParameter("dataFlag");
		if(dataFlag==null){
			return "faile";   
		}else if(dataFlag.equals("1")){//几k
			readFile(1);
		}else if(dataFlag.equals("2")){//几十k
			readFile(2);
		}else if(dataFlag.equals("3")){//几M
			readFile(3);
		}
		
		return "success";   
	}
	
	
	private void readFile(int flag) {
		String classPath = this.getClass().getClassLoader().getResource("").getPath(); 
		String configFilePath = classPath + File.separator + "redis-test/test.zip"; 
		  
	       InputStream in = null;
	       TestRedis testRedis = new TestRedis();
		   try {
			   in = new FileInputStream(configFilePath);
	            // 一次读多个字节
	            byte[] tempbytes = new byte[100];
	            int byteread = 0;
	            
	            if(flag==1){//几k
	            	tempbytes = new byte[5024];
	            	//testRedis.setTempbytes(tempbytes);
	            	in.read(tempbytes);
	            	cacheTemplate.set("testRedis1".getBytes(), tempbytes,0);  
	            }else if(flag==2){//几十k
		            // 读入多个字节到字节数组中，byteread为一次读入的字节数
		           /* while ((byteread = in.read(tempbytes)) != -1) {
		                System.out.write(tempbytes, 0, byteread);
		            }*/
	            	tempbytes = new byte[150024];
	            	in.read(tempbytes);
	            	
	            	cacheTemplate.set("testRedis2".getBytes(), tempbytes,0);  
	            }else if(flag==3){//几M
		            	File file = new File(configFilePath);
		                // 获得文件尺寸
		                long length = file.length();
		                if (length > Integer.MAX_VALUE) {
		                	System.out.println("文件尺寸太大");
		                }
		            
		                // 创建字节数组保存数据
		                byte[] bytes = new byte[(int)length];

		                // 读取字节
		                int offset = 0;
		                int numRead = 0;
		                while (offset < bytes.length
		                       && (numRead=in.read(bytes, offset, bytes.length-offset)) >= 0) {
		                    offset += numRead;
		                }
		            
		                // 确保所有字节被读取
		                if (offset < bytes.length) {
		                    throw new IOException("Could not completely read file "+file.getName());
		                }
		            
		                cacheTemplate.set("testRedis3".getBytes(), bytes,0);  
	            }
	        } catch (Exception e1) {
	            log.error(e1.getMessage(), e1);
	        } finally {
	            if (in != null) {
	                try {
	                    in.close();
	                } catch (IOException e1) {
	                }
	            }
	        }
	}
	 
}
