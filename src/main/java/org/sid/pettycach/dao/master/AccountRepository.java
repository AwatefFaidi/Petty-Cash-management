package org.sid.pettycach.dao.master;

import org.sid.pettycach.entity.AppUser;
import org.sid.pettycach.entity.master.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
	//@Query("select appuser  from accounts_users c where  c.user_id=:x")
	//public AppUser  finduser(@Param("x")long id );
}
