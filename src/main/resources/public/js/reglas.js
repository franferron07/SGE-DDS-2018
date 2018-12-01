

function modificar(id, permiteEdicion = false){

	var ruta = "/sge/cliente/regla/"+id;
	var metodo = "GET";
	if(permiteEdicion) metodo = "PUT";
    $.ajax({
    			type	: metodo,
    			url 	: ruta,
    			dataType: "html",
    	 		success : function(result){
    	 			$(result).appendTo('body').modal();
            		//showInModal("modal",result);
        		}
        	});
}



// this is the id of the form
$("#form_regla").submit(function(e) {

	alert("esta haciendo submit");
    var form = $(this);
    var url = form.attr('action');

    $.ajax({
           type: "POST",
           url: url,
           data: form.serialize(), // serializes the form's elements.
           success: function(data)
           {
              // alert("Dispositivo modificado"); 
              location.reload();
           }
         });

    location.reload();

    e.preventDefault(); 
});





function pedidoBorrar(id){

		if (confirm("¿Está seguro que desea eliminar la regla?")) {
			borrarDispositivo(id);
		}
		return false;
	
}

function borrarDispositivo(id){
	var ruta = "/sge/cliente/regla/"+id;
	var metodo = "DELETE";
    $.ajax({
    			type	: metodo,
    			url 	: ruta,
    			dataType: "html",
    	 		success : function(result){
            		location.reload();
        		}
        	});
}