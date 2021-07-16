package org.sid.pettycach.dao.master;

import org.sid.pettycach.entity.master.Receivers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ReceiversRepository extends JpaRepository<Receivers,Long>{


}
