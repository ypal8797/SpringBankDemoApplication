package com.demo.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.springboot.model.Account;
import com.demo.springboot.repositories.AccountRepository;

@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account findById(Long id) {
		// TODO Auto-generated method stub
		return accountRepository.findOne(id);
	}

	@Override
	public Account findByName(String name) {
		// TODO Auto-generated method stub
		return accountRepository.findByName(name);
	}

	@Override
	public void saveAccount(Account account) {
		// TODO Auto-generated method stub
		accountRepository.save(account);
	}

	@Override
	public void updateAccount(Account account) {
		saveAccount(account);
		
	}

	@Override
	public void deleteAccountById(Long id) {
		// TODO Auto-generated method stub
		accountRepository.delete(id);
	}

	@Override
	public List<Account> findAllAccounts() {
		// TODO Auto-generated method stub
		return accountRepository.findAll();
	}

	@Override
	public boolean isAccountExist(Account account) {
		// TODO Auto-generated method stub
		return findById(account.getAccountId()) != null;
	}

}
