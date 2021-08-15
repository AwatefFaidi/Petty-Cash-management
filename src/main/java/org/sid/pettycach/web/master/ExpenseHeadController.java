package org.sid.pettycach.web.master;

import java.util.List;

import org.sid.pettycach.dao.master.ExpenseHeadRepository;
import org.sid.pettycach.dao.transaction.ExpenseVoucherRepository;
import org.sid.pettycach.entity.master.ExpenseHead;
import org.sid.pettycach.entity.master.Narration;
import org.sid.pettycach.entity.transaction.ExpenseVoucher;
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
public class ExpenseHeadController {
	@Autowired
	ExpenseHeadRepository expenseheadRepository;
	@Autowired
	ExpenseVoucherRepository expensevoucherrepository;
	
	@GetMapping("/expensehead")
	public String showexpenseList(Model model) {
		List<ExpenseHead> expense=(List<ExpenseHead>) expenseheadRepository.findAll();
		ExpenseHead  expensehead= new ExpenseHead();
		model.addAttribute("expenselist",expense );
		model.addAttribute("expense", expensehead);
	    return "expensehead";
	}
	
	@GetMapping("/newexpense")
	public String newformfornarration(Model model)
	{
		ExpenseHead  expensehead= new ExpenseHead();
		model.addAttribute("expense", expensehead);
		return "new_expense";
	}
	
	@PostMapping(value= "expense/add",  params = "New")
	public String New( @ModelAttribute("expense") ExpenseHead expense) {
		
		return "redirect:/expensehead";    
	}
	@PostMapping(value= "expense/add",  params = "Save")
	public String Save( @ModelAttribute("expense") ExpenseHead expense) {
		expenseheadRepository.save(expense);
		return "redirect:/expensehead";    
	}
	
	@PostMapping(value= "expense/add",  params = "Update")
	public String Update( @ModelAttribute("expense") ExpenseHead expense) {
		expenseheadRepository.save(expense);
		return "redirect:/expensehead";    
	}
	
	@PostMapping(value="expense/add",  params="Cancel")
	public String cancel( @ModelAttribute("expense") ExpenseHead expense) {
		return "redirect:/expensehead";
	}
	
	
	@RequestMapping("expense/delete/{id}")
	public String deleteExpense(@PathVariable("id")long id)
	{ 
		ExpenseHead expenseshead = expenseheadRepository.findById((long) id).orElse(null);
		expenseheadRepository.deleteById((long) id);
    	return "redirect:/expensehead";
		
   /* if (expenseshead!= expensevoucherrepository.findexpense(id) )
      {
    	expenseheadRepository.deleteById((long) id);
    	return "redirect:/expensehead";
              }
    else
     {

    	return "redirect:/expensehead";
     }*/
}
	
	
	@GetMapping("expense/edit/{id}")
    public String showUpdateexpense(@PathVariable("id") long id, Model model) {
        ExpenseHead expense = expenseheadRepository.findById( id).orElse(null);
        model.addAttribute("expense", expense);
        return "update_expense";
    }
	
	

	
}
