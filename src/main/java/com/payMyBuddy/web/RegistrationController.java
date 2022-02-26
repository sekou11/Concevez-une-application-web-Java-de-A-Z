package com.payMyBuddy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.payMyBuddy.Models.UserApp;
import com.payMyBuddy.Models.Dto.UserAppDto;
import com.payMyBuddy.service.UserAppService;
import com.payMyBuddy.service.Auth.UserDetailsServiceImpl;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	private final UserAppService userService;
	private final UserDetailsServiceImpl  userDetailsService ;
	@Autowired
	public RegistrationController(UserAppService userService, UserDetailsServiceImpl userDetailsService) {
		super();
		this.userService = userService;
		this.userDetailsService = userDetailsService;
	}
	 @GetMapping
	public String showRegistrationPage() {
		 return "registration";
	 }
	   @PostMapping
	 public String register_proccess(UserAppDto userAppDto ,Model model ,RedirectAttributes attributes) {
		   String registerError =null;
		   UserApp userApp =  userService.findByEmail(userAppDto.getEmail());
		     if (userApp != null) {
				registerError=" Email Already taken..Choose another Email";
			}
		       if (registerError ==null) {
				userDetailsService.save(userAppDto);
			}
		       if (registerError ==null) {
				attributes.addFlashAttribute("message", "You've successfully signed up, please login");
				return "redirect:/login";
			} else {
                model.addAttribute("registerError", true);
			}
		 return "registration";
	 }
	 
	
			
	
	
	
	

}
