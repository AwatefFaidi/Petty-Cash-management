package org.sid.pettycach.web.reports;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;


import org.sid.pettycach.dao.master.AccountRepository;
import org.sid.pettycach.dao.reports.*;
import org.sid.pettycach.dao.transaction.*;
import org.sid.pettycach.entity.master.*;

import org.sid.pettycach.entity.reports.*;

import org.sid.pettycach.service.reports.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.lowagie.text.DocumentException;

@Controller
@Transactional

public class AccountwiseController {
	
	@Autowired
	   private  ReceiptVoucherRepository receiptvoucherRepository;
	@Autowired
	   private  ExpenseVoucherRepository expensevoucherRepository;
	@Autowired
	   private  AdvanceVoucherRepository advancevoucherRepository;
	@Autowired
    private  AccountRepository accountRepository;
	@Autowired
	   private  AccountwiseRepsoitory accountwiseRepository;
	
	
	@GetMapping("/accountwise")
	public String accountwise(Model model
		 
		   ) {
		List<acountwise> ac= accountwiseRepository.findAll();
		
				List<Account> acc= accountRepository.findAll();
		    	model.addAttribute("listaccount",acc );
				model.addAttribute("listac", ac);
				
	    return "accountwise";
	}
	
	@PostMapping(value="accountwise")
	public String filteraccountwise(Model model,
			@RequestParam(name = "account") Account account 
			 
			   ) {
		List<acountwise> ac= (List<acountwise>)accountwiseRepository.show( account );
		acountwise  acountwise  = new acountwise();
		model.addAttribute("acountwise ", acountwise );
		
		double totalreceipt = 0;
		double totaladvance=0;
		double totalexpense=0;
		if(receiptvoucherRepository.TotalReceipt(account.getId()) != null)
		{
			totalreceipt=receiptvoucherRepository.TotalReceipt(account.getId());
		}
		else {totalreceipt=0;}
		if(advancevoucherRepository.TotalAdvance(account.getId()) != null)
		{
			totaladvance=advancevoucherRepository.TotalAdvance(account.getId());
		}else {totaladvance=0;}
		if(expensevoucherRepository.TotalExpense(account.getId()) != null)
		{
			 totalexpense=expensevoucherRepository.TotalExpense(account.getId());
		}else {totalexpense=0;}
		
		
		double totalbalance=totalreceipt - totalexpense-totaladvance;
		acountwise .setTotalreceipt(totalreceipt);
		acountwise .setTotaladvance(totaladvance);
		acountwise .setTotalexpense(totalexpense);
		acountwise .setTotalbalance(totalbalance);
		acountwise.setManager(account.getUsers().toString());
		acountwise.setAccount(account);
		accountwiseRepository.save(acountwise );
		
		
		return "redirect:/accountwise";
	}
	
	 @GetMapping("/accounts/export/pdf")
	    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
	        response.setContentType("application/pdf");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=account_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue);
	         
	        List<acountwise> ac= accountwiseRepository.findAll();
	         
	        AccountPDFExporter exporter = new AccountPDFExporter(ac);
	        exporter.export(response);
	         
	    }
	 
	 @GetMapping("/accounts/export/excel")
	    public void exportToExcel(HttpServletResponse response) throws IOException {
	        response.setContentType("application/octet-stream");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=account_" + currentDateTime + ".xlsx";
	        response.setHeader(headerKey, headerValue);
	         
	        List<acountwise> ac= accountwiseRepository.findAll();
	         
	        AccountExcelExporter excelExporter = new AccountExcelExporter(ac);
	         
	        excelExporter.export(response);    
	    }  
	
	
	

}
