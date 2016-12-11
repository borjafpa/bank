package com.borjafpa.bank.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borjafpa.bank.model.Transaction;
import com.borjafpa.bank.model.User;
import com.borjafpa.bank.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;
	
    @Override
    public void save(Transaction transaction) {
    	transactionRepository.save(transaction);
    }
    
	@Override
	public BigDecimal getBalance(User user) {
		BigDecimal balance = new BigDecimal(0);
    	
    	for ( Transaction transaction: user.getTransactions() ) {
    		if ( "Deposit".equals(transaction.getType()) ) {
    			balance = balance.add(transaction.getAmount());
    		} else {
    			balance = balance.subtract(transaction.getAmount());
    		}
    	}
    	
    	return balance;
	}

	@Override
	public List<Transaction> findAll(User user) {
		return transactionRepository.findByUserId(user.getId());
	}
}
