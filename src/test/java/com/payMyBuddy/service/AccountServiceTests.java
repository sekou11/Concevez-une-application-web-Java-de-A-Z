package com.payMyBuddy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.payMyBuddy.DAO.AccountRepository;
import com.payMyBuddy.DAO.TransactionRepository;
import com.payMyBuddy.DAO.UserAppRespository;
import com.payMyBuddy.Models.Account;
import com.payMyBuddy.Models.UserApp;
import com.payMyBuddy.Models.dto.AccountDto;
import com.payMyBuddy.Service.AccountService;
import com.payMyBuddy.Service.UserService;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountServiceTests {

    private AccountDto fromAccount = new AccountDto();
    private AccountDto toAccount = new AccountDto();

    private UserApp fromUser = new UserApp();
    private UserApp toUser = new UserApp();

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserAppRespository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @BeforeAll
    public void initAccountsWithUsers() {
        fromAccount.setBalance(new BigDecimal(1000));
        fromUser.setUserName("Foo");
        fromUser.setEmail("foo@gmail.com");
        fromUser.setPassword("foo");
        fromUser.setId(userService.save(fromUser));

        accountService.saveOrUpdate(fromAccount, "foo@gmail.com");

        toAccount.setBalance(new BigDecimal(0));
        toUser.setUserName("Bar");
        toUser.setEmail("bar@gmail.com");
        toUser.setPassword("bar");
        toUser.setId(userService.save(toUser));

        accountService.saveOrUpdate(toAccount, "bar@gmail.com");

    }

    @AfterAll
    public void cleanAccount() {
        transactionRepository.deleteAll();
        accountRepository.deleteAll();
        userRepository.deleteAll();
    }

    
    @Test
    @Order(1)
    public void testSendMoney() {
        Account account1 = accountRepository.findByUserId(fromUser.getId());
        Account account2 = accountRepository.findByUserId(toUser.getId());
        accountService.sendMoney(account1, account2, new BigDecimal(200), "");
        assertEquals(0, account2.getBalance().compareTo(new BigDecimal(200)));
    }

    @Test
    @Order(2)
    public void testFindByUserId() {
        Account account = accountService.findByUserId(fromUser.getId());
        assertEquals(fromUser.getId(), account.getUser().getId());
    }

}
