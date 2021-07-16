package org.sid.pettycach.dao.master;

import org.sid.pettycach.entity.master.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

}
