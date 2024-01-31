package com.TM.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@SuppressWarnings("deprecation")
public class CreateAccountRequest {

    @NotBlank(message="Account no should no be blank")
	private String accountNumber;
	 @Positive(message = "initial balance shuold be positive")
	private double initialBalance;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(double initialBalance) {
		this.initialBalance = initialBalance;
	}

}
