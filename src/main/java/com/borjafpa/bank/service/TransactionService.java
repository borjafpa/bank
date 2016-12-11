package com.borjafpa.bank.service;

import java.math.BigDecimal;
import java.util.List;

import com.borjafpa.bank.model.Transaction;
import com.borjafpa.bank.model.User;

public interface TransactionService {
    void save(Transaction transaction);
    
    List<Transaction> findAll(User user);
    
    BigDecimal getBalance(User user);
}
