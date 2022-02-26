package com.payMyBuddy.service.Impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payMyBuddy.DAO.AccountRepository;
import com.payMyBuddy.DAO.TransactionRepository;
import com.payMyBuddy.DAO.UserAppRepositry;
import com.payMyBuddy.Models.Account;
import com.payMyBuddy.Models.Transaction;
import com.payMyBuddy.Models.UserApp;
import com.payMyBuddy.Models.Dto.AccountDto;
import com.payMyBuddy.service.AccountService;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final UserAppRepositry userRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository, UserAppRepositry userRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Boolean saveOrUpdate(AccountDto accountDto, String email) {
        UserApp user = userRepository.findByEmail(email);
        Account account = accountRepository.findByUserId(user.getId());
        if (account == null) {
            Account newAccount = new Account();
            newAccount.setUser(user);
            newAccount.setBalance(accountDto.getBalance());
            accountRepository.save(newAccount);
        } else {
            accountRepository.update(account.getId(), accountDto.getBalance());
        }
        return true;
    }

    @Override
    public Account findByUserId(int id) {
        return accountRepository.findByUserId(id);
    }

    @Override
    public Boolean sendMoney(Account fromAcc, Account toAcc, BigDecimal amount, String description) {
        boolean send = false;
        if (fromAcc == null || toAcc == null) {
            return false;
        } else {
            if (fromAcc.getBalance().compareTo(BigDecimal.ONE) > 0
                    && fromAcc.getBalance().compareTo(amount) > 0) {
                
                fromAcc.setBalance(fromAcc.getBalance().subtract(amount.add(new BigDecimal("0.005").multiply(amount))));
                accountRepository.save(fromAcc);
                toAcc.setBalance(toAcc.getBalance().add(amount));
                accountRepository.save(toAcc);

                
                Transaction transaction = new Transaction();
                transaction.setFromAccount(fromAcc);
                transaction.setToAccount(toAcc);
                transaction.setDescription(description);
                transaction.setAmount(amount);
                transaction.setTransacted(true);
                transaction.setCharge(new BigDecimal("0.005").multiply(amount));
                transactionRepository.save(transaction);

              
                fromAcc.getTransactions().add(transaction);

                send = true;
            }
        }

        return send;
    }

}