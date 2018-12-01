 $( document ).ready(function() {

     var $TABLE = $('#table');
     var $BTN = $('#export-btn');
     var $EXPORT = $('#export');

     $('.table-add').click(function () {
     var $clone = $TABLE.find('tr.hide').clone(true).removeClass('hide table-line');
     $($clone).attr("trCond", "trCond");
     $TABLE.find('table').append($clone);
     });

     $('.table-remove').click(function () {
     $(this).parents('tr').detach();
     });



     // A few jQuery helpers for exporting only
     jQuery.fn.pop = [].pop;
     jQuery.fn.shift = [].shift;

     $BTN.click(function () {
     var $rows = $TABLE.find('tr:not(:hidden)');
     var headers = [];
     var data = [];

     // Get the headers (add special header logic here)
     $($rows.shift()).find('th:not(:empty)').each(function () {
     headers.push($(this).text().toLowerCase());
     });

     // Turn all existing rows into a loopable array
     $rows.each(function () {
     var $td = $(this).find('td');
     var h = {};

     // Use the headers from earlier to name our hash keys
     headers.forEach(function (header, i) {
     h[header] = $td.eq(i).text();
     });

     data.push(h);
     });

     // Output the result
     $EXPORT.text(JSON.stringify(data));
     });



     $('#crear').click(function(e){
        
        
        //realizo validacion de campos numericos
        var form= $('#CreateForm');
         
        
        var items = [];
        var objetoserializado;

        items=serializarCondiciones();
        
        objetoserializado = JSON.stringify(items);
        console.log(objetoserializado);
        //se guarda en el atributo del formulario
        $('#export').val(objetoserializado);      

      });


     $('#actuadores').change(function(){
        
        
        var checkboxes = $('#actuadores').val();
        
        var valor = checkboxes.toString();

        $('#actuadores_string').val( valor );
        console.log( valor);
        console.log( $('#actuadores').val() );

      });


     $('#dispositivos').change(function(){
        
        var checkboxes = $('#dispositivos').val();
        
        var valor = checkboxes.toString();

        $('#dispositivos_string').val( valor );
        console.log( valor);
      });


    /* $('#export-btn').click(function(e){
        
     
        var items = [];
        var objetoserializado;

        items=serializarCondiciones();
        
        objetoserializado = JSON.stringify(items);
        console.log(objetoserializado);
        //se guarda en el atributo del formulario
        $('#export').val(objetoserializado);       
      });*/



     function serializarCondiciones(){
      
        var items= [];
        console.log( $('#tablaCondiciones tbody tr') );
        $('tr[trCond="trCond"]').each(function (i){  //me da vector con todas las filas y las itera con el each (i) 
          
          var fila = $(this); // fila actual 
      
          // creo objeto javascript con los campos con valor por defecto.
          var item =     {"comparador":0,"valorComparable":0 };
          
          // asigno al objeto los datos de la fila
          item.comparador = fila.find('select[tipo-dato="comparador"]').val();
          item.valorComparable= fila.find('input[tipo-dato="valorComparable"]').val();
          //item.datatype= fila.find('select[tipo-dato="datatype"]').val() ;

          
          // agregar item al array
          items.push(item);
     });
      
    return items;
    }





 });