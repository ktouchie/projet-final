$(document).ready(function(){
	
	$.datetimepicker.setLocale('en');
	
	$('#startTimePicker').datetimepicker({
		// disable reservations on weekends
		onGenerate:function(ct){
			$(this).find('.xdsoft_date.xdsoft_weekend').addClass('xdsoft_disabled');
		},
		// start date is before end date
		onShow:function(ct){
			this.setOptions({
				maxDate:$('#endTimePicker').val()?$('#endTimePicker').val():false
			})
		},
		format:'Y-m-d H',
		lang:'en',
		allowTimes:['9:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00'],
		minDate: 0
	});
	
	$('#editStartTimePicker').datetimepicker({
		// disable reservations on weekends
		onGenerate:function(ct){
			$(this).find('.xdsoft_date.xdsoft_weekend').addClass('xdsoft_disabled');
		},
		// start date is before end date
		onShow:function(ct){
			this.setOptions({
				maxDate:$('#editEndTimePicker').val()?$('#editEndTimePicker').val():false
			})
		},
		format:'Y-m-d H',
		lang:'en',
		allowTimes:['9:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00'],
		minDate: 0
	});
	
	$('#endTimePicker').datetimepicker({
		onGenerate:function(ct){
			$(this).find('.xdsoft_date.xdsoft_weekend').addClass('xdsoft_disabled');
		},
		onShow:function(ct){
		// end date is after start date
			this.setOptions({
				minDate:$('#startTimePicker').val()?$('#startTimePicker').val():false
			});
		},
		format:'Y-m-d H',
		lang:'en',
		allowTimes:['9:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00'],
		minDate: 0
	});
	
	$('#editEndTimePicker').datetimepicker({
		onGenerate:function(ct){
			$(this).find('.xdsoft_date.xdsoft_weekend').addClass('xdsoft_disabled');
		},
		onShow:function(ct){
		// end date is after start date
			this.setOptions({
				minDate:$('#editStartTimePicker').val()?$('#editStartTimePicker').val():false
			});
		},
		format:'Y-m-d H',
		lang:'en',
		allowTimes:['9:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00'],
		minDate: 0
	});

});