package com.borjafpa.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.borjafpa.bank.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	 @Query("select t from Transaction t where t.user.id = ?1 order by createdAt desc")
	 public List<Transaction> findByUserId(Long userId);
}
