package org.sid.pettycach.service.transaction;

import java.util.Date;

import org.sid.pettycach.entity.master.*;



public interface TransactionService {
	
	public Account VerifyAccount(Long idaccount);
	
	
	
	public void pay(Long idaccount, double advance);
	
	public void  credit(Long idaccount ,double amount);
	public void changerturnadvance(Long idadvance, double returnamount);
	public void updateadvance(Long idadvance, Date datecreation,double amount, String remarks, Receivers rec, Account act);
	public void updatereceipt(Long idreceipt, Date datecreation,double amount, String remarks,  Account act);
	public void updateexpense(Long  id,Date date, double amount,double totalamount, String remarks, String voucherremarks, Account account, Receivers receiver, Narration narration,ExpenseHead expensehead);
	void returnadvance( Long id_advocuher, double return_advance);

	
	

}
