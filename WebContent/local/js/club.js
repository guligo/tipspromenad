var clubController = function() {
	
	var _CLUB_LIST_ACTION_URL = null;
	var _DEFAULT_LAT = 56.160833; 
	var _DEFAULT_LNG = 15.586111;
	
	var _dictionary = null;
	
	function _initUrls(CLUB_LIST_ACTION_URL) {
		_CLUB_LIST_ACTION_URL = CLUB_LIST_ACTION_URL;
	}
	
	function _initTranslations(dictionary) {
		_dictionary = dictionary;
		console.debug('Dictionary = %o', _dictionary);
	}
	
	function _initMap(container) {
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
		});
	}
	
	function _getPosition(callback) {
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(function(position) {
				if (callback != null) {
					callback(new google.maps.LatLng(position.coords.latitude, position.coords.longitude));
				}
			});
		} else {
			if (callback != null) {
				callback(new google.maps.LatLng(_DEFAULT_LAT, _DEFAULT_LNG));
			}
		}
	}
	
	function _renderClubs(map, clubs) {
		if (clubs != null) {
			for (var i = 0; i < clubs.length; i++) {
				var window = new google.maps.InfoWindow({
				    content: _renderClub(clubs[i])
				});
				
				var marker = new google.maps.Marker({
				    position: new google.maps.LatLng(clubs[i].latitude, clubs[i].longitude),
				    title: clubs[i].name,
				});
				marker.setMap(map);
				
				google.maps.event.addListener(marker, 'click', function(event) {
					window.open(map, marker);
				});
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
	
	return {
		init: function(CLUB_LIST_ACTION_URL, dictionary) {
			_initUrls(CLUB_LIST_ACTION_URL);
			_initTranslations(dictionary);
			_initMap($('#mapContainer')[0]);
		},
	};
	
}();
