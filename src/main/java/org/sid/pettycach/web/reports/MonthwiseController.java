package org.sid.pettycach.web.reports;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
@Transactional
public class MonthwiseController {

	
	@GetMapping("/monthwise")
	public String shownmonthwise(Model model) {
		
	    
	    return "monthwise";
	}
}
