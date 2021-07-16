package org.sid.pettycach.entity.transaction;

import java.util.Collection;

import javax.persistence.Entity;
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
	private double totalamount;
	
	private String voucherremarks;
	
	@OneToOne  
	private Narration narration;
	
	@OneToOne  
	private  Receivers receiver;  
	
	@OneToMany
	private Collection <ExpenseHead> expenses; 

}
