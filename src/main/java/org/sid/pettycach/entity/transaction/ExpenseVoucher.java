package org.sid.pettycach.entity.transaction;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.sid.pettycach.entity.master.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@PrimaryKeyJoinColumn(name = "EV_id")
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class ExpenseVoucher extends Voucher{
	
	@Enumerated(EnumType.STRING)
    private VoucherStatus expensestatus;
	private String voucherremarks;
	private Double voucheramount;
	private double totalamount;
	@OneToOne  
	private Narration narration;
	
	@OneToOne  
	private  Receivers receiver;  
	
	/*@OneToMany
	private Collection <ExpenseDetails> details; */
	@OneToMany
	private Collection <ExpenseHead> expenses; 

}
