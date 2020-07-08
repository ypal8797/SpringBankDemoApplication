package com.demo.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.springboot.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	Account findByName(String name);

}
