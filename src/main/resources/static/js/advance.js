$('document').ready(function() {
	
	$('.table #editButton').on('click',function(event){		
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(ReceiptVoucher, status){
			$('#txtAccountEdit').val(ReceiptVoucher.account);
			$('#voucher_numberedit').val(ReceiptVoucher.id);
			//var dob = ReceiptVoucher.date.substr(0,10);
			$('#dateedit').val(ReceiptVoucher.date);
			$('#amountedit').val(ReceiptVoucher.amount);
			$('#remarksedit').val(ReceiptVoucher.remarks);
						
		});			
		$('#receiptedit').modal('open');		
	});
	
	$('.table #verifyButton').on('click',function(event){		
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(receiptvoucher, status){
			$('#account_list').val(receiptvoucher.account);
			$('#voucher_number_verify').val(receiptvoucher.id);
			 
			$('#dateverify').val(receiptvoucher.date);
			$('#amountverify').val(receiptvoucher.amount);
			$('#remarksverify').val(receiptvoucher.remarks);
						
		});			
		$('#receiptverify').modal('open');		
	});
	
	$('.table #deleteButton').on('click',function(event){		
				
		event.preventDefault();
		var href = $(this).attr('href');
		$('#receiptdelete #delRef').attr('href', href);
			
		$('#receiptdelete').modal('open');		
	});
});