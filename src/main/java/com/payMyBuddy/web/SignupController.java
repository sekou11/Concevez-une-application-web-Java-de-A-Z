package com.payMyBuddy.Web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.payMyBuddy.Models.UserApp;
import com.payMyBuddy.Models.dto.UserDto;
import com.payMyBuddy.Service.UserService;

@Controller
//@RequestMapping("/signup")
public class SignupController {
    private UserService userService;
	


    public SignupController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/signup")
    public String signupView() {
        return "signup";
    }

    @PostMapping("/signup")
    private String signupUser(@ModelAttribute UserDto user,
    		Model model, RedirectAttributes redirAttrs) {
        String signupError = null;
       UserApp existsUser = userService.findByEmail(user.getEmail());
        if (existsUser != null) {
            signupError = "The email already exists";
        }
        if (signupError == null) {
           userService.saveDto(user);
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
