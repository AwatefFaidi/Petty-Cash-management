package org.sid.pettycach.dao.transaction;

import java.util.List;
import org.sid.pettycach.entity.transaction.ReceiptVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository

public interface ReceiptVoucherRepository  extends JpaRepository<ReceiptVoucher,Long>{
	/*@Query("SELECT v FROM  org.sid.pettycach.entity.transaction.Voucher v  JOIN org.sid.pettycach.entity.transaction.ReceiptVoucher rv   ON v.id=:rv.id")
	public List<ReceiptVoucher> findallreceiptvoucher();*/
}
