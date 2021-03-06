package org.sid.pettycach.service.master;

import java.util.List;

import org.sid.pettycach.dao.master.NarrationRepository;
import org.sid.pettycach.dao.transaction.ExpenseVoucherRepository;
import org.sid.pettycach.entity.master.Account;
import org.sid.pettycach.entity.master.Narration;
import org.sid.pettycach.entity.transaction.ExpenseVoucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NarrationServiceImpl  implements NarrationService{
	
	@Autowired
	NarrationRepository narrationRepository;
	
	

	@Override
	public void  saveNarration(String namenarration) {
		Narration narration=new Narration();
		narration.setNarration(namenarration);  
        narrationRepository.saveAndFlush(narration);
	}
	
	
	@Override
	public void  deleteNarration(long id ) {
		
		
		
	     
	     
	}
}
