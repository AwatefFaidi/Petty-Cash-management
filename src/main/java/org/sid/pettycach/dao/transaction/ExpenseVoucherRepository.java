package org.sid.pettycach.dao.transaction;

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
	//@Query("select e from ExpenseVoucher expv join ExpenseHead e where  expv.e.id=:x")
	//public List<ExpenseHead> findexpense(@Param("x")long id );
}
