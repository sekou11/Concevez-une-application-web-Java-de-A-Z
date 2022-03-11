package com.payMyBuddy.Service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.payMyBuddy.DAO.AccountRepository;
import com.payMyBuddy.DAO.TransactionRepository;
import com.payMyBuddy.DAO.UserAppRespository;
import com.payMyBuddy.Models.Account;
import com.payMyBuddy.Models.Transaction;
import com.payMyBuddy.Models.UserApp;
import com.payMyBuddy.Service.TransactionService;
import com.payMyBuddy.Service.UserService;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransactionServiceTests {

    private Account account1 = new Account();
    private Account account2 = new Account();
    private UserApp user1 = new UserApp();
    private UserApp user2 = new UserApp();
    private Transaction transaction = new Transaction();

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserAppRespository userRepository;

    @BeforeEach
    private void initAccountsAndTransaction() {
        account1.setBalance(new BigDecimal(100));
        user1.setUserName("Foo");
        user1.setEmail("foo@gmail.com");
        user1.setPassword("123");
        user1.setId(userService.saveUser(user1));
        account1.setUser(user1);
        accountRepository.save(account1);

        account2.setBalance(new BigDecimal(0));
        user2.setUserName("Bar");
        user2.setEmail("bar@gmail.com");
        user2.setPassword("456");
        user2.setId(userService.saveUser(user2));
        account2.setUser(user2);
        accountRepository.save(account2);

        transaction.setAmount(new BigDecimal(20));
        transaction.setFromAccount(accountRepository.findByUserId(user1.getId()));
        transaction.setToAccount(accountRepository.findByUserId(user2.getId()));
        transaction.setDescription("Test transfer 20 $.");
        transactionRepository.save(transaction);
    }

    @AfterEach
    public void cleanAccount() {
        
        transactionRepository.deleteAll();
        accountRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void testFindById() {
        Optional<Transaction> transaction = transactionService.findById(this.transaction.getId());
        String userName = this.transaction.getToAccount().getUser().getUserName();
        assertEquals(user2.getUserName(), userName);
    }

    @Test
    public void testFindAll() {
        List<Transaction> transactions = transactionService.findAll();
        assertEquals(1, transactions.size());
    }

    @Test
    public void testFindAllByFromAccountId() {
        List<Transaction> transactions = transactionService.findTransactionsByFromAccount_Id(this.transaction.getFromAccount().getId());
        assertEquals(1, transactions.size());
    }

}
