package org.sid.pettycach.dao;




import org.sid.pettycach.entity.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    public AppUser findByUsername(String username);
   /* @Query("select c from appuser c where c.nom like :x")
	public Page<App_User> search(@Param("x")String mc, Pageable pageable );
    */

}
