package org.sid.pettycach.web.master;


import java.util.List;

import org.sid.pettycach.dao.master.NarrationRepository;
import org.sid.pettycach.dao.transaction.ExpenseVoucherRepository;
import org.sid.pettycach.entity.master.Narration;
import org.sid.pettycach.entity.transaction.ExpenseVoucher;
import org.sid.pettycach.service.master.NarrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;





@Controller
@Transactional
@RequestMapping("/narration")
public class NarrationController {
	@Autowired
	NarrationRepository narrationRespository;
	
	@Autowired 
	NarrationService narrationService;
	@Autowired
	ExpenseVoucherRepository expensevoucherrepository;

	@GetMapping("")
	public String shownarrationList(Model model) {
		List<Narration> narlist=(List<Narration>) narrationRespository.findAll();
	    model.addAttribute("narlist",narlist );

	    //for create new narration
	    Narration  narration = new Narration();
		model.addAttribute("narration", narration);
	    
	    return "narration";
	}
	
	
	@GetMapping("/new")
	public String create(Model model) {
		

	    //for create new narration
	    Narration  narration = new Narration();
		model.addAttribute("narration", narration);
	    
	    return "narration";
	}
	
	@PostMapping(value= "/add",  params = "Save")

	public String Save( @ModelAttribute("nar") Narration nar) {
		narrationRespository.save(nar);
		return "redirect:/narration";    
	}
	
	
	
	
	@PostMapping(value="/add",  params="Cancel")
	public String cancel( Model model) {
	    return "redirect:/narration";
	} 
	
	@PostMapping(value= "/add",  params = "New")

	public String New( @ModelAttribute("nar") Narration nar) {
		return "redirect:/narration";    
	}
	
	
	@PostMapping(value= "/update",  params = "Update")

	public String Update( @ModelAttribute("nar") Narration nar) {
		narrationRespository.save(nar);
		return "redirect:/narration";    
	}
	
	
	
	
	@RequestMapping("delete/{id}")
	public String deleteNarration(@PathVariable("id")int id)
	{    Narration narration = narrationRespository.findById((long) id).orElse(null);
	
	         if (narration!= expensevoucherrepository.findnarration(id) )
	           {
	        	 narrationRespository.deleteById((long) id);
	     		return "redirect:/narration";
	                   }
	         else
	     {
		//narrationRespository.deleteById((long) id);
		return "redirect:/narration";
		}
	    
	}
	
	@GetMapping(path="/{id}")
    public String showUpdateNarration(@PathVariable("id") int id, Model model) {
        Narration narration = narrationRespository.findById((long) id).orElse(null);
          model.addAttribute("narration", narration);
          //return "narration";
        return "update_narration";
    }
	
	
	
	
	@PostMapping(value= "narration/update",  params = "Save")

	public String save( @ModelAttribute("nar") Narration nar) {
		narrationRespository.save(nar);
		return "redirect:/narration";    
	}
}

