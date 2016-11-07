package com.nova.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/sys")
public class SysController {

	@RequestMapping("/perm-error")
	public String showPermError(){
		return "perm-error";
	}
}
