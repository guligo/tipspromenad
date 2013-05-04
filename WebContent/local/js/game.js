var gameController = function() {
	
	var GAME_SAVE_ACTION_URL = null;
	var GAME_LIST_PAGE_URL = null;
	
	var _map = null;
	var _markers = null;
	var _id = null;
	
	// initialize Google map
	function _initMap(container) {
		var options = {
			center: new google.maps.LatLng(-34.397, 150.644),
			zoom: 8,
			mapTypeId: google.maps.MapTypeId.ROADMAP
		};
		_map = new google.maps.Map(container, options);
		_markers = [];
		
		google.maps.event.addListener(_map, 'click', function(event) {
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
		_markers.push(marker);
		marker.setMap(_map);
	}
	
	// removes marker from Google map
	function _removeMarker(markerId) {
		if (markerId < _markers.length) {
			_markers[markerId].setMap(null);
		}
	}
	
	function _saveGame(callback) {
		commonUtils.hideError($('#gameName'));
		commonUtils.hideError($('#gameDate'));
		$.ajax({
			url: GAME_SAVE_ACTION_URL,
			type: 'post',
		    contentType: 'application/json',
		    dataType: 'json',
			data: JSON.stringify({
				id: _id,
				name: $('#gameName').val(),
				date: $('#gameDate').val()
			}),
		    success: function(response) {
		    	if (response.errors == null || response.errors.length == 0) {
		    		_id = response.id;
		    		if (callback != null) {
		    			callback();
		    		}
		    	} else {
		    		if ($.inArray('NAME_EMPTY', response.errors) > -1) {
		    			commonUtils.showError($('#gameName'), 'Name empty');
		    		} if ($.inArray('NAME_TOO_SHORT', response.errors) > -1) {
		    			commonUtils.showError($('#gameName'), 'Name too short');
		    		} else if ($.inArray('NAME_TOO_LONG', response.errors) > -1) {
		    			commonUtils.showError($('#gameName'), 'Name too long');
		    		}
		    		
		    		if ($.inArray('DATE_EMPTY', response.errors) > -1) {
		    			commonUtils.showError($('#gameDate'), 'Date may not be empty');
		    		} else if ($.inArray('DATE_WRONG_FORMAT', response.errors) > -1) {
		    			commonUtils.showError($('#gameDate'), 'Date in wrong format');
		    		}
		    	}
			}
		});
	}
	
	return {
		init: function(url1, url2) {
			GAME_SAVE_ACTION_URL = url1;
			GAME_LIST_PAGE_URL = url2;
			$('#gameDate').datepicker();
		},
		initMap: function() {
			_initMap($('#mapContainer')[0]);
		},
		removeMarker: function(markerId) {
			_removeMarker(markerId);
		},
		saveGame: function(callback) {
			_saveGame(_id, callback);
		},
		getGameId: function() {
			return _id;
		}
	};
	
}();
