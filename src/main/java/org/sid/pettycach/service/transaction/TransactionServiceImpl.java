package org.sid.pettycach.service.transaction;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.sid.pettycach.dao.master.AccountRepository;
import org.sid.pettycach.dao.transaction.AdvanceVoucherRepository;
import org.sid.pettycach.dao.transaction.ExpenseVoucherRepository;
import org.sid.pettycach.dao.transaction.ReceiptVoucherRepository;
import org.sid.pettycach.dao.transaction.VoucherRepository;
import org.sid.pettycach.entity.master.Account;
import org.sid.pettycach.entity.transaction.ExpenseVoucher;
import org.sid.pettycach.entity.transaction.ReceiptVoucher;
import org.sid.pettycach.entity.transaction.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	   private  VoucherRepository voucherRepository;

	    @Autowired
	    private ExpenseVoucherRepository  expensevoucherRepository;

	    @Autowired
	    private AdvanceVoucherRepository  advancetvoucherRepository;

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
	public void returnadvance(Long id_account, double return_advance) {
		Account account=VerifyAccount(id_account);
		account.setOpeningbalance(account.getOpeningbalance()+return_advance);
		
		accountRepository.save(account);
		
	}
	@Override
	public Double ExpenseUnverified(Long id_expensevoucher) {
		
		//Sum of All expense heads in the voucher
		
		Optional<ExpenseVoucher> expvoucher=expensevoucherRepository.findById(id_expensevoucher);
		//expvoucher.getallexpensehead()
		return null;
	}

	

	@Override
	public Double AdvanceAmount() {
		// TODO Auto-generated method stub
		//Advance Amount Paid – Advance Amount Returned
		return null;
	}

	

	@Override
	public void expense(Long id_account,Long id_expensevoucher ) {
		double ExpenseAmount= ExpenseVoucherVerified( id_expensevoucher);
		Account account=VerifyAccount(id_account);
		account.setOpeningbalance(account.getOpeningbalance()+ExpenseAmount);
		
		accountRepository.save(account);
		
		
	}

	@Override
	public Double ExpenseVoucherVerified(Long id_expensevoucher) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
