package com.payMyBuddy.service;

import java.math.BigDecimal;

import com.payMyBuddy.Models.Account;
import com.payMyBuddy.Models.Dto.AccountDto;

public interface AccountService {
	public Boolean saveOrUpdate(AccountDto accountDto, String email);

	public Account findByUserId(int id);

	Boolean sendMoney(Account fromAcc, Account toAcc, BigDecimal amount, String description);

}
