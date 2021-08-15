package org.sid.pettycach.dao.transaction;

import java.util.Date;

import java.util.List;

import org.sid.pettycach.entity.master.*;
import org.sid.pettycach.entity.transaction.ExpenseVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ExpenseVoucherRepository  extends JpaRepository<ExpenseVoucher,Long> {
	
	@Query("select narration from ExpenseVoucher expv where  expv.narration.id=:x")
	public Narration  findnarration(@Param("x")long id );
	@Query("select receiver from ExpenseVoucher expv where  expv.receiver.id=:x")
	public Receivers  findreceiver(@Param("x")long id );
	@Query("select sum(totalamount) from ExpenseVoucher expv where  expv.account.id=:x ")
	public 	Double  TotalExpense(@Param("x")long id );
	@Query("select totalamount from ExpenseVoucher expv where  expv.account.id=:x and  (expv.datecreation between '2021-08-01 00:00:00'and '2021-08-29 00:00:00') order by expv.datecreation ASC")
	public List<Double>  ExpenseAmount(@Param("x")long id); 
	@Query("select datecreation from ExpenseVoucher expv where  expv.account.id=:x and  (expv.datecreation between '2021-08-01 00:00:00'and '2021-08-29 00:00:00') order by expv.datecreation ASC")
	public List<Date>  Expensedate(@Param("x")long id); 
	@Query("select receiver from ExpenseVoucher expv where  expv.account.id=:x and  (expv.datecreation between '2021-08-01 00:00:00'and '2021-08-29 00:00:00') order by expv.datecreation ASC")
	public List<Receivers>  Expensereceiver(@Param("x")long id); 
	//@Query("select expenses from ExpenseVoucher expv  where  expv..id=:x")
	//public ExpenseHead findexpense(@Param("x")long id );
}
