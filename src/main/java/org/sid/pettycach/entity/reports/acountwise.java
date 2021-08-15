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
public class acountwise {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private double totalreceipt;
	private double totalexpense;
	private double totaladvance;
	private double totalbalance;
	private String  manager;
	@OneToOne
    @JoinColumn(name = "account_id")
	private Account account;

}
