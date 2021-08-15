package org.sid.pettycach.entity.transaction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.sid.pettycach.entity.master.ExpenseHead;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class ExpenseDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
	private String voucherremarks;
	private String voucheramount;
	private String expensehead;
	public ExpenseDetails(String voucherremarks, String voucheramount, String expensehead) {
		super();
		this.voucherremarks = voucherremarks;
		this.voucheramount = voucheramount;
		this.expensehead = expensehead;
	}
	
	
}
