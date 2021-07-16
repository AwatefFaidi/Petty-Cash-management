package org.sid.pettycach.web.transaction;

import java.util.List;



import org.sid.pettycach.dao.master.*;
import org.sid.pettycach.dao.transaction.*;
import org.sid.pettycach.entity.AppUser;
import org.sid.pettycach.entity.master.*;
import org.sid.pettycach.entity.transaction.*;
import org.sid.pettycach.service.transaction.TransactionService;
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
public class VoucherController {
	@Autowired
   private  VoucherRepository voucherRepository;

    @Autowired
    private ExpenseVoucherRepository  expensevoucherRepository;

    @Autowired
    private AdvanceVoucherRepository  advancevoucherRepository;

    @Autowired
    private ReceiptVoucherRepository  receiptvoucherRepository;

    @Autowired
    private  AccountRepository accountRepository;
    
    @Autowired
    private  ExpenseHeadRepository expenseheadRepository;

    @Autowired
    private  NarrationRepository narrationRepository;

    @Autowired
    private  ReceiversRepository receiverRepository;

    @Autowired
    private  TransactionService transactionservice;
    
    
    
    /******************************** receipt voucher part *************/
	
	@GetMapping("/receiptvoucher")
	public String showreceiptList(Model model) {
		// for list account
		List<ReceiptVoucher> listreceiptvoucher=(List<ReceiptVoucher>)
				receiptvoucherRepository.findAll();
			
		model.addAttribute("listreceiptvoucher",listreceiptvoucher );
	    
		// for create  new receipt voucher
		 
		ReceiptVoucher  receiptvoucher = new ReceiptVoucher();
				List<Account> account= accountRepository.findAll();
		    	model.addAttribute("listaccount",account );
				model.addAttribute("receiptvoucher", receiptvoucher);
			
		return "receiptvoucher";
	    
	}
	
	@PostMapping(value= "receipt/add",  params = "Save")

	public String Save( @ModelAttribute("receiptvoucher") ReceiptVoucher receiptvoucher) {
		receiptvoucherRepository.save(receiptvoucher);
		// call function credit to update opening balance of account
		transactionservice.credit(receiptvoucher.getAccount().getId(),receiptvoucher.getAmount());
		 
		return "redirect:/receiptvoucher";    
	}

	@PostMapping(value= "receipt/add",  params = "Cancel")

	public String Cancel( @ModelAttribute("receiptvoucher") ReceiptVoucher receiptvoucher) {
		
		return "redirect:/receiptvoucher";    
	}
	
	@RequestMapping("receipt/delete/{id}")
	public String deletereceipt(@PathVariable("id")long id)
	{    
		ReceiptVoucher receipt= receiptvoucherRepository.findById(id).get(); 
		receipt.getAccount().getUsers().removeAll(receipt.getAccount().getUsers());
		receiptvoucherRepository.delete(receipt);
		return "redirect:/receiptvoucher";
	}
	
	@GetMapping("receipt/edit/{id}")
    public String showUpdatereceipt(@PathVariable("id") long id, Model model) {
        ReceiptVoucher rcpt=receiptvoucherRepository.findById(id).get();
		
        model.addAttribute("rcpt", rcpt);
        List <Account> listaccount= accountRepository.findAll();
        model.addAttribute("listaccount",listaccount );
        return "redirect:/receiptvoucher";
    }
	/************************* Expense Voucher Part ******************/
	
	@GetMapping("/expensevoucher")
	public String showexpenseList(Model model) {
		// for list account
				List<ExpenseVoucher> listexpensetvoucher=(List<ExpenseVoucher>)
						expensevoucherRepository.findAll();
					
				model.addAttribute("listexpensetvoucher",listexpensetvoucher );
			    
				// for create  new expense voucher
				 
				ExpenseVoucher  expensevoucher = new ExpenseVoucher();
						List<Account> listaccount= accountRepository.findAll();
						List<ExpenseHead> listexpensehead= expenseheadRepository.findAll();
						List<Narration> listnarration= narrationRepository.findAll();
						List<Receivers> listreceiver= receiverRepository.findAll();
				    	model.addAttribute("listaccount",listaccount );
				    	model.addAttribute("listexpensehead",listexpensehead );
				    	model.addAttribute("listnarration",listnarration );
				    	model.addAttribute("listreceiver",listreceiver );
						
				    	model.addAttribute("expensevoucher", expensevoucher);
	
		
		return "expensevoucher";
	    
	}
	
	
	
	/************************Advance Voucher Part ****************************/
	@GetMapping("/advancedetails")
	public String showadvanceList(Model model) {
		// for list advance voucher
		List<AdvanceVoucher> listeadvancevoucher=(List<AdvanceVoucher>)
				advancevoucherRepository.findAll();
			
		model.addAttribute("listeadvancevoucher",listeadvancevoucher );
	    
		// for create  new advance voucher
		 
		AdvanceVoucher  advancevoucher = new AdvanceVoucher();
				List<Account> listaccount= accountRepository.findAll();
				List<Receivers> listreceiver= receiverRepository.findAll();
		    	model.addAttribute("listaccount",listaccount );
		    	model.addAttribute("listreceiver",listreceiver );
				
		    	model.addAttribute("advancevoucher", advancevoucher);


		return "advancedetails";
	    
	}

}
