package org.sid.pettycach.entity.reports;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.sid.pettycach.entity.master.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Runningbalance {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Double totalreceipt;
	private Double totalexpense;
	private Date date ;
	private Double balance;
	private String receiverby;
	private Double op;
	@OneToOne
    @JoinColumn(name = "account_id")
	private Account account;
	public Runningbalance(Double op,Double balance, Double totalexpense, Double totalreceipt, Date date, String name ) {
		super();
		this.totalreceipt = totalreceipt;
		this.totalexpense=totalexpense;
		this.op=op;
		this.balance=balance;
		this.receiverby=name;
		this.date = date;
	}
	
}
