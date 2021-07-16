package org.sid.pettycach.dao.transaction;

import org.sid.pettycach.entity.transaction.ExpenseVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ExpenseVoucherRepository  extends JpaRepository<ExpenseVoucher,Long> {

}
