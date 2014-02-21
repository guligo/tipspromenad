var clubController = function() {
	
	var _CLUB_LIST_ACTION_URL = null;
	var _DEFAULT_LAT = 56.160833; 
	var _DEFAULT_LNG = 15.586111;
	
	var _dictionary = null;
	var _placemarks = null;
	
	function _initVariables() {
		_placemarks = [];
	}
	
	function _initUrls(CLUB_LIST_ACTION_URL) {
		_CLUB_LIST_ACTION_URL = CLUB_LIST_ACTION_URL;
	}
	
	function _initTranslations(dictionary) {
		_dictionary = dictionary;
		console.debug('CLB dictionary = %o', _dictionary);
	}
	
	function _initMap(container) {
		console.debug('Initializing map');
		_getPosition(function(position) {		
			var options = {
				center: position,
				zoom: 8,
				mapTypeId: google.maps.MapTypeId.ROADMAP
			};
			
			var map = new google.maps.Map(container, options);
			_getClubs(function(clubs) {
				_renderClubs(map, clubs);
			});
			console.debug('Map initialized');
		});
	}
	
	function _getPosition(callback) {
		if (navigator.geolocation) {
			console.debug('Trying to locate user');
			navigator.geolocation.getCurrentPosition(function(position) {
				console.debug('User located');
				if (callback != null) {
					callback(new google.maps.LatLng(position.coords.latitude, position.coords.longitude));
				}
			},
			function() {
				console.debug('User not located, because of timeout. Using defaults');
				if (callback != null) {
					callback(new google.maps.LatLng(_DEFAULT_LAT, _DEFAULT_LNG));
				}
			}, {
				timeout: 5000
			});
		} else {
			console.debug('User not located, because feature is not available. Using defaults');
			if (callback != null) {
				callback(new google.maps.LatLng(_DEFAULT_LAT, _DEFAULT_LNG));
			}
		}
	}
	
	function _renderClubs(map, clubs) {
		if (clubs != null) {
			for (var i = 0; i < clubs.length; i++) {
				_putPlacemark(map, clubs[i]);
			}
		}
	}
	
	function _renderClub(club) {
		var html = '<table class="clubWindow">';
			html += '<tr>';
				// html += '<th>' + _dictionary['clubs.map.label.name'] + ':</th>';
				html += '<td><a href="' + club.url + '" target="_blank">' + club.name + '</a></td>';
			html += '</tr>';
		html += '</table>';
		return html;
	}
	
	function _putPlacemark(map, club) {
		var window = new google.maps.InfoWindow({
		    content: _renderClub(club)
		});
		
		var marker = new google.maps.Marker({
		    position: new google.maps.LatLng(club.latitude, club.longitude),
		    title: club.name,
		    map: map
		});
		marker.window = window;
		marker.club = club;
		
		google.maps.event.addListener(marker, 'click', function() {
			_hideAllWindows();
			marker.window.open(map, marker);
		});
		_placemarks.push(marker);
	}
	
	function _getClubs(callback) {
		$.ajax({
			type: 'GET',
			url: _CLUB_LIST_ACTION_URL,
			success: function(clubs) {
				if (callback != null) {
					callback(clubs);
				}
			},
			error: function(xhr) {
				console.error('Error on getting club list');
			}
		});
	}
	
	function _hideAllWindows() {
		if (_placemarks != null) {
			for (var i = 0; i < _placemarks.length; i++) {
				_placemarks[i].window.close();
			}
		}
	}
	
	function _showInMap(id) {
		if (_placemarks != null) {
			for (var i = 0; i < _placemarks.length; i++) {
				if (_placemarks[i].club.id == id) {
					google.maps.event.trigger(_placemarks[i], 'click');
				}
			}
		}
	}
	
	return {
		init: function(CLUB_LIST_ACTION_URL, dictionary) {
			_initVariables();
			_initUrls(CLUB_LIST_ACTION_URL);
			_initTranslations(dictionary);
			_initMap($('#mapContainer')[0]);
		},
		showInMap: function(id) {
			_showInMap(id);
		}
	};
	
}();
