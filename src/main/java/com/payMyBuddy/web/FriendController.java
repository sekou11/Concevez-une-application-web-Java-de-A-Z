package com.payMyBuddy.web;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    private UserAppService userService;
    private FriendService friendsService;

    public FriendController(UserAppService userService, FriendService friendsService) {
        this.userService = userService;
        this.friendsService = friendsService;
    }

    @PostMapping
    public String addFriend(Authentication authentication, @ModelAttribute("newFriend") FriendDto friendDto, Model model) {
        //1, Find user first
        String email = authentication.getName();
        UserApp user = userService.findByEmail(email);
        int errorType = 0;
        boolean success = false;

        //2, Check if friend is in database or has an account
        String friendEmail = friendDto.getFriendEmail();
        UserApp friendToBe = userService.findByEmail(friendEmail);

        if (friendToBe == null || friendToBe.getAccount() == null) {
            errorType = 4;
        } else {
            //3, Save
            Friend friends = new Friend();
            friends.setUser(user);
            friends.setFriend(friendToBe);
            
            friendsService.SaveFriend(friends);
            success = true;
        }

        model.addAttribute("success", success);
        model.addAttribute("errorType", errorType);

        return "connection";
    }
}