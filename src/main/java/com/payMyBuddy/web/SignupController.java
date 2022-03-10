package com.payMyBuddy.Web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.payMyBuddy.Auth.UserDetailServiceImpl;
import com.payMyBuddy.Models.UserApp;
import com.payMyBuddy.Models.dto.UserDto;
import com.payMyBuddy.Service.UserService;

@Controller
@RequestMapping("/signup")
public class SignupController {
    private UserService userService;
	private UserDetailServiceImpl userDetailServiceImpl;


    @Autowired
    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String signupView() {
        return "signup";
    }

    @PostMapping
    private String signupUser(@ModelAttribute UserDto user,
    		Model model, RedirectAttributes redirAttrs) {
        String signupError = null;
       UserApp existsUser = userService.findByEmail(user.getEmail());
        if (existsUser != null) {
            signupError = "The email already exists";
        }
        if (signupError == null) {
           userDetailServiceImpl.save(user);
        }

        if (signupError == null) {
            redirAttrs.addFlashAttribute("message", "You've successfully signed up, please login.");
            return "redirect:/login";
        } else {
            model.addAttribute("signupError", true);
        }

        return "signup";

    }

}
