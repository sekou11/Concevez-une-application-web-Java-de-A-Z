package com.payMyBuddy.Models.Dto;

import java.math.BigDecimal;

import com.payMyBuddy.Models.Account;

public class AccountDto {
	private Account accountId;
	private BigDecimal balance;
	public Account getUserAccount() {
		return accountId;
	}
	public void setUserAccount(Account accountId) {
		this.accountId = accountId;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	

}
