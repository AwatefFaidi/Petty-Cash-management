package org.sid.pettycach.entity.transaction;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.PrimaryKeyJoinColumn;

import org.sid.pettycach.entity.master.Account;
import org.sid.pettycach.entity.transaction.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data  @ToString
@PrimaryKeyJoinColumn(name = "IDRV")
public class ReceiptVoucher  extends Voucher{
	
	
}
