package org.sid.pettycach.web;

import java.util.List;

import org.sid.pettycach.dao.AppUserRepository;
import org.sid.pettycach.entity.AppUser;
import org.sid.pettycach.service.SecurityService;
import org.sid.pettycach.service.UserService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@Controller
@Transactional
public class AuthController {
	@Autowired
    private UserService userService;
	@Autowired
    private AppUserRepository appUserRepository;
	
	@Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
	
	
	@GetMapping("/registration")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }
        model.addAttribute("userForm", new AppUser());

        return "registration";
	}
	 
	 @PostMapping("/registration")
	    public String registration(@ModelAttribute("userForm") UserRegistration userregistration, BindingResult bindingResult) {
	        //userValidator.validate(userregistration, bindingResult);

	        /*if (bindingResult.hasErrors()) {
	            return "registration";
	        }*/
	        System.out.println(userregistration);
	        userService.saveUser(
	        		userregistration.getUsername(),userregistration.getPassword(),userregistration.getPasswordConfirm());
	    	  
	        securityService.autoLogin(userregistration.getUsername(), userregistration.getPasswordConfirm());

	        return "redirect:/dashboard";
	    }
	
    
	 
    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (securityService.isAuthenticated()) {
            return "redirect:/dashboard";
        }

        if (error != null)
        {
            model.addAttribute("error", "Your username and password is invalid.");
        
        }

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "index";
    }
    
    @GetMapping("/index")
    public String index(Model model, String error, String logout) {
    	 if (securityService.isAuthenticated()) {
             return "redirect:/dashboard";
         }

         if (error != null)
             model.addAttribute("error", "Your username and password is invalid.");

         if (logout != null)
             model.addAttribute("message", "You have been logged out successfully.");

         return "index";
     }
    
    @GetMapping( "/welcome")
    public String welcome(Model model) {
        return "dashboard";
    }
    
    @GetMapping( "/profile")
    public String profile(Model model) {
        return "profile";
    }
    
    @GetMapping({"/", "/dashboard"})
    public String dashboard() {
        return "dashboard";
    }
 
    
}
@Data
class UserRegistration{
    private String username;
    private String password;
    private String PasswordConfirm;

}
