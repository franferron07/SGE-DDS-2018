$(function () {

	$('#horas_minimas').change(function() {

		var minimas = $(this).val();
		var maximas = $('#horas_maximas').val();
		validarHoras(minimas, maximas);
	});

	$('#horas_maximas').change(function() {

		var maxima = $(this).val();
		var minimas = $('#horas_minimas').val();
		validarHoras(minimas, maximas);
	});

});

function validarHoras(minimo, maximo){
	if(minimo > maximo) {
		alert("Las horas minimas no pueden ser mayores a las maximas!");
	}
}
