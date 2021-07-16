package org.sid.pettycach.entity.transaction;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.sid.pettycach.entity.master.Account;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Inheritance(strategy=InheritanceType.JOINED)  

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public  class Voucher   {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private  Date datecreation;
	private double amount;
	private String  remarks;
	@OneToOne
    @JoinColumn(name = "account_id")
	private Account account;
	

}
