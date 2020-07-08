package com.demo.springboot.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="APP_CUSTOMER")
public class Account implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long accountId;
	
	@NotEmpty
	@Column(name="ACCOUNTNUMBER", nullable=false)
	String accountNumber;
	
	@NotEmpty
	@Column(name="BALANCE", nullable=false)
    double balance;
	
	@Column(name="SSN", nullable=false)
	private Integer ssn;

	@NotEmpty
	@Column(name="FIRSTNAME", nullable=false)
	private String firstname;
	
	@NotEmpty
	@Column(name="LASTNAME", nullable=false)
	private String lastname;
	
	@NotEmpty
	@Column(name="Address", nullable=false)
	private String address;
	
	@NotEmpty
	@Column(name="MOBILE", nullable=false)
	private Integer mobile;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Integer getSsn() {
		return ssn;
	}

	public void setSsn(Integer ssn) {
		this.ssn = ssn;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getMobile() {
		return mobile;
	}

	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}
}

