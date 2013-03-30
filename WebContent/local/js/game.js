var gameController = function() {
	
	var GAME_SAVE_ACTION_URL = null;
	var GAME_LIST_PAGE_URL = null;
	
	var map = null;
	var markers = null;
	var id = null;
	
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
	
	function _saveGame(id, name) {
		$.ajax({
			url: GAME_SAVE_ACTION_URL,
			type: 'POST',
			data: {
				id: id,
				name: name
			},
		    success: function(response) {				    	
		    	if (response.errorCodes == null || response.errorCodes.length == 0) {
		    		window.location = GAME_LIST_PAGE_URL;
		    	}
			}
		});
	}
	
	return {
		init: function(url1, url2, container) {
			GAME_SAVE_ACTION_URL = url1;
			GAME_LIST_PAGE_URL = url2;
			_initMap(container);
		},
		removeMarker: function(markerId) {
			_removeMarker(markerId);
		},
		saveGame: function(name) {
			_saveGame(id, name);
		}
	};
	
}();
