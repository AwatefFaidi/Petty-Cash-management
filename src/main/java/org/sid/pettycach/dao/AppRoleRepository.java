package org.sid.pettycach.dao;
import org.sid.pettycach.entity.App_Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface AppRoleRepository extends JpaRepository<App_Role,Long>{
	
    public App_Role findByRoleName(String roleName);

}


