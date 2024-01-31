package com.TM.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TM.entity.Account;


public interface AccountRepo extends JpaRepository<Account, Long> {

	Account findByAccountNumber(String fromAccountNumber);

}
