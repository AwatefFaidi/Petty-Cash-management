package org.sid.pettycach.web.reports;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.sid.pettycach.dao.master.AccountRepository;

import org.sid.pettycach.dao.reports.RunningbalanceRepository;

import org.sid.pettycach.dao.transaction.ExpenseVoucherRepository;
import org.sid.pettycach.dao.transaction.ReceiptVoucherRepository;
import org.sid.pettycach.entity.master.Account;

import org.sid.pettycach.entity.reports.Runningbalance;

import org.sid.pettycach.service.reports.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.lowagie.text.DocumentException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
@Controller
@Transactional
public class RunningbalanceController {
	@Autowired
	   private  ReceiptVoucherRepository receiptvoucherRepository;
	@Autowired
	   private  ExpenseVoucherRepository expensevoucherRepository;
	
	@Autowired
 private  AccountRepository accountRepository;
	
	@Autowired
	   private  RunningbalanceRepository runningbalanceRepository;
	
	@GetMapping("/runningbalance")
	public String runningbalance(Model model) {
		List<Runningbalance> ac= runningbalanceRepository.findAll();
		
		List<Account> acc= accountRepository.findAll();
    	model.addAttribute("listaccount",acc );
		model.addAttribute("listac", ac);
		return "runningbalance";
	}
	@PostMapping(value="/runningbalance")
	public String filteraccountwise(Model model,
			@RequestParam(name = "account") Account account
			   ) {
		runningbalanceRepository.deleteAll();
		
		List<Double> totalreceipt=null;
		List<Double> totalexpense=null;
		List<Date> receiptdate=null;
		List<Date> expensedate=null;
		String receiver=account.getUsers().toString();
	    double op=account.getOpeningbalance();
		
		if(receiptvoucherRepository.TotalReceipt(account.getId()) != null)
		{
			totalreceipt=receiptvoucherRepository.ReceiptAmount(account.getId());
			
		}
		
		
		if(receiptvoucherRepository.Receiptdate(account.getId()) != null)
		{
			receiptdate=receiptvoucherRepository.Receiptdate(account.getId());
		}
		
		if(expensevoucherRepository.ExpenseAmount(account.getId()) != null)
		{
			 totalexpense=expensevoucherRepository.ExpenseAmount(account.getId());
		}
		if(expensevoucherRepository.Expensedate(account.getId()) != null)
		{
			 expensedate=expensevoucherRepository.Expensedate(account.getId());
			 //System.out.println(expensedate);
		}
		
		List<Runningbalance> RunningbalanceList = new ArrayList<>();
		for (int i = 0; i < expensedate.size(); i++) {
			
		RunningbalanceList.add(new Runningbalance(op,op-totalexpense.get(i),totalexpense.get(i),0.0,expensedate.get(i),receiver));
		}
		
		List<Runningbalance> RunningbalanceList1 = new ArrayList<>();
		for (int j = 0; j < receiptdate.size(); j++) {
		RunningbalanceList.add(new Runningbalance( op,op+totalreceipt.get(j),0.0,totalreceipt.get(j),receiptdate.get(j),receiver));
		}
		runningbalanceRepository.saveAll(RunningbalanceList);
		runningbalanceRepository.saveAll(RunningbalanceList1);
		
	
		return "redirect:/runningbalance";
	}
	
	
	
	@GetMapping("/runningbalance/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Runningbalance_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<Runningbalance> ac= runningbalanceRepository.findAll();
         
        RunningbalancePDFExporter exporter = new RunningbalancePDFExporter(ac);
        exporter.export(response);
         
    }
 
 @GetMapping("/runningbalance/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Runningbalance_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<Runningbalance> ac= runningbalanceRepository.findAll();
         
        RunningbalanceExcelExporter excelExporter = new RunningbalanceExcelExporter(ac);
         
        excelExporter.export(response);    
    }  
}
