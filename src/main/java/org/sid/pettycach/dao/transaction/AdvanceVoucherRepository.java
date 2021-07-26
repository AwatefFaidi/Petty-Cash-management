package org.sid.pettycach.dao.transaction;

import java.util.List;

import org.sid.pettycach.entity.master.Narration;
import org.sid.pettycach.entity.master.Receivers;
import org.sid.pettycach.entity.transaction.AdvanceVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface AdvanceVoucherRepository extends JpaRepository<AdvanceVoucher,Long>
{ 
	@Query("select receiver from AdvanceVoucher adv where  adv.receiver.id=:x")
	public Receivers  findreceiver(@Param("x")long id );
	
}
