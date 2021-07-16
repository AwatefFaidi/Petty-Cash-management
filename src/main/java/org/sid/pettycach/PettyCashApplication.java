package org.sid.pettycach;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.sid.pettycach.dao.AppRoleRepository;
import org.sid.pettycach.dao.AppUserRepository;
import org.sid.pettycach.dao.master.AccountRepository;
import org.sid.pettycach.dao.master.ExpenseHeadRepository;
import org.sid.pettycach.dao.master.NarrationRepository;
import org.sid.pettycach.dao.master.ReceiversRepository;
import org.sid.pettycach.entity.AppUser;
import org.sid.pettycach.entity.App_Role;
import org.sid.pettycach.entity.UserStatus;
import org.sid.pettycach.entity.Userverified;
import org.sid.pettycach.entity.master.Account;
import org.sid.pettycach.entity.master.ExpenseHead;
import org.sid.pettycach.entity.master.Narration;
import org.sid.pettycach.entity.master.Receivers;
import org.sid.pettycach.service.UserService;
import org.sid.pettycach.service.master.NarrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import antlr.collections.List;

@SpringBootApplication
public class PettyCashApplication  extends SpringBootServletInitializer  {
	/*@Autowired
	private UserService userService;*/
	
	public static void main(String[] args) {
		SpringApplication.run(PettyCashApplication.class, args);
	}

	
	
	@Bean
    CommandLineRunner start(UserService userService,AppRoleRepository roleRepository,AccountRepository accountRepository, AppUserRepository appuserRepository, NarrationRepository narrationRepository, ExpenseHeadRepository expenseheadRepository, ReceiversRepository receiversRepository, NarrationService narrationService){
        return args->{
        	roleRepository.save(new App_Role(null,"USER"));
        	userService.save(new App_Role(null,"ADMIN"));
        	userService.saveUser("admin","1234","1234");
           userService.saveUser("user1","1234","1234");
          userService.addRoleToUser("user1","USER");
            narrationRepository.save(new Narration(null,"Vehicle Maintanence"));
            narrationService.saveNarration( "House Maintanence");
            expenseheadRepository.save(new ExpenseHead(null, "Fuel"));
            receiversRepository.save(new Receivers(null, "Jino Pulickal Mathew"));
        };
    }
    @Bean
    BCryptPasswordEncoder getBCPE(){
        return new BCryptPasswordEncoder();
    }
}
