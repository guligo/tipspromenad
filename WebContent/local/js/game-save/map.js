var mapController = function() {
	
	var DEFAULT_LAT = 56.160833; 
	var DEFAULT_LNG = 15.586111;
	var LABELS = ['1.', 'X.', '2.'];
	
	var PLACEMARK_LIST_ACTION_URL   = null;
	var PLACEMARK_SAVE_ACTION_URL   = null;
	var PLACEMARK_REMOVE_ACTION_URL = null;
	
	var _initialized = null;
	var _map         = null;
	var _questions   = null;
	
	// initialize Google map
	function _initMap(container, callback) {
		if (!_initialized) {
			_getPosition(function(position) {
				var options = {
					center: position,
					zoom: 14,
					mapTypeId: google.maps.MapTypeId.ROADMAP
				};
				_map = new google.maps.Map(container, options);
				
				google.maps.event.addListener(_map, 'click', function(event) {
					if (_questions != null && _questions.length > 0) {
						var question = _getCurrentQuestion();
						if (question != null) {
							_removePlacemarks();
							_savePlacemark(gameController.getGameId(), question, event.latLng.lat(), event.latLng.lng(), function (placemark) {
								_getPlacemarks(gameController.getGameId(), function(placemarks) {
									_assignPlacemarksToQuestions(placemarks);
									_renderQuestions();
								});
							});
						}
					}
				});
				_initialized = true;
				
				if (callback != null) {
					callback();
				}
			});
		}
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
					callback(new google.maps.LatLng(DEFAULT_LAT, DEFAULT_LNG));
				}
			}, {
				timeout: 5000
			});
		} else {
			console.debug('User not located, because feature is not available. Using defaults');
			if (callback != null) {
				callback(new google.maps.LatLng(DEFAULT_LAT, DEFAULT_LNG));
			}
		}
	}
	
	function _getPlacemarks(gameId, callback) {
		if (gameId != null) {
			$.ajax({
				url: PLACEMARK_LIST_ACTION_URL.replace('{gameId}', gameId),
				type: 'get',
			    contentType: 'application/json',
			    dataType: 'json',
			    success: function(placemarks) {
			    	if (callback != null) {
			    		callback(placemarks);
			    	}
				}
			});
		}
	}
	
	function _savePlacemark(gameId, question, latitude, longitude, callback) {
		if (question != null) {
			var id = null;
			if (question != null && question.placemark != null) {
				id = question.placemark.id;
			}
			
			$.ajax({
				url: PLACEMARK_SAVE_ACTION_URL,
				type: 'post',
			    contentType: 'application/json',
			    dataType: 'json',
				data: JSON.stringify({
					id: id,
					gameId: gameId,
					questionId: question.id,
					latitude: latitude,
					longitude: longitude
				}),
			    success: function(placemark) {
			    	if (callback != null) {
			    		callback(placemark);
			    	}
				}
			});
		}
	}
	
	function _getQuestionWindow(question) {
		var html = '';
		if (question.type == 'START') {
			// html += '<p style="margin-top: 10px;">Start</p>';
		} else if (question.type == 'FINISH') {
			// html += '<p style="margin-top: 10px;">Finish</p>';
		} else {
			html += '<p style="margin-top: 10px;">';
				html += question.text;
			html += '</p>';
			html += '<p>';
			if (question.answers != null) {
				for (var i = 0; i < question.answers.length; i++) {
					var clazz = '';
					if (question.answers[i].correct == true) {
						clazz = 'badge badge-success';
					} else {
						clazz = 'badge';
					}
					html += '<div style="margin-top: 5px;"><span class="' + clazz + '" style="margin-top: 5px;">' + LABELS[i] + '</span>&nbsp;' + question.answers[i].text + '</div>';
				}
			}
			html += '</p>';
		}
		html += '<div>';
			html += '<a href="javascript:mapController.removePlacemarkFromDatabase(' + question.placemark.id + ');" class="btn">Remove</a>';
		html += '</div>';
		return html;
	}
	
	function _putPlacemark(question) {
		if (question != null && question.placemark != null) {
			var title = 'Question';
			var icon  = 'local/img/pins/pin' + question.sequence + '.png';
			if (question.type == 'START') {
				title = 'Start';
				icon  = 'local/img/pins/pinstart.png';
			} else if (question.type == 'FINISH') {
				title = 'Finish';
				icon  = 'local/img/pins/pinfinish.png';
			}
			
			var marker = new google.maps.Marker({
				draggable: true,
			    position: new google.maps.LatLng(question.placemark.latitude, question.placemark.longitude),
			    title: title,
			    icon: icon
			});
			
			var window = new google.maps.InfoWindow({
			    content: _getQuestionWindow(question)
			});
			
			google.maps.event.addListener(marker, 'click', function(event) {
				window.open(_map, marker);
			});
			google.maps.event.addListener(marker, 'dragend', function(event) {
				_savePlacemark(gameController.getGameId(), marker.question, event.latLng.lat(), event.latLng.lng());
			});
			question.placemark.marker = marker;
			marker.question = question;
			marker.setMap(_map);
		}
	}
	
	function _removePlacemark(question) {
		if (question != null
				&& question.placemark != null
				&& question.placemark.marker != null) {
			question.placemark.marker.setMap(null);
		}
	}
	
	function _removePlacemarks() {
		if (_questions != null) {
			for (var i = 0; i < _questions.length; i++) {
				_removePlacemark(_questions[i]);
			}
		}
	}
	
	function _removePlacemarkFromDatabase(placemarkId, callback) {
		$.ajax({
			url: PLACEMARK_REMOVE_ACTION_URL.replace("{id}", placemarkId),
			type: 'post',
		    contentType: 'application/json',
		    dataType: 'json',
		    success: function() {
		    	if (callback != null) {
		    		callback();
		    	}
			}
		});
	}
	
	function _assignPlacemarksToQuestions(placemarks) {
		if (placemarks != null) {
			for (var i = 0; i < placemarks.length; i++) {
				question = _getQuestion(placemarks[i].question.id);
				if (question != null) {
					if (question.type == 'START') {
						_map.setCenter(new google.maps.LatLng(placemarks[i].latitude, placemarks[i].longitude));
					}
					question.placemark = placemarks[i];
				}
			}
		}
	}
	
	function _getQuestion(id) {
		if (_questions != null) {
			for (var i = 0; i < _questions.length; i++) {
				if (_questions[i].id == id) {
					return _questions[i];
				}
			}
		}
		return null;
	}
	
	function _getCurrentQuestion() {
		var question = null;
		if (_questions != null) {
			for (var i = 0; i < _questions.length; i++) {
				if (_questions[i].placemark == null) {
					question = _questions[i];
					break;
				}
			}
		}
		return question;
	}
	
	function _renderQuestions() {
		var html = '';
		if (_questions != null) {
			for (var i = 0; i < _questions.length; i++) {
				if (_questions[i].type == 'START' && _questions[i].placemark == null) {
					if (html == '') html += '<tr class="info">'; else html += '<tr>';
						html += '<td style="width: 26px; text-align: center;">';
							html += '<img src="local/img/pins/pinstart.png" />';
						html += '</td>';
						html += '<td style="width: 750px; padding-top: 15px;">';
							html += 'Starting point';
						html += '</td>';
					html += '</tr>';
				} else if (_questions[i].type == 'FINISH' && _questions[i].placemark == null) {
					if (html == '') html += '<tr class="info">'; else html += '<tr>';
						html += '<td style="width: 26px; text-align: center;">';
							html += '<img src="local/img/pins/pinfinish.png" />';
						html += '</td>';
						html += '<td style="width: 750px; padding-top: 15px;">';
							html += 'Finish';
						html += '</td>';
					html += '</tr>';
				} else if (_questions[i].placemark == null) {
					if (html == '') html += '<tr class="info">'; else html += '<tr>';
						html += '<td style="width: 26px; text-align: center; vertical-align: middle;">';
							html += '<img src="local/img/pins/pin' +  _questions[i].sequence + '.png" />'; // html += _questions[i].sequence + '.';
						html += '</td>';
						html += '<td style="width: 750px; padding-top: 15px;">';
							html += '<p>';
								html += _questions[i].text;
							html += '</p>';
							html += '<p>';
								if (_questions[i].answers != null) {
									for (var j = 0; j < _questions[i].answers.length; j++) {
										var clazz = '';
										if (_questions[i].answers[j].correct == true) {
											clazz = 'badge badge-success';
										} else {
											clazz = 'badge';
										}
										html += '<span class="' + clazz + '">' + LABELS[j] + '</span>&nbsp;' + _questions[i].answers[j].text + '<br />';
									}
								}
							html += '</p>';
						html += '</td>';
					html += '</tr>';
				} else {
					_putPlacemark(_questions[i]);
				}
			}
		}
		
		if (html != '') {
			html = '<table style="width: 100%;" class="table table-bordered table-hover">'
				+ html;
			html += '</table>';
		}
		$('#questionsContainer').html(html);
	}
	
	function _initData() {
		_removePlacemarks();
		questionController.getQuestions(gameController.getGameId(), function(questions) {
			if (questions != null) {
				_questions = [];
				for (var i = 0; i < questions.length; i++) {
					_questions.push(questions[i]);
				}
			}
			
			_getPlacemarks(gameController.getGameId(), function(placemarks) {
				_assignPlacemarksToQuestions(placemarks);
				_renderQuestions();
			});
		});
	}
	
	return {
		init: function(url1, url2, url3) {
			PLACEMARK_LIST_ACTION_URL = url1;
			PLACEMARK_SAVE_ACTION_URL = url2;
			PLACEMARK_REMOVE_ACTION_URL = url3;
			
			_initialized = false;
			_placemarks = [];
			_combine = false;
		},
		initMap: function(callback) {
			_initMap($('#mapContainer')[0], callback);
		},
		initData: function() {
			_initData();
		},
		removePlacemarkFromDatabase: function(placemarkId) {
			_removePlacemarkFromDatabase(placemarkId, function() {
				_initData();
			});
		}
	};
	
}();
