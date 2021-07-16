package org.sid.pettycach.service;

import org.sid.pettycach.entity.App_Role;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;

import org.sid.pettycach.entity.AppUser;

// manage our users + to spring data 
public interface UserService {
	public AppUser saveUser(String username,String password,String confirmedPassword);
	
    public App_Role save(App_Role role);
    public AppUser loadUserByUsername(String username);
    public void addRoleToUser(String username,String rolename);
    List < AppUser > getAllUsers();
    void saveAppUser(AppUser appuser);
    AppUser getUserById(long id);
    void deleteUserById(long id);
	AppUser savenewuser(String username, String status, String name,  String verified,
			Date last_activity, String password, String confirmedPassword);
	AppUser saveAppuser(AppUser user);
}
