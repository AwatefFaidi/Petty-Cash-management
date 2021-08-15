package org.sid.pettycach.web.transaction;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.sid.pettycach.dao.master.*;
import org.sid.pettycach.dao.transaction.*;
import org.sid.pettycach.entity.AppUser;
import org.sid.pettycach.entity.master.*;
import org.sid.pettycach.entity.transaction.*;
import org.sid.pettycach.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


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
		// for list all receipt
		
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
		receiptvoucher.setReceiptstatus(VoucherStatus.pending);
		receiptvoucherRepository.save(receiptvoucher);
		
	
		return "redirect:/receiptvoucher";    
	}

	@PostMapping(value= "receipt/add",  params = "Cancel")

	public String Cancel( @ModelAttribute("receiptvoucher") ReceiptVoucher receiptvoucher) {
		
		return "redirect:/receiptvoucher";    
	}
	
	@PostMapping(value="receipt/delete",params = "Delete")
	public String deletereceipt(@RequestParam(name = "id") long id)
	{    
		ReceiptVoucher receipt= receiptvoucherRepository.findById(id).get(); 
		
		receiptvoucherRepository.delete(receipt);
		return "redirect:/receiptvoucher";
	}
	
	@PostMapping(value="receipt/verify",params = "Verify")
	public String verifyreceipt(@RequestParam(name = "id") long id,
			@RequestParam(name = "account") Account account ,
			@RequestParam(name = "amount") double amount,
			  @RequestParam(name = "date") Date date, 
			  @RequestParam(name = "remarks") String remarks ) {
		 ReceiptVoucher receipt=receiptvoucherRepository.findById(id).get();
		 transactionservice.credit(account.getId(),amount);
		 receipt.setReceiptstatus(VoucherStatus.verified);
		 transactionservice.updatereceipt(id, date, amount,  remarks,  account);
		 receiptvoucherRepository.save(receipt);
		
		return "redirect:/receiptvoucher";
	}
	
	@PostMapping(value="receipt/filter",params = "Show")
	public String filterreceipt(Model model,
			@RequestParam(name = "account") Account account ,
			@RequestParam(name = "amount") double amount
			 
			   ) {
		
		
		 List<Voucher> listreceiptvoucher=
					voucherRepository.showvoucher(account, amount);
		 model.addAttribute("listreceiptvoucher",listreceiptvoucher );
		// for create  new receipt voucher
		 
			ReceiptVoucher  receiptvoucher = new ReceiptVoucher();
					
			 
			
					List<Account> acc= accountRepository.findAll();
			    	model.addAttribute("listaccount",acc );
					model.addAttribute("receiptvoucher", receiptvoucher);
		 
		
		return "receiptvoucher";
	}
	
	
	
	
	@PostMapping(value="receipt/update", params="Save")
    public String updatereceipt( @RequestParam(name = "id") long id,
			 @RequestParam(name = "account") Account account ,@RequestParam(name = "amount") double amount,
			  @RequestParam(name = "date") Date date, @RequestParam(name = "remarks") String remarks ) {
		
		
		 transactionservice.updatereceipt(id, date, amount,  remarks,  account);
     
		
		
		
	        return "redirect:/receiptvoucher"; 
	 }
	
	
	@GetMapping("receipt/edit/{id}")
    public String showUpdatereceipt(@PathVariable("id") long id, Model model) {
        ReceiptVoucher receiptvoucher=receiptvoucherRepository.findById(id).get();
   
        model.addAttribute("receiptvoucher", receiptvoucher);
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
	
	
	@PostMapping(value= "expensevoucher/add",params="Saveexpense")

	public String Saveexpense( @ModelAttribute("expensevoucher") ExpenseVoucher expensevoucher,HttpServletRequest request) {
		expensevoucher.setExpensestatus(VoucherStatus.pending);
		//Collection<ExpenseHead> expenses=expenseheadRepository.findAll();
		//expensevoucher.setExpenses(expenses);
		
		
		 /*String[]DetailsAmounts=request.getParameterValues("detailsamount");
		
		 String[]DetailsRemarks=request.getParameterValues("detailsremarks");
		 System.out.println(DetailsAmounts);
		 System.out.println(DetailsRemarks);
		 for (int i=0; i<DetailsAmounts.length;i++)
		 {
			 
			 expensevoucher.getDetails().add(new ExpenseDetails(DetailsRemarks[i],DetailsAmounts[i],DetailsHead[i]));
		 }
		//expensevoucher.getExpenses().add(new ExpenseHead());*/
		expensevoucherRepository.save(expensevoucher);
		
		return "redirect:/expensevoucher";    
	}
	
	
	
	
	
	
	
	@PostMapping(value="expensevoucher/verify",params = "Verify")
	public String verifyexpensevoucher(@RequestParam(name = "id") long id,
			@RequestParam(name = "account") Account account ,
			@RequestParam(name = "receiver") Receivers receiver ,
			@RequestParam(name = "narration") Narration narration ,
			@RequestParam(name = "totalamount") Double totalamount,
			@RequestParam(name = "date") Date date, 
			@RequestParam(name = "remarks") String remarks)
			//@RequestParam(name = "expenses")ExpenseDetails expensehead, 
			//@RequestParam(name = "amount") Double amount,
			//@RequestParam(name = "vremarks") String voucherremarks)
	
	{
		 ExpenseVoucher expense=expensevoucherRepository.findById(id).get();
		 transactionservice.pay(account.getId(),totalamount);
		 
		 transactionservice.updateexpense(id, date, 0.0,totalamount,  remarks, remarks, account, receiver, narration, null);
		 
		
		return "redirect:/expensevoucher";
	}
	
	@PostMapping(value="expensevoucher/update",params = "Update")
	public String editexpensevoucher(@RequestParam(name = "id") long id,
			@RequestParam(name = "account") Account account ,
			@RequestParam(name = "receiver") Receivers receiver ,
			@RequestParam(name = "narration") Narration narration ,
			@RequestParam(name = "amount") double amount,
			@RequestParam(name = "totalamount") double totalamount,
			@RequestParam(name = "expenses")ExpenseDetails expensehead ,
			  @RequestParam(name = "date") Date date, 
			  @RequestParam(name = "vremarks") String voucherremarks,
			  @RequestParam(name = "remarks") String remarks)
	
	   {
		 ExpenseVoucher expense=expensevoucherRepository.findById(id).get();
		 
		 transactionservice.updateexpense(id, date, amount,totalamount,  remarks, voucherremarks, account, receiver, narration, expensehead);
		 
		
		return "redirect:/expensevoucher";
	  }
	
	

	@PostMapping(value="expensevoucher/delete",params = "Delete")
	public String deleteexpense(@RequestParam(name = "id") long id)
	{    
		ExpenseVoucher expense= expensevoucherRepository.findById(id).get(); 
		
		expensevoucherRepository.delete(expense);
		return "redirect:/expensevoucher";
	}
	
	@PostMapping(value= "expensevoucher/add", params="AddExpense")

	public String Savexpense( @ModelAttribute("expensevoucher") ExpenseVoucher expensevoucher,HttpServletRequest request) {
		expensevoucher.setExpensestatus(VoucherStatus.pending);
		return "redirect:/expensevoucher"; 
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
	
	@PostMapping(value= "advance/add",  params = "Save")

	public String Saveadvance( @ModelAttribute("advancevoucher") AdvanceVoucher advancevoucher) {
		
		advancevoucherRepository.save(advancevoucher);
		transactionservice.pay(advancevoucher.getAccount().getId(),advancevoucher.getAmount());
	
		return "redirect:/advancedetails";    
	}

	@PostMapping(value= "advance/add",  params = "Cancel")

	public String Canceladvance( @ModelAttribute("advancevoucher") AdvanceVoucher advancevoucher) {
		
		return "redirect:/advancedetails";    
	}
	
	 @PostMapping(value="advance/return", params = "Save")
	public String savereturnadvancevoucher( @RequestParam(name = "id") long id,@RequestParam(name = "returnamount")double returnamount) {
		 transactionservice.changerturnadvance(id,returnamount);
	        
	    
	        transactionservice.returnadvance( id,returnamount) ;
	        
	        
	        return "redirect:/advancedetails"; 
	 }
	 
	 @PostMapping(value="advance/return", params = "Cancel")
	public String cancelreturnadvancevoucher( Model model) {
	       
	        return "redirect:/advancedetails"; 
	 }
	 
	 @GetMapping("advance/return/{id}")
	public String showreturnadvance(@RequestParam(name = "id") long id, Model model) {
		 AdvanceVoucher advancevoucher=advancevoucherRepository.findById((long) id).get();
		 model.addAttribute("advancevoucher", advancevoucher);
		 //transactionservice.returnadvance( advancevoucher.getId(), returnamount) ;
	        
		 return "redirect:/advancedetails"; 
	 }
	
	@PostMapping(value="advance/delete",params = "Delete")
	public String deleteadvance( @RequestParam(name = "id") long id )
	{    
		AdvanceVoucher advance= advancevoucherRepository.findById(id).get(); 
					//set account_opening balance  after delete advnacevoucher as credit operation
		transactionservice.credit(advance.getAccount().getId(),advance.getAmount());
		
		advancevoucherRepository.delete(advance);
		return "redirect:/advancedetails";
        
	}
	
	 @PostMapping(value="advance/update", params="Save")
	public String update( @RequestParam(name = "id") long id,
			 @RequestParam(name = "account") Account account ,@RequestParam(name = "amount") double amount,
			 @RequestParam(name = "receiver") Receivers receiver,
			 @RequestParam(name = "date") Date date, @RequestParam(name = "remarks") String remarks ) {
		 AdvanceVoucher adv=advancevoucherRepository.findById(id).get();
		//System.out.println(adv.getAmount());
		//System.out.println(adv.getAccount());
		if(adv.getAccount()==account)
		{
			account.setOpeningbalance(account.getOpeningbalance()+adv.getAmount() -amount);
		}
		else {account.setOpeningbalance(account.getOpeningbalance() -amount);
		}
			
			accountRepository.save(account);
		 transactionservice.updateadvance(id, date, amount,  remarks, receiver, account);
      
		
		
		//transactionservice.pay( account.getId(), amount);
	        return "redirect:/advancedetails"; 
	 }
	 
}


/******** second method with js 

@RequestMapping("receiptvoucher/findById") 
@ResponseBody
public Optional<ReceiptVoucher> findById(Long id)
{
	return receiptvoucherRepository.findById(id);
}

@RequestMapping(value="receiptvoucher/update", method = {RequestMethod.PUT, RequestMethod.GET})
public String update(ReceiptVoucher receipt) {
	voucherRepository.save(receipt);	
	return "redirect:/receiptvoucher";
}
****************/
