package org.sid.pettycach.web;

import java.util.List;
import java.util.Optional;

import org.sid.pettycach.dao.AppRoleRepository;
import org.sid.pettycach.dao.AppUserRepository;
import org.sid.pettycach.entity.AppUser;
import org.sid.pettycach.entity.App_Role;
import org.sid.pettycach.entity.UserStatus;
import org.sid.pettycach.entity.Userverified;
import org.sid.pettycach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Transactional
public class UserController {
	 @Autowired
	    private UserService userService;
	 
	 @Autowired
	    private AppUserRepository appUserRepository;
	 @Autowired
	    private AppRoleRepository appRoleRepository;
	 
	// display list of users
	 @GetMapping("/users")
		public String showusersList(Model model) {
			List<AppUser> userlist= appUserRepository.findAll();
		   model.addAttribute("userlist",userlist );
		    return "users";
	}
	
	 @GetMapping("users/new")
	    public String showNewuserForm(Model model) {
		 List <App_Role> listroles= appRoleRepository.findAll();
	      AppUser user = new AppUser();
	         //UserStatus status =user.getUserstatus();
	        model.addAttribute("listroles",listroles );
	        model.addAttribute("user", user);
	        model.addAttribute("status",  UserStatus.values());
	        model.addAttribute("verified",  Userverified.values());
	        return "new_user";
	    }
	 
		@PostMapping(value= "users/add",  params = "Save")
		public String Save( @ModelAttribute("user") AppUser user) {
			appUserRepository.save(user);
			return "redirect:/users";    
		}
		
		@PostMapping(value= "users/add",  params = "Update")
		public String Update( @ModelAttribute("user") AppUser user) {
			appUserRepository.save(user);
			return "redirect:/users";    
		}
		
		@PostMapping(value="users/add",  params="Cancel")
		public String cancel( Model model) {
			AppUser  user = new AppUser();
			model.addAttribute("user",user );
		    return "redirect:/users";
		}
		
		@RequestMapping("users/delete/{id}")
		public String deleteUser(@PathVariable("id")long id)
		{	AppUser user= appUserRepository.findById(id).get(); 
			user.getRoles().removeAll(user.getRoles());
			appUserRepository.delete(user);    
			return "redirect:/users";
		}
		
		@GetMapping("users/edit/{id}")
	    public String EditUser(@PathVariable("id") int id, Model model) {
	        AppUser user = appUserRepository.findById((long) id).get();
	        List <App_Role> listroles= appRoleRepository.findAll();
	        model.addAttribute("user", user);
	        model.addAttribute("listroles",listroles );
	        return "new_user";
	    }
		
	
}
