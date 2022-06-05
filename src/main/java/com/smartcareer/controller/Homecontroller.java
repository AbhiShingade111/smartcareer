package com.smartcareer.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smartcareer.entities.Contactmail;
import com.smartcareer.entities.User;
import com.smartcareer.helper.Message;
import com.smartcareer.repo.ContactJpaRepository;
import com.smartcareer.repo.UserJpaRepository;

@Controller
public class Homecontroller {

	@Autowired
	private ContactJpaRepository contactjpa;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserJpaRepository userRepo;

	@RequestMapping("/access-denied")
	public String denied(Model model) {
		model.addAttribute("title", "Access Denied !!");
		return "access-denied";
	}

	@RequestMapping({ "/signup" })
	public String signup(Model model) {
		model.addAttribute("title", "SignUp page");
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping({ "/signin", "/" })
	public String singin(Model model) {
		model.addAttribute("title", "Login page");
		return "signin";
	}

	@RequestMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("title", "Contact us");
		model.addAttribute("contactmail", new Contactmail());
		return "contact";
	}

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public String sendcontact(@Valid @ModelAttribute("contactmail") Contactmail contactmail, BindingResult thResult,
			Model model, HttpSession session) {

		try {
			if (thResult.hasErrors()) {
				model.addAttribute("contactmail", contactmail);
				return "contact";
			}

			Contactmail contact = this.contactjpa.save(contactmail);
			System.out.println(contact);
			model.addAttribute("title", "Contact us");
//			model.addAttribute("contactmail", contact);
			session.setAttribute("message", new Message("Thank you connecting with us", "alert-success"));
			return "contact";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("title", "Contact us");
			session.setAttribute("message", new Message("Something went wrong", "alert-danger"));
			return "contact";
		}
	}

	@RequestMapping(value = "/do-register", method = RequestMethod.POST)
	public String registration(@Valid @ModelAttribute("user") User user, BindingResult theResult, Model model,
			HttpSession session) {
		try {

			if (theResult.hasErrors()) {
				System.out.println("Error " + theResult.toString());
				model.addAttribute("user", user);
				return "signup";
			}

			User theUser = userRepo.getUserByUsername(user.getEmail());

			if (theUser != null) {
				session.setAttribute("message", new Message("Username already exist", "alert-danger"));
				return "signup";
			}

			System.out.println("User : " + user);
			user.setRole("ROLE_USER");
			user.setPassword(passwordEncoder.encode(user.getPassword()));

			User result = this.userRepo.save(user);
			System.out.println(result);
			model.addAttribute("title", "Sign Up");
			model.addAttribute("user", result);
			session.setAttribute("message", new Message("Resgistration successful", "alert-success"));
			return "signup";
		} catch (Exception e) {

			e.printStackTrace();
			session.setAttribute("message", new Message("Something went wrong " + e.getMessage(), "alert-danger"));
			model.addAttribute("user", user);
			return "signup";
		}

	}

	
}
