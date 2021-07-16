package org.sid.pettycach.web.master;

import java.util.List;

import org.sid.pettycach.dao.AppUserRepository;
import org.sid.pettycach.dao.master.AccountRepository;
import org.sid.pettycach.entity.AppUser;
import org.sid.pettycach.entity.App_Role;
import org.sid.pettycach.entity.master.Account;
import org.sid.pettycach.entity.master.Narration;
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
public class AccountController {
	@Autowired 
	AccountRepository accountRepository;
	@Autowired 
	AppUserRepository appuserRepository;
	
	@GetMapping("/accounts")
	public String showaccountList(Model model) {
		// for list account
		List<Account> listaccount=(List<Account>) accountRepository.findAll();
		model.addAttribute("accountlist",listaccount );
		//for create new account 
		Account  account = new Account();
		List <AppUser> listusers= appuserRepository.findAll();
		model.addAttribute("listusers",listusers );
		model.addAttribute("account", account);
	    return "accounts";
	}
	
	
	@PostMapping(value= "account/add",  params = "New")

	public String New( @ModelAttribute("account") Account account) {
		//accountRepository.save(account);
		return "redirect:/accounts";    
	}
	
	@PostMapping(value= "account/add",  params = "Save")

	public String Save( @ModelAttribute("account") Account account) {
		accountRepository.save(account);
		return "redirect:/accounts";    
	}
	
	@PostMapping(value="account/add",  params = "Update")

	public String Update( @ModelAttribute("account") Account account) {
		accountRepository.save(account);
		return "redirect:/accounts";    
	}
	
	@PostMapping(value="account/add",  params="Cancel")
	public String cancel( Model model) {
		
		Account account = new Account();
		model.addAttribute("account",account );
	    return "redirect:/accounts";
	}
	
	@RequestMapping("account/delete/{id}")
	public String deleteaccount(@PathVariable("id")long id)
	{ 
		Account account= accountRepository.findById(id).get(); 
	    account.getUsers().removeAll(account.getUsers());
		accountRepository.delete(account);
		return "redirect:/accounts";
	}
	
	@GetMapping("account/edit/{id}")
    public String showUpdateAccount(@PathVariable("id") long id, Model model) {
        Account account = accountRepository.findById( id).get();
        model.addAttribute("account", account);
        List <AppUser> listusers= appuserRepository.findAll();
        model.addAttribute("listusers",listusers );
        return "accounts";
    }
	/*
	@GetMapping("account/new")
	public String newformforaccount(Model model)
	{ 
		Account  account = new Account();
		model.addAttribute("account", account);
		return "new_account";
	}*/

}
