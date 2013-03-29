var gameController = function() {
	
	var map = null;
	var markers = null;
	
	// initialize Google map
	function _initMap(container) {
		var options = {
			center: new google.maps.LatLng(-34.397, 150.644),
			zoom: 8,
			mapTypeId: google.maps.MapTypeId.ROADMAP
		};
		map = new google.maps.Map(container, options);
		markers = [];
		
		google.maps.event.addListener(map, 'click', function(event) {
			_placeMarker(event.latLng);
		});
	}
	
	// places marker on the Google map
	function _placeMarker(latLng) {
		var marker = new google.maps.Marker({
			draggable: true,
		    position: latLng,
		    title: "Hello World!"
		});
		
		var infoWin = new google.maps.InfoWindow({
		    content:
		    	'<b>Question?</b><br />' +
		    	'<br />' +
		    	'<span>1. Answer</span><br />' +
		    	'<span>X. Answer</span><br />' +
		    	'<span>2. Answer</span><br />' +
		    	'<br />' +
		    	'<a href="javascript:gameController.removeMarker(' + markers.length + ');">Remove</a>'
		});
		
		google.maps.event.addListener(marker, 'click', function() {
			infoWin.open(map, marker);
		});
		markers.push(marker);
		marker.setMap(map);
	}
	
	// removes marker from Google map
	function _removeMarker(markerId) {
		if (markerId < markers.length) {
			markers[markerId].setMap(null);
		}
	}
	
	return {
		init: function(container) {
			_initMap(container);
		},
		removeMarker: function(markerId) {
			_removeMarker(markerId);
		}
	};
	
}();