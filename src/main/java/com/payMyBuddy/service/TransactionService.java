package com.payMyBuddy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.payMyBuddy.Models.Transaction;

public interface TransactionService {
	
	 public Integer save(Transaction transaction);
	 public List<Transaction> findAll();
	 public Optional<Transaction> findById(int id);
	 public  Page<Transaction> findTransactionsByFromAccount_Id(int id, PageRequest pageRequest);
	 public List<Transaction> findTransactionsByFromAccount_Id(int id);
}
