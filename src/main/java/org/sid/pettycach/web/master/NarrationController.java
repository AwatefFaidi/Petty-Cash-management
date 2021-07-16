package org.sid.pettycach.web.master;


import java.util.List;

import org.sid.pettycach.dao.master.NarrationRepository;
import org.sid.pettycach.entity.master.Narration;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;





@Controller
@Transactional
public class NarrationController {
	@Autowired
	NarrationRepository narrationRespository;
	
	@Autowired 
	NarrationService narrationService;
	

	@GetMapping("/narration")
	public String shownarrationList(Model model) {
		List<Narration> narlist=(List<Narration>) narrationRespository.findAll();
	    model.addAttribute("narlist",narlist );
	    //for create new narration
	    Narration  narration = new Narration();
		model.addAttribute("narration", narration);
	    return "narration";
	}
	
	
	@PostMapping(value= "narration/add",  params = "Save")

	public String Save( @ModelAttribute("nar") Narration nar) {
		narrationRespository.save(nar);
		return "redirect:/narration";    
	}
	
	@PostMapping(value="narration/add",  params="Cancel")
	public String cancel( Model model) {
	    return "redirect:/narration";
	} 
	
	@PostMapping(value= "narration/add",  params = "New")

	public String New( @ModelAttribute("nar") Narration nar) {
		return "redirect:/narration";    
	}
	
	
	@PostMapping(value= "narration/add",  params = "Update")

	public String Update( @ModelAttribute("nar") Narration nar) {
		narrationRespository.save(nar);
		return "redirect:/narration";    
	}
	
	
	
	@RequestMapping("narration/delete/{id}")
	public String deleteNarration(@PathVariable("id")int id)
	{
		narrationRespository.deleteById((long) id); 
		return "redirect:/narration";
	}
	
	@GetMapping("narration/edit/{id}")
    public String showUpdateNarration(@PathVariable("id") int id, Model model) {
        Narration narration = narrationRespository.findById((long) id).orElse(null);
          model.addAttribute("narration", narration);
        return "update_narration";
    }
	
	
	@PostMapping("narration/update/{id}")
	public String updateNarration(@PathVariable("id") long id,   
			@ModelAttribute("nar") Narration nar) {
		narrationRespository.save(nar);
		return "redirect:/narration";
        
	}
	
	/*@GetMapping("/new")
	public String newformfornarration(Model model)
	{
		Narration  nar = new Narration();
		model.addAttribute("narra", nar);
		return "new_narration";
	}*/
}

