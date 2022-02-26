package com.payMyBuddy.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payMyBuddy.DAO.TransactionRepository;
import com.payMyBuddy.Models.Transaction;
import com.payMyBuddy.service.TransactionService;
@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
	private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
	@Override
	public Integer save(Transaction transaction) {
		// TODO Auto-generated method stub
		return transactionRepository.save(transaction).getId();
	}

	@Override
	public List<Transaction> findAll() {
		// TODO Auto-generated method stub
		return transactionRepository.findAll();
	}

	@Override
	public Optional<Transaction> findById(int id) {
		// TODO Auto-generated method stub
		return transactionRepository.findById(id);
	}

	@Override
	public Page<Transaction> findTransactionsByFromAccount_Id(int id, PageRequest pageRequest) {
		return transactionRepository.findTransactionsByFromAccount_Id(id, pageRequest);
	}

	@Override
	public List<Transaction> findTransactionsByFromAccount_Id(int id) {
		 return transactionRepository.findTransactionsByFromAccount_Id(id);
	}

}
