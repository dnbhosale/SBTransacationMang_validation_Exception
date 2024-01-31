package com.TM.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.TM.DTO.*;
import com.TM.Repository.AccountRepo;
import com.TM.entity.Account;
import com.TM.Services.AccountSer;

@RestController
public class AccountController {
	
	@Autowired
	private AccountSer accountSer;
	
	@PostMapping("/transfer")
	public String transferMoney(@RequestBody TransferRequest transferRequest) {
		
		try {
			accountSer.transferMoney(transferRequest.getFromAccountNumber(), transferRequest.getToAccountNumber(), transferRequest.getAmmount());
			return "Ammount transfer successfully !!";
		} catch (Exception e) {
			return "Failed -"+e.getMessage();
			// TODO: handle exception
		}
		
	}
	
	@PostMapping("/createAccount")
	public String createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
		
		try {
			accountSer.createAccount(createAccountRequest.getAccountNumber(), createAccountRequest.getInitialBalance());
			return "Account has been created"+createAccountRequest.getAccountNumber();
			
		} catch (Exception e) {
			return "Error Failed to create an Account "+e.getMessage();
		}
		
	}
	@Cacheable(value = "accountCache", key = "#accountId")
	@GetMapping("/getAccount/{accountId}")
	public ResponseEntity<?> getAccountDetails(@PathVariable Long accountId) {
	    try {
	        Optional<Account> optionalAccount = Optional.of(accountSer.getAccountById(accountId));
	        if (optionalAccount.isPresent()) {
	            Account account = optionalAccount.get();
	            return ResponseEntity.ok(account);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
	    }
	}


}
