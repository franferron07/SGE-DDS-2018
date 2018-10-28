$( document ).ready(function() {
	mapa = L.map('mapa', {
		center: [-34.598313, -58.463745],
		zoom: 10,  
		minZoom: 4,
		maxZoom:17,
		zoomControl:true 
	});

	L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
		attribution: ''}).addTo(mapa);


	getTrafos();


	//getZonePolygons();
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
		  	L.marker([this.coordenadas.longitud,this.coordenadas.latitud]).addTo(mapa).bindPopup("<p>Transformador "+"id:"+this.id+"</br>"+"Coordenadas: "+this.coordenadas.latitud+", "+this.coordenadas.longitud+"</br>"+"Clientes:"+this.clientes+"</br>"+"Consumo último mes:"+this.consumo+"KW"+"</br>"+"</p>");
		});

	}
}

);