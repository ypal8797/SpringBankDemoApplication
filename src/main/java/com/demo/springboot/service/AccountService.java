package com.demo.springboot.service;

import java.util.List;

import javax.transaction.Transaction;

import com.demo.springboot.model.Account;
import com.demo.springboot.request.TransferRequest;

public interface AccountService {

	Account findById(Long id);

	Account findByName(String name);

	void saveAccount(Account account);

	void updateAccount(Account account);

	void deleteAccountById(Long id);
	
	List<Account> findAllAccounts();
	
	boolean isAccountExist(Account account);
}
