$(function(){


     $('input[name="tipo"]').change(function (){

          mostrarTodos();

          $('#dispositivo_detalle').val(-1);

     	if( $("input[name='tipo']:checked").val() == "0" ){
     		/*$('#accionado_div').hide();*/
     		$('#horas_div').show();
               filtrarDispositivos("false");	
     	}
     	else{
     		/*$('#accionado_div').show();*/
     		$('#horas_div').hide();	
               filtrarDispositivos("true");
     	}

     });



     function filtrarDispositivos(discriminador){
          console.log("asd");
          $('select[name="dispositivo_detalle"] option').each(function(){
               var elemento= $(this);
               /* filtro las option por un criterio */
               if( elemento.attr('esInteligente') == discriminador  ){
                    $(this).show();
               } else {
                    $(this).hide();
               }
          });

     }

     function mostrarTodos(){

          $('select[name="dispositivo_detalle"] option').each(function(){
               var elemento= $(this);
                    $(this).show();
          });

     }

      
}); 