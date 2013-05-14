var mapController = function() {
	
	var _initialized = null;
	var _placemarks = null;
	var _map = null;
	var _markers = null;
	var _questions = null;
	
	// initialize Google map
	function _initMap(container) {
		if (!_initialized) {
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
			_initialized = true;
		}
	}
	
	// places marker on the Google map
	function _placeMarker(latLng) {
		if (_questions != null && _questions.length > 0) {
			var marker = new google.maps.Marker({
				draggable: true,
			    position: latLng,
			    title: "Hello World!"
			});
			
			var infoWin = new google.maps.InfoWindow({
			    content: _questions.pop().text
			});
			google.maps.event.addListener(marker, 'click', function() {
				infoWin.open(_map, marker);
			});
			marker.setMap(_map);
			
			_markers.push(marker);
			_renderQuestions();
		}
	}
	
	function _renderPlacemarks(placemarks) {
		if (pacemarks != null) {
			for (var i = 0; i < placemarks.length; i++) {
				var marker = new google.maps.Marker({
					draggable: true,
				    position: latLng,
				    title: "Question"
				});
				marker.setMap(_map);
			}
		}
	}
	
	function _renderQuestions() {
		var html = '';
		for (var i = 0; i < _questions.length; i++) {
			html += '<div class="hero-unit">';
				// FIXME: JSON serialization problem here!
				html += _questions[i].text;
			html += '</div>';
		}
		$('#questionsContainer').html(html);
	}
	
	return {
		init: function() {
			_initialized = false;
			_placemarks = [];
		},
		initMap: function() {
			_initMap($('#mapContainer')[0]);
		},
		renderQuestions: function(questions) {
			_questions = [];
			// FIXME: JSON serialization problem here!
			for (var i = 0; i < questions.length; i++) {
				_questions.push(questions[i][0]);
			}
			_renderQuestions();
		}
	};
	
}();
