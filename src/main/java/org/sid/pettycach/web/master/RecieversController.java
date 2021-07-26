package org.sid.pettycach.web.master;

import java.util.List;

import org.sid.pettycach.dao.master.ReceiversRepository;
import org.sid.pettycach.dao.transaction.*;
import org.sid.pettycach.entity.master.ExpenseHead;
import org.sid.pettycach.entity.master.Narration;
import org.sid.pettycach.entity.master.Receivers;
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
public class RecieversController {
	
	@Autowired
	ReceiversRepository receiversRepository;
	@Autowired
	ExpenseVoucherRepository expensevoucherrepository;
	@Autowired
	AdvanceVoucherRepository advancevoucherrepository;
	
	@GetMapping("/receivers")
	public String showexpenseList(Model model) {
		List<Receivers> receiver=(List<Receivers>) receiversRepository.findAll();
		Receivers  rec = new Receivers();
		model.addAttribute("receiver", rec);
		model.addAttribute("receiverlist",receiver );
	    return "receivers";
	}
	
	@GetMapping("/newreceiver")
	public String newformfornarration(Model model)
	{
		Receivers  rec = new Receivers();
		model.addAttribute("receiver", rec);
		return "new_receiver";
	}
	
	@PostMapping(value= "receiver/add",  params = "New")
	public String New( @ModelAttribute("receiver") Receivers receiver) {
 		return "redirect:/receivers";    
	}
	
	@PostMapping(value= "receiver/add",  params = "Save")
	public String Save( @ModelAttribute("receiver") Receivers receiver) {
		receiversRepository.save(receiver);
		return "redirect:/receivers";    
	}
	
	@PostMapping(value= "receiver/add",  params = "Update")
	public String Update( @ModelAttribute("receiver") Receivers receiver) {
		receiversRepository.save(receiver);
		return "redirect:/receivers";    
	}
	
	@PostMapping(value="receiver/add",  params="Cancel")
	public String cancel( @ModelAttribute("receiver") Receivers receiver) {
		
	    return "redirect:/receivers";
	}
	
	
	@RequestMapping("receiver/delete/{id}")
	public String deleteReceiver(@PathVariable("id")long id)
	{
		 
		Receivers receiver = receiversRepository.findById((long) id).get();
		
        
        if (receiver != advancevoucherrepository.findreceiver(id))
          {
        	
        	receiversRepository.deleteById(id);
    		return "redirect:/receivers";
                  }
        else if (receiver!= expensevoucherrepository.findreceiver(id) )
          {
        	receiversRepository.deleteById(id);
    		return "redirect:/receivers";
                  }
        else
          {

		return "redirect:/receivers";
	     }
	}
	@GetMapping("receiver/edit/{id}")
    public String showUpdatereceiver(@PathVariable("id") long id, Model model) {
        Receivers receiver = receiversRepository.findById( id).orElse(null);
        model.addAttribute("receiver", receiver);
        return "update_receiver";
    }
	

}
