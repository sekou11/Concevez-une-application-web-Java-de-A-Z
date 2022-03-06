package com.payMyBuddy.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payMyBuddy.Models.Transaction;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	  public  List<Transaction> findTransactionsByFromAccount_Id(int id);
	  public  Page<Transaction> findTransactionsByFromAccount_Id(int id, Pageable pageable);
}
