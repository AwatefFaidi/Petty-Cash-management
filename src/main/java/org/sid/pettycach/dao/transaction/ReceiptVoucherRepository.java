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
	

	
	@Query("select sum(amount) from ReceiptVoucher v where  v.account.id=:x and v.receiptstatus='verified'")
	public 	Double  TotalReceipt(@Param("x")long id );
	
	@Query("select amount  from ReceiptVoucher rv where  rv.account.id=:x  and rv.receiptstatus='verified' and  (rv.datecreation between '2021-08-01 00:00:00'and '2021-08-29 00:00:00') order by rv.datecreation ASC")
	public List<Double>  ReceiptAmount(@Param("x")long id); 
	@Query("select datecreation from ReceiptVoucher rv where  rv.account.id=:x  and rv.receiptstatus='verified' and  (rv.datecreation between '2021-08-01 00:00:00'and '2021-08-29 00:00:00') order by rv.datecreation ASC")
	public List<Date>  Receiptdate(@Param("x")long id); 
	
	
	//@Query("select amount from ReceiptVoucher rv where  rv.account.id=:x and rv.receiptstatus='verified' and (rv.datecreation between date1 and date2 )")
	//public List<Double>  ReceiptAmount(@Param("x")long id, @Param("startdate") Date date1,@Param("enddate") Date date2); 
	
	/*
	 @Query("select rv  org.sid.pettycach.entity.transaction.ReceiptVoucher rv  where rv.datecreation = :datecreation AND  rv.account = :account AND  rv.amount = :amount")
	    List<ReceiptVoucher> show(
	      @Param("datecreation") Date datecreation,
	      @Param("account") Account account,
	      @Param("amount") Date amount);*/
}
