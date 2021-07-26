package org.sid.pettycach.dao.transaction;

import java.util.Date;
import java.util.List;

import org.sid.pettycach.entity.master.Account;
import org.sid.pettycach.entity.transaction.ReceiptVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository

public interface ReceiptVoucherRepository  extends JpaRepository<ReceiptVoucher,Long>{
	
	/*
	
	 @Query("select rv  org.sid.pettycach.entity.transaction.ReceiptVoucher rv  where rv.datecreation = :datecreation AND  rv.account = :account AND  rv.amount = :amount")
	    List<ReceiptVoucher> show(
	      @Param("datecreation") Date datecreation,
	      @Param("account") Account account,
	      @Param("amount") Date amount);*/
}
