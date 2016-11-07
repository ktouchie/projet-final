$(document).ready(function(){
	$.datetimepicker.setLocale('en');
	
	$('#startTimePicker').datetimepicker({
		onGenerate:function(ct){
			$(this).find('.xdsoft_date.xdsoft_weekend').addClass('xdsoft_disabled');
		},
		onClose:function(ct){
			var date = $(this).datetimepicker('getValue');
			alert(date.getFullYear());
		},
		format:'D j M, H:i',
		lang:'en',
		allowTimes:['9:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00'],
		minDate: 0
	});
	
	$('#endTimePicker').datetimepicker({
		onGenerate:function(ct){
			$(this).find('.xdsoft_date.xdsoft_weekend').addClass('xdsoft_disabled');
		},
		format:'D j M, H:i',
		lang:'en',
		allowTimes:['9:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00'],
		minDate: 0
	});

});