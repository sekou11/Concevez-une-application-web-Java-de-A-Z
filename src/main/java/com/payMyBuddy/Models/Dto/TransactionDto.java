package com.payMyBuddy.Models.dto;

import java.math.BigDecimal;

import com.payMyBuddy.Models.Account;

public class TransactionDto {
    private Account userAccount;
    private String toEmail;
    private BigDecimal amount;
    private String description;

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
