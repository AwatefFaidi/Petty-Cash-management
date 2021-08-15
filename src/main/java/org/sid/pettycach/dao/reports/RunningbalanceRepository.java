package org.sid.pettycach.dao.reports;

import java.util.List;

import org.sid.pettycach.entity.master.Account;
import org.sid.pettycach.entity.reports.Runningbalance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RunningbalanceRepository extends JpaRepository<Runningbalance,Long>{
	@Query("select v from Runningbalance v where  v.account=:ac and (v.date between '2021-08-08 00:00:00'and '2021-08-10 00:00:00') " )
	public List<Runningbalance> show(@Param("ac")Account account );
	
}
