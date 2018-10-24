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


	polygons = getZonePolygons();
	displayZonePolygons(polygons);



	function getTrafos(){
		$.ajax({
			url: 'localhost:9000/api/transformadores',
			data: {
				format: 'json'
			},
			error: function() {
				$('#info').html('<p>An error has occurred</p>');
			},
			dataType: 'jsonp',
			success: function(data) {
				displayTrafos(data);
			},
			type: 'GET'
		});
	}
}

