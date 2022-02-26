package com.payMyBuddy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.payMyBuddy.Models.Friend;
import com.payMyBuddy.Models.UserApp;
import com.payMyBuddy.Models.Dto.FriendDto;
import com.payMyBuddy.service.FriendService;
import com.payMyBuddy.service.UserAppService;

@Controller
@RequestMapping("/friend")
public class FriendController {

	private final UserAppService userService;
	private final FriendService friendService;

	@Autowired
	public FriendController(UserAppService userService, FriendService friendService) {
		super();
		this.userService = userService;
		this.friendService = friendService;
	}

	@PostMapping
	public String addFriend(Authentication authentication, @ModelAttribute("newFriend") FriendDto friendDto,
			Model model) {
		String email = authentication.getName();
		UserApp user = userService.findByEmail(email);
		int errorType = 0;
		boolean success = false;
		
		String friendEmail = friendDto.getFriendEmail();
        UserApp friend = userService.findByEmail(friendEmail);

        if (friend == null || friend.getAccount() == null) {
        	  //in view html
            errorType = 4;
        } else {
           
            Friend friends = new Friend();
            friends.setUser(user);
            friends.setFriend(friend);
            friendService.SaveFriend(friends);
            success = true;
        }

        model.addAttribute("success", success);
        model.addAttribute("errorType", errorType);

        return "connection";
    }

		

	

	@GetMapping
	public String ShowConnectionPageaddFriend() {
		return "connection";

	}

}
