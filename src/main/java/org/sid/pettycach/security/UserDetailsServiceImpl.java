package org.sid.pettycach.security;

import java.util.ArrayList;
import java.util.Collection;
import org.sid.pettycach.entity.AppUser;
import org.sid.pettycach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	//  we can replace userService.loadUserByUsername(username); by  appUserRepository.findByUsername(username)
	@Autowired
    private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 AppUser appUser=userService.loadUserByUsername(username);
		  if(appUser==null) throw new UsernameNotFoundException("invalid user");
		  //convert all  my roles in authorities  
		  Collection<GrantedAuthority> authorities=new ArrayList<>();
		   appUser.getRoles().forEach(r->{
		            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
		        });
		   //transfer my data to object user which provide by spring security
		   return new org.springframework.security.core.userdetails.
		    User(
				   appUser.getUsername(), 
				   appUser.getPassword(), 
					authorities);
		    
		    }


}
