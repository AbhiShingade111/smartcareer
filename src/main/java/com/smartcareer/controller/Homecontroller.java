package com.smartcareer.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smartcareer.entities.User;
import com.smartcareer.helper.Message;
import com.smartcareer.repo.UserJpaRepository;

@Controller
public class Homecontroller {

	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserJpaRepository userRepo;

	@RequestMapping("/access-denied")
	public String denied() {
		return "access-denied";
	}
	
//	@RequestMapping("/")
//	public String home(Model model) {
//		model.addAttribute("title", "Login page");
//		return "signin";
//	}
	
	@RequestMapping({"/signup"})
	public String signup(Model model) {
		model.addAttribute("title", "SignUp page of SmartCareer");
		model.addAttribute("user", new User());
		return "signup";
	}
	
	@RequestMapping({"/signin","/"})
	public String singin(Model model) {
		model.addAttribute("title", "Login page");
		return "signin";
	}
	
	@RequestMapping(value = "/do-register",method = RequestMethod.POST)
	public String registration(@Valid @ModelAttribute("user") User user,BindingResult theResult,Model model,HttpSession session) { 
		try {
			
			if(theResult.hasErrors()) {
				System.out.println("Error "+theResult.toString());
				model.addAttribute("user", user);
				return "signup";
			}
			
			User theUser = userRepo.getUserByUsername(user.getEmail());
			
			if(theUser!=null) {
				session.setAttribute("message", new Message("Username already exist", "alert-danger"));
				return "signup";
			}
			
			System.out.println("User : "+user);
			user.setRole("ROLE_USER");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			
			
			User result = this.userRepo.save(user);
			System.out.println(result);
			model.addAttribute("user", result);
			session.setAttribute("message",new Message("Resgistration successful", "alert-success"));
			return "signup";
		} catch (Exception e) {
			
			e.printStackTrace();
			session.setAttribute("message", new Message("Something went wrong "+e.getMessage(),"alert-danger"));
			model.addAttribute("user", user);
			return "signup";
		}
	}
}
