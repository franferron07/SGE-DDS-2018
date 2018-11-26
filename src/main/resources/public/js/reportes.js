 $( document ).ready(function() {
 	$( function() {
 		$( "#date-picker-desde" ).datepicker({ dateFormat: 'dd-mm-yy 00:00' });
 		$( "#date-picker-hasta" ).datepicker({ dateFormat: 'dd-mm-yy 00:00' });
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
				mostrar_resultado(data);				
			},
			type: 'POST'
		});

 	});


 	function mostrar_resultado(data){

 		$("#resultados").append(data);
 		/*$('#resultados').appendTo('body');*/
 	}


 });