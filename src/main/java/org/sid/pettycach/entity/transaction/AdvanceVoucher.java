package org.sid.pettycach.entity.transaction;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.sid.pettycach.entity.master.Receivers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@PrimaryKeyJoinColumn(name = "AV_id")
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class AdvanceVoucher extends Voucher{
	private double advance;
	private double returnamount;
	private double balance;
	
	@OneToOne  
	private  Receivers receiver; 

}
