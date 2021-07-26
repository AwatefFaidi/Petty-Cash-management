package org.sid.pettycach.entity.transaction;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;

import org.sid.pettycach.entity.UserStatus;
import org.sid.pettycach.entity.master.Account;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data  @ToString @NoArgsConstructor 
@PrimaryKeyJoinColumn(name = "IDRV")
public class ReceiptVoucher  extends Voucher{
	@Enumerated(EnumType.STRING)
    private VoucherStatus receiptstatus;
	 public ReceiptVoucher( Date datecreation,double amount, String remarks, Account account) {
	        super(datecreation, amount, remarks, account);
	       
	    }
	
}
