package com.TM.Services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.TM.Repository.*;
import com.TM.entity.*;

import jakarta.transaction.Transactional;

@Service
public class AccountSer {
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Transactional
	public void transferMoney(String fromAccountNumber, String toAccountNumber, Double amount) {
	    Account fromAccount = accountRepo.findByAccountNumber(fromAccountNumber);
	    Account toAccount = accountRepo.findByAccountNumber(toAccountNumber);

	    if (fromAccount != null && toAccount != null) {
	        fromAccount.setBalance(fromAccount.getBalance() - amount);
	        
	        toAccount.setBalance(toAccount.getBalance() + amount);

	        accountRepo.save(fromAccount);
	        accountRepo.save(toAccount);
	    } else {
	        // Handle the case where either fromAccount or toAccount is null
	        throw new RuntimeException("One or both accounts not found");
	    }
	}

	@Transactional
	public void createAccount(String accountNumber,double ammount) {
		Account a=new Account(accountNumber,ammount);
		accountRepo.save(a);
	}
	
	public Account getAccountById(Long accountId) {
		return accountRepo.findById(accountId).orElseThrow();
	}

}
