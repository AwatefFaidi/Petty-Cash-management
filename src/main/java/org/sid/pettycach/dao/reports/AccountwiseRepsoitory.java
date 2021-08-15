package org.sid.pettycach.dao.reports;

import java.util.List;

import org.sid.pettycach.entity.master.Account;

import org.sid.pettycach.entity.reports.acountwise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface AccountwiseRepsoitory extends JpaRepository<acountwise,Long>
{ 
@Query("select v from acountwise v where  v.account=:ac" )
	
	public List<acountwise> show(@Param("ac")Account account );
	
}
