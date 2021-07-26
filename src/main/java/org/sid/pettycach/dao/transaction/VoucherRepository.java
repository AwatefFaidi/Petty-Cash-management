package org.sid.pettycach.dao.transaction;


import java.util.Date;
import java.util.List;

import org.sid.pettycach.entity.master.Account;
import org.sid.pettycach.entity.transaction.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface VoucherRepository extends JpaRepository<Voucher,Long> {

	@Query("select account from Voucher v where  v.account.id=:x")
	public Account  findaccount(@Param("x")long id );
	@Query("select v from Voucher v where  v.account=:ac AND v.amount=:am")
	
	public List<Voucher> showvoucher(@Param("ac")Account account,@Param("am")double amount );                // 1. Spring JPA In cause using method name
	
}
