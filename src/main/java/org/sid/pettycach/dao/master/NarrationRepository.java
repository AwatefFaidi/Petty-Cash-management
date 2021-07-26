package org.sid.pettycach.dao.master;


import org.sid.pettycach.entity.master.Narration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NarrationRepository extends JpaRepository<Narration,Long>{

}
