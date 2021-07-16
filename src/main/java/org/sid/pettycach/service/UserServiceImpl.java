package org.sid.pettycach.service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.sid.pettycach.dao.AppRoleRepository;
import org.sid.pettycach.dao.AppUserRepository;
import org.sid.pettycach.entity.App_Role;
import org.sid.pettycach.entity.UserStatus;
import org.sid.pettycach.entity.Userverified;
import org.sid.pettycach.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@Transactional
public class UserServiceImpl implements UserService {
  @Autowired	
  private AppUserRepository  appUserRepository;
  @Autowired
  private AppRoleRepository appRoleRepository;
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  
  public UserServiceImpl( BCryptPasswordEncoder bCryptPasswordEncoder) {
      this.appUserRepository = appUserRepository;
      this.appRoleRepository = appRoleRepository;
      this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }
  
  // function save for registration to give role ADMIN:
  //save user as ADMIN
	@Override
	public AppUser saveUser(String username, String password, String confirmedPassword) {
		AppUser  user=appUserRepository.findByUsername(username);
        System.out.println(user);
        if(user!=null) throw new RuntimeException("User already exists");
        if(!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password");
        AppUser appUser=new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        appUserRepository.save(appUser);
        addRoleToUser(username,"ADMIN");
        System.out.println(appUser);
        return appUser;
	}
	
	// function save user as USER 
	@Override
	public AppUser savenewuser(String username,String status,String name, String verified,Date last_activity,String password, String confirmedPassword) {
		AppUser  user=appUserRepository.findByUsername(username);
        System.out.println(user);
        if(user!=null) throw new RuntimeException("User already exists");
        if(!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password");
        AppUser appUser=new AppUser();
        appUser.setUsername(username);
        appUser.setName(name);
        
        appUser.setUserstatus(UserStatus.valueOf(status));
        
        appUser.setUserverified(Userverified.valueOf(verified));    
        appUser.setLast_activity(last_activity);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        appUserRepository.save(appUser);
        addRoleToUser(username,"USER");
        System.out.println(appUser);
        return appUser;
	}
	
	@Override
	public AppUser saveAppuser(AppUser user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		appUserRepository.save(user);
        addRoleToUser(user.getUsername(),"ADMIN");
        
        return user;
	}
	

	@Override
	public App_Role save(App_Role role) {
		return appRoleRepository.save(role);
	}

	@Override
	public AppUser loadUserByUsername(String username) {
		return appUserRepository.findByUsername(username);
	}
 
	

	@Override
	public void addRoleToUser(String username, String rolename) {
		AppUser appUser=appUserRepository.findByUsername(username);
        App_Role appRole=appRoleRepository.findByRoleName(rolename);
        appUser.getRoles().add(appRole);
		
	}
	@Override
	public List<AppUser> getAllUsers() {
		 return appUserRepository.findAll();
		 
	}
	
	@Override
	public void saveAppUser(AppUser appuser) {
		this.appUserRepository.save(appuser);
		
	}
	
	@Override
	public AppUser getUserById(long id) {
		  Optional < AppUser > optional = appUserRepository.findById(id);
		  AppUser user = null;
	        if (optional.isPresent()) {
	            user = optional.get();
	        } else {
	            throw new RuntimeException(" User not found for id :: " + id);
	        }
	        return user;
	}
	
	@Override
	public void deleteUserById(long id) {
		this.appUserRepository.deleteById(id);
	}

}
