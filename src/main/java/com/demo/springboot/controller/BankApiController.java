package com.demo.springboot.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.demo.springboot.model.Account;
import com.demo.springboot.request.TransferRequest;
import com.demo.springboot.response.Response;
import com.demo.springboot.service.AccountService;
import com.demo.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class BankApiController {
	
	public static final Logger logger = LoggerFactory.getLogger(BankApiController.class);
	
	@Autowired
	AccountService accountService;
	
	@RequestMapping(value = "/account/", method = RequestMethod.GET)
	public ResponseEntity<List<Account>> listAllAccounts() {
		List<Account> accounts = accountService.findAllAccounts();
		if (accounts.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Account>>(accounts, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAccount(@PathVariable("id") long id) {
		logger.info("Fetching Account with id {}", id);
		Account account = accountService.findById(id);
		if (account == null) {
			logger.error("Account with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Account with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/account/", method = RequestMethod.POST)
	public ResponseEntity<?> createAccount(@RequestBody Account account, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Account : {}", account);

		if (accountService.isAccountExist(account)) {
			logger.error("Unable to create. A Account with id {} already exist", account.getAccountId());
			return new ResponseEntity(new CustomErrorType("Unable to create. A Account with Id " + 
			account.getAccountId() + " already exist."),HttpStatus.CONFLICT);
		}
		accountService.saveAccount(account);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/account/{id}").buildAndExpand(account.getAccountId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/account/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateAccount(@PathVariable("id") long id, @RequestBody Account account) {
		logger.info("Updating Account with id {}", id);

		Account currentAccount = accountService.findById(id);

		if (currentAccount == null) {
			logger.error("Unable to update. Account with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to update. Account with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		
		currentAccount.setFirstname(account.getFirstname());
		currentAccount.setLastname(account.getLastname());
		currentAccount.setAddress(account.getAddress());
		currentAccount.setMobile(account.getMobile());
		
		accountService.updateAccount(currentAccount);
		return new ResponseEntity<Account>(currentAccount, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/account/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAccount(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting Account with id {}", id);

		Account account = accountService.findById(id);
		if (account == null) {
			logger.error("Unable to delete. Account with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Account with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		accountService.deleteAccountById(id);
		return new ResponseEntity<Account>(HttpStatus.NO_CONTENT);
	}
}
