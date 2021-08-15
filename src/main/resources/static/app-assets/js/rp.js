
//jQuery
function replaceExpenses (html) {
    // Replace the <div  id="expenses"> with a new one returned by server.
    $('#expenses').replaceWith($(html));
}

$('button[name="AddExpense"]').click(function (event) {
    event.preventDefault();
    var expense = $('form ').serialize();
    // Add parameter "AddExpense" to POSTed form data. Button's name and value is
    // POSTed only when clicked. Since "event.preventDefault();" prevents from
    // actual clicking the button, following line will add parameter to form
    // data.
    expense += 'AddExpense';
    console.log(expense);
    
 
    $.post('/expensevoucher/add', expense);
});


$('document').ready(function(){	
	$(".mt-repeater").repeater();

	$(".invoice-repeat-btn").click(function () {
		$('.mt-repeater').repeaterVal()["group-a"].map(function(fields){
	                //fields contain the collection of input fields per row
			console.log(fields["expenses"]);
			console.log(fields["voucherremarks"]);
			console.log(fields["voucheramount"]);
			$('#expenses1').val(fields["expenses"]);
			
		})
	})				
	});	
	
	