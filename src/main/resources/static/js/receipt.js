$('document').ready(function() {
	
	$('.table #receiptedit').on('click',function(event){		
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(ReceiptVoucher, status){
			$('#txtAccountEdit').val(ReceiptVoucher.account);
			$('#voucher_numberedit').val(ReceiptVoucher.id);
			var dob = receiptvoucher.date.substr(0,10);
			$('#dateedit').val(rdob);
			$('#amountedit').val(ReceiptVoucher.amount);
			$('#remarksedit').val(ReceiptVoucher.remarks);
						
		});			
		$('#receiptedit').modal('open');		
	});
	
	$('.table #verifyButton').on('click',function(event){		
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(receiptvoucher, status){
			$('#txtAccountverify').val(receiptvoucher.account);
			$('#voucher_numberverify').val(receiptvoucher.id);
			var dob = receiptvoucher.date.substr(0,10);
			$('#dateverify').val(rdob);
			$('#amountverify').val(receiptvoucher.amount);
			$('#remarksverify').val(receiptvoucher.remarks);
						
		});			
		$('#receiptverify').modal();		
	});
});