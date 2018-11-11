 $( document ).ready(function() {
 	$( function() {
 		$( "#date-picker-desde" ).datepicker();
 		$( "#date-picker-hasta" ).datepicker();
 	} );


 	$("#execute").click(function(){
 		var reporte = $("#reporte").val();
 		var desde = $("#date-picker-desde").val()
 		var hasta = $("#date-picker-hasta").val()

 		console.log("Desde: "+desde+" Hasta: "+hasta+" Reporte: "+reporte);



 		$.ajax({
			url: '/api/reportes',
			data: {
				reporte: reporte,
				desde: desde,
				hasta: hasta
			},
			error: function(error) {
				console.log("error");	
				console.log(error);
			},
			dataType: 'json',
			success: function(data) {
				console.log("success");
				console.log(data);
			},
			type: 'POST'
		});

 	});
 });