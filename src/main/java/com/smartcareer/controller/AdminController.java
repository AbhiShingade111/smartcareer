package com.smartcareer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping("/index")
	public String adminHome() {
		return "admin/dashboard";
	}
	
}
