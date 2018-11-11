function valorDe(unaVariableDelDocumento){
	return $("#"+unaVariableDelDocumento+"").val();
}

function modal_show(unModal){
	$("#"+unModal+"").modal('show');
}

function agregarA(unaVariableDelDocumento, unValor){
	$("#"+unaVariableDelDocumento+"").append(unValor);
}

function vaciar(unaVariableDelDocumento){
	$("#"+unaVariableDelDocumento+"").empty();
}

function showInModal(unModal, unContenido){

	vaciar(unModal);
	agregarA(unModal,unContenido);
	modal_show(unModal);
}

function modificar(id, permiteEdicion = false){
	alert(permiteEdicion);
	var ruta = "/sge/cliente/dispositivo/"+id;
	var metodo = "GET";
	if(permiteEdicion) metodo = "PUT";
    $.ajax({
    			type	: metodo,
    			url 	: ruta,
    			dataType: "html",
    	 		success : function(result){
    	 			$(" <button type='button'>Click Me!</button> ").appendTo('body').modal();
            		//showInModal("modal",result);
        		}
        	});
}

function recuperarDatosUsuario(){
	var datos = {
		nombre 			: valorDe("usuario-nombre"),
		apellido 		: valorDe("usuario-apellido"),
		nombreDeUsuario : valorDe("usuario-username")
	};
	return datos;
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