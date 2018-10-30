$(function(){


     $('input[name="tipo"]').change(function (){

          $('#dispositivo_detalle').val(-1);

     	if( $("input[name='tipo']:checked").val() == "estandar" ){
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

      
}); 