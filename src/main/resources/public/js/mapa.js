var RED_ZONE=230;
var YELLOW_ZONE=150;
var GREEN_ZONE=80;

var RED_MARKER=230;
var YELLOW_MARKER=150;
var GREEN_MARKER=80;

$( document ).ready(function() {

 	$(document).ajaxStart(function () {
                $(".loading").show();
            });

    $(document).ajaxComplete(function () {
        //$(".loading").hide();
    });

	mapa = L.map('mapa', {
		center: [-34.598313, -58.463745],
		zoom: 11,  
		minZoom: 4,
		maxZoom:17,
		zoomControl:true 
	});

	L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
		attribution: ''}).addTo(mapa);

	getZonePolygons();
	getTrafos();
	
	//displayZonePolygons(polygons);



	function getTrafos(){
		console.log("getTrafos");
		$.ajax({
			url: '/api/transformadores',
			data: {
				format: 'json'
			},
			error: function(error) {
				console.log("error");	
				console.log(error);
			},
			dataType: 'json',
			success: function(data) {
				console.log("success");
				console.log(data);
				displayTrafos(data);
			},
			type: 'GET'
		});
	}

	function displayTrafos(data){
		console.log("displayTrafos");
		//iter trafos
		$.each(data, function() {
			console.log(this.id);

		  	L.circleMarker([this.coordenadas.longitud,this.coordenadas.latitud],{radius:styleRadius(this.consumo),color: styleCMarker(this.consumo)}).addTo(mapa).bindPopup("<p>Transformador "+"id:"+this.id+"</br>"+"Coordenadas: "+this.coordenadas.latitud+", "+this.coordenadas.longitud+"</br>"+"Clientes:"+this.clientes+"</br>"+"Consumo último mes:"+this.consumo+"KW"+"</br>"+"</p>").bringToFront();
		
			function styleRadius(consumo){
				switch(true){
					case (consumo>=RED_MARKER):
						return 9;
						break;
					case (consumo>=YELLOW_MARKER):
						return 7;
						break;
					case (consumo>=GREEN_MARKER):
						return 5;
						break;
					default:
						return 3;
						break;
				}
			}

			function styleCMarker(consumo){
				switch(true){
					case (consumo>=RED_MARKER):
						return 'red';
						break;
					case (consumo>=YELLOW_MARKER):
						return 'yellow';
						break;
					case (consumo>=GREEN_MARKER):
						return 'green';
						break;
					default:
						return 'white';
						break;
				}
			}

		});

	}

	function getZonePolygons(){
		console.log("getZonas");
		$.ajax({
			url: '/api/zonas',
			data: {
				format: 'json'
			},
			error: function(error) {
				console.log("error");	
				console.log(error);
			},
			dataType: 'json',
			success: function(data) {
				console.log("success");
				console.log(data);
				$(".loading").hide();
				displayZonas(data);
			},
			type: 'GET'
		});
	}

	function displayZonas(data){



		console.log("displayZonas");
		//iter
		$.each(data, function() {
			console.log(this.id);
			/*
			$.each(this.coordenadas,function(){
				coordenadas.push([this.longitud,this.latitud]);
			});
			*/
			var coordenadas = JSON.parse(this.coordenadas);
			coordenadas.forEach(function(item,index){item.reverse();});
		  	L.polygon(coordenadas,{color: styleColor(this.consumo)}).addTo(mapa).bindPopup("<p>Zona "+"id:"+this.id+"</br>"+"Consumo último mes:"+this.consumo+"KW"+"</br>"+"</p>").bringToBack();
			function styleColor(consumo){
				switch(true){
					case (consumo>=RED_ZONE):
						return 'red';
						break;
					case (consumo>=YELLOW_ZONE):
						return 'yellow';
						break;
					case (consumo>=GREEN_ZONE):
						return 'green';
						break;
					default:
						return 'blue';
						break;
				}
			}

		});

	}

}

);
