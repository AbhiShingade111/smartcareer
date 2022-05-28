package com.smartcareer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping({"/index","/"})
	public String dash() {
		return "user/dashboard";
	}
	
}
