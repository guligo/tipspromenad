var gameController = function() {
	
	var GAME_GET_ACTION_URL = null;
	var GAME_SAVE_ACTION_URL = null;
	var GAME_LIST_PAGE_URL = null;
	
	var _id = null;
	
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
	
	function _getGame(callback) {
		$.ajax({
			url: GAME_GET_ACTION_URL.replace('{id}', _id),
			type: 'get',
			contentType: 'application/json',
			dataType: 'json',
			success: function(game) {
				$('#gameName').val(game.name);
				$('#gameDate').datepicker("setValue", game.date);
				if (callback != null) {
					callback(game);
				}
			}
		});
	}
	
	return {
		init: function(id, url1, url2, url3) {
			GAME_GET_ACTION_URL = url1;
			GAME_SAVE_ACTION_URL = url2;
			GAME_LIST_PAGE_URL = url3;
			
			if (id != null) {
				_id = id;
				_getGame();
			} else {
				$('#gameDate').datepicker();
			}
		},
		initMap: function() {
			_initMap($('#mapContainer')[0]);
		},
		removeMarker: function(markerId) {
			_removeMarker(markerId);
		},
		saveGame: function(callback) {
			_saveGame(callback);
		},
		getGameId: function() {
			return _id;
		}
	};
	
}();
