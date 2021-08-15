package org.sid.pettycach;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.sid.pettycach.dao.*;

import org.sid.pettycach.dao.master.*;

import org.sid.pettycach.dao.transaction.*;
import org.sid.pettycach.entity.*;

import org.sid.pettycach.entity.master.*;
import org.sid.pettycach.entity.transaction.*;
import org.sid.pettycach.service.UserService;
import org.sid.pettycach.service.master.NarrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@SpringBootApplication

public class PettyCashApplication  extends SpringBootServletInitializer  {
	
	
	public static void main(String[] args) {
		SpringApplication.run(PettyCashApplication.class, args);
	}

	
	
	@Bean
    CommandLineRunner start(UserService userService,AppRoleRepository roleRepository,
    		AccountRepository accountRepository, AppUserRepository appuserRepository, 
    		NarrationRepository narrationRepository, ExpenseHeadRepository expenseheadRepository, 
    		ReceiversRepository receiversRepository, NarrationService narrationService,
    		  VoucherRepository voucherrepository, ReceiptVoucherRepository receiptvoucherRepository){
        return args->{
        	
        	roleRepository.save(new App_Role(null,"USER"));
        	userService.save(new App_Role(null,"ADMIN"));
        	userService.saveUser("admin","1234","1234");
           userService.saveUser("user1","1234","1234");
           userService.addRoleToUser("user1","USER");
           
           /*
           
            narrationRepository.save(new Narration(null,"Vehicle Maintanence"));
            narrationService.saveNarration( "House Maintanence");
            expenseheadRepository.save(new ExpenseHead(null, "Fuel"));
            receiversRepository.save(new Receivers(null, "Jino Pulickal Mathew"));*/
            
            
        };
    }
    



	@Bean
    BCryptPasswordEncoder getBCPE(){
        return new BCryptPasswordEncoder();
    }
}
