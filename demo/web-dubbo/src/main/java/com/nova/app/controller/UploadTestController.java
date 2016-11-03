package com.nova.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/test")
public class UploadTestController {

	private static final Logger log = LoggerFactory.getLogger(UploadTestController.class);
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String test(HttpServletRequest request, Model model){
		return "test/upload";   
	}
	
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public Object upload(@RequestParam MultipartFile file) {
		String path = null;
		try {
			if(!file.isEmpty()){
				 path = UserController.saveFile (file);
			}
			
		} catch (Exception e) {
			 log.error(e.getMessage(), e);
		}
		return path;
	}
}
