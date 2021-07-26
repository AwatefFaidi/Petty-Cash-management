package org.sid.pettycach.service.transaction;

import java.util.Date;

import java.util.Optional;

import org.sid.pettycach.dao.master.AccountRepository;
import org.sid.pettycach.dao.transaction.AdvanceVoucherRepository;
import org.sid.pettycach.dao.transaction.ExpenseVoucherRepository;
import org.sid.pettycach.dao.transaction.ReceiptVoucherRepository;

import org.sid.pettycach.entity.master.Account;
import org.sid.pettycach.entity.master.ExpenseHead;
import org.sid.pettycach.entity.master.Narration;
import org.sid.pettycach.entity.master.Receivers;
import org.sid.pettycach.entity.transaction.AdvanceVoucher;
import org.sid.pettycach.entity.transaction.ExpenseVoucher;
import org.sid.pettycach.entity.transaction.ReceiptVoucher;

import org.sid.pettycach.entity.transaction.VoucherStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TransactionServiceImpl implements TransactionService {

	

	    @Autowired
	    private ExpenseVoucherRepository  expensevoucherRepository;

	    @Autowired
	    private AdvanceVoucherRepository  advancevoucherRepository;

	    @Autowired
	    private ReceiptVoucherRepository  receiptvoucherRepository;

	    @Autowired
	    private  AccountRepository accountRepository;
	
	@Override
	public Account VerifyAccount(Long idaccount) {
		Optional<Account> account=accountRepository.findById(idaccount);
		if(account==null) throw new RuntimeException("Account Not exist");
				return  account.get();
	}
	
	
	@Override
	public void pay(Long id_account, double advance)
	{
		Account account=VerifyAccount(id_account);
		account.setOpeningbalance(account.getOpeningbalance()-advance);
		
		accountRepository.save(account);
	}

	@Override
	public void credit(Long id_account, double amount) {
		
		Account account=VerifyAccount(id_account);
		account.setOpeningbalance(account.getOpeningbalance()+amount);
		
		accountRepository.save(account);
		
	}
	
	@Override
	public void changerturnadvance(Long idadvance, double returnamount) {
		AdvanceVoucher advancevoucher = new AdvanceVoucher ();
		advancevoucher =advancevoucherRepository.findById(idadvance).get();
		advancevoucher.setReturnamount(returnamount);
    	
		advancevoucherRepository.save(advancevoucher);
	}
	
	@Override
	public void returnadvance( Long id_advocuher, double return_advance) {
		AdvanceVoucher advancevoucher =advancevoucherRepository.findById(id_advocuher).get();
		Account account=VerifyAccount(advancevoucher.getAccount().getId());
		account.setOpeningbalance(account.getOpeningbalance()+return_advance);
		accountRepository.save(account);
		advancevoucher.setBalance(advancevoucher.getAmount()-return_advance);
		advancevoucherRepository.save(advancevoucher);
		
		
		
	}
	
	@Override
	public void updateadvance(Long idadvance, Date datecreation,double amount, String remarks, Receivers rec, Account account)
	{
		AdvanceVoucher advancevoucher = new AdvanceVoucher ();
		advancevoucher =advancevoucherRepository.findById(idadvance).get();
		advancevoucher.setDatecreation(datecreation);
		advancevoucher.setAccount(account);
		advancevoucher.setReceiver(rec);
		advancevoucher.setAmount(amount);
		advancevoucher.setRemarks(remarks);
    	
		advancevoucherRepository.save(advancevoucher);
	}
	
	@Override
	public void updatereceipt(Long idreceipt, Date datecreation,double amount, String remarks,  Account account)
	{
		ReceiptVoucher receiptvoucher = new ReceiptVoucher ();
		receiptvoucher =receiptvoucherRepository.findById(idreceipt).get();
		receiptvoucher.setDatecreation(datecreation);
		receiptvoucher.setAccount(account);
		
		receiptvoucher.setAmount(amount);
		receiptvoucher.setRemarks(remarks);
    	
		receiptvoucherRepository.save(receiptvoucher);
	}
	
	public void updateexpense(Long  id,Date date, double amount,double totalamount, String remarks, String voucherremarks, Account account, Receivers receiver, Narration narration,ExpenseHead expensehead)
	{
		ExpenseVoucher expensevoucher = new ExpenseVoucher ();
		expensevoucher =expensevoucherRepository.findById(id).get();
		expensevoucher.setDatecreation(date);
		expensevoucher.setAccount(account);
		expensevoucher.getExpenses().add(expensehead);
		//expensevoucher.setExpenses(expensehead);
		expensevoucher.setNarration(narration);
		expensevoucher.setReceiver(receiver);
		expensevoucher.setTotalamount(totalamount);
		expensevoucher.setVoucherremarks(voucherremarks);
		expensevoucher.setAmount(amount);
		expensevoucher.setRemarks(remarks);
		expensevoucher.setExpensestatus(VoucherStatus.verified);
		expensevoucherRepository.save(expensevoucher);
	}
	

	
	

	
	

}
