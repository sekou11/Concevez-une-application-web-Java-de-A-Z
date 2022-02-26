package com.payMyBuddy.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.payMyBuddy.Models.Account;
import com.payMyBuddy.Models.UserApp;
import com.payMyBuddy.service.AccountService;
import com.payMyBuddy.service.FriendService;
import com.payMyBuddy.service.TransactionService;
import com.payMyBuddy.service.UserAppService;

@Controller
//@RequestMapping
public class HomeController {
    private UserAppService userService;
    private AccountService accountService;
    private TransactionService transactionService;
    private FriendService friendsService;

    public HomeController(UserAppService userService, AccountService accountService, TransactionService transactionService, FriendService friendsService) {
        this.userService = userService;
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.friendsService = friendsService;
    }

    @GetMapping({"/", "/home"})
    public String getHome(Authentication authentication,
                          Model model) {

       
      UserApp user = userService.findByEmail(authentication.getName());
        String name = user.getUserName();
        Set<UserApp> myFriends = friendsService.findAllMyFriends(user.getId());

        Account account = accountService.findByUserId(user.getId());

        model.addAttribute("transactionList", (account == null) ? new ArrayList<>() : transactionService.findTransactionsByFromAccount_Id(account.getId()));
        model.addAttribute("friendList", myFriends == null ? new HashSet<>() : myFriends);

        model.addAttribute("message", "Hi " + name);
        model.addAttribute("balance", user.getAccount() == null ? new BigDecimal(0) : user.getAccount().getBalance());
        return "home";
    }
}
