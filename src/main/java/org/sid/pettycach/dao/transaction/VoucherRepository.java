package org.sid.pettycach.dao.transaction;


import org.sid.pettycach.entity.transaction.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface VoucherRepository extends JpaRepository<Voucher,Long> {

	/*@Query("SELECT rv\r\n" + 
			" FROM org.sid.pettycach.entity.transaction.ReceiptVoucher rv\r\n" + 
			" INNER JOIN org.sid.pettycach.entity.transaction.Voucher v ON rv.number=:v.number")
	public List<ReceiptVoucher> findallreceiptvoucher();
*/
	
}
