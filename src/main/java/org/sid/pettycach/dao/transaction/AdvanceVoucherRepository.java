package org.sid.pettycach.dao.transaction;

import org.sid.pettycach.entity.transaction.AdvanceVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AdvanceVoucherRepository extends JpaRepository<AdvanceVoucher,Long>
{
}
