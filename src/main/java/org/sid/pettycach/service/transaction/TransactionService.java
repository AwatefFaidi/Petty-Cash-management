package org.sid.pettycach.service.transaction;

import org.sid.pettycach.entity.master.Account;

public interface TransactionService {
	
	public Account VerifyAccount(Long idaccount);
	
	public void pay(Long idaccount, double advance);
	
	public void  credit(Long idaccount ,double amount);
	
	public void returnadvance(Long idaccount ,double return_advance ); 	
	
	public void expense(Long idaccount,Long id_expensevoucher );
	
	public Double ExpenseUnverified(Long id_expensehead);
	
	public Double ExpenseVoucherVerified(Long id_expensevoucher) ;
	
	public Double AdvanceAmount();

	
	

}
