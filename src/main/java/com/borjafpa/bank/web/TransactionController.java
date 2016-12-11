package com.borjafpa.bank.web;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.borjafpa.bank.model.Transaction;
import com.borjafpa.bank.model.User;
import com.borjafpa.bank.service.TransactionService;
import com.borjafpa.bank.service.UserService;

@Controller
public class TransactionController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private TransactionService transactionService;
    
    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public String transactions(Model model, String username) {
        return showTransactions(model, username);
    }
    
    @RequestMapping(value = "/deposit", method = RequestMethod.GET)
    public String deposit(Model model) {
    	return createTransaction(model, "Deposit");
    }
    
    @RequestMapping(value = "/withdraw", method = RequestMethod.GET)
    public String withdraw(Model model) {
    	return createTransaction(model, "Withdraw");
    }
    
    @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    public String transaction(Model model, String username, String type, BigDecimal amount) {
    	
    	Transaction transaction = new Transaction();
        transaction.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        transaction.setUser(userService.findByUsername(username));
        transaction.setType(type);
        transaction.setAmount(amount.abs());
        
        transactionService.save(transaction);
        
        return showTransactions(model, username);
    }
    
    private String createTransaction(Model model, String type) {
    	model.addAttribute("transactionType", type);
        model.addAttribute("transactionForm", new Transaction());

        return "transaction";
    }
    
    private String showTransactions(Model model, String username) {
    	User user = userService.findByUsername(username);
    	
    	model.addAttribute("transactions", transactionService.findAll(user));
    	model.addAttribute("transactionsBalance", transactionService.getBalance(user));
    	
    	return "transactions";
    }
}
