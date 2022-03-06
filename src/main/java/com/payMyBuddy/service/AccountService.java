package com.payMyBuddy.Service;

import java.math.BigDecimal;

import com.payMyBuddy.Models.Account;
import com.payMyBuddy.Models.dto.AccountDto;

public interface AccountService {
	public Boolean saveOrUpdate(AccountDto accountDto, String email);

	public Account findByUserId(int id);

	public Boolean sendMoney(Account fromAcc, Account toAcc, BigDecimal amount, String description);

}
