

function modificar(id, permiteEdicion = false){

	var ruta = "/sge/cliente/dispositivo/"+id;
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



function usuarioGuardar(id = null){
	var datos = recuperarDatosUsuario();
	var ruta = "usuario/"+id;
	var metodo = "POST";
	$.ajax({
    			type	: metodo,
    			url 	: ruta,
    			dataType: "html",
    			data 	: datos,
    	 		success : function(result){
            		showInModal("modal",result);
        		}
        	});
}

function pedidoBorrar(id){

		if (confirm("¿Está seguro que desea eliminar el dispositivo?")) {
			borrarDispositivo(id);
		}
		return false;
	
}

function borrarDispositivo(id){
	var ruta = "/sge/cliente/dispositivo/"+id;
	var metodo = "DELETE";
    $.ajax({
    			type	: metodo,
    			url 	: ruta,
    			dataType: "html",
    	 		success : function(result){
            		showInModal("modal",result);
        		}
        	});
}