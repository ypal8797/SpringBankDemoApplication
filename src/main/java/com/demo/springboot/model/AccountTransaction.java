package com.demo.springboot.model;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTION")
public class AccountTransaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long transactionId;
	private int type;
	private double amount;
	private Date date;
	
	protected AccountTransaction() {}
	
	public AccountTransaction(int type, double amount, Date date) {
		this.type = type;
		this.amount = amount;
		this.date = date;
	}

	
	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
