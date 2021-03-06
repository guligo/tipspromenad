var gameListController = function() {
	
	var GAME_SAVE_PAGE_URL = null;
	var GAME_LIST_GET_LIST_ACTION_URL = null;
	var GAME_LIST_REMOVE_ACTION_URL = null;
	var GAME_LIST_CONTAINER = null;
	
	var _dictionary = null;
	
	function _initTranslations(dictionary) {
		_dictionary = dictionary;
		console.debug('Dictionary = %o', _dictionary);
	}
	
	function _getGameList(callback) {
		$.ajax({
			type: 'GET',
			url: GAME_LIST_GET_LIST_ACTION_URL,
			success: function(games) {
				if (callback != null) {
					callback(games);
				}
			},
			error: function(xhr) {
				alert('Error on getting games list!');
			}
		});
	}
	
	function _renderGameList(games) {
		// construct HTML
		html = '<table style="width: 100%;" class="table table-bordered table-hover">';
			html += '<thead>';
				html += '<tr>';
					html += '<th width="16%">' + _dictionary['gamelist.label.name'] + '</th>';
					html += '<th width="16%">' + _dictionary['gamelist.label.date'] + '</th>';
					html += '<th width="16%">' + _dictionary['gamelist.label.questions'] + '</th>';
					html += '<th width="16%">' + _dictionary['gamelist.label.code'] + '</th>';
					html += '<th width="16%">' + _dictionary['gamelist.label.state'] + '</th>';
					html += '<th width="10%">' + _dictionary['gamelist.label.action'] + '</th>';
				html += '</tr>';
			html += '</thead>';
			html += '<tbody>';
			for (index in games) {
				games[index].questions.length--;
				games[index].questions.length--;
				
				html += '<tr>'; 
					html += '<td>' + games[index].name + '</td>';
					html += '<td>' + commonUtils.formatDateShort(games[index].date) + '</td>';
					html += '<td>' + games[index].questions.length + '</td>';
					if (games[index].state == 'UNDER_CONSTRUCTION') {
						html += '<td>&nbsp;</td>';
						html += '<td><code class="label label-warning" style="font-size: 15px;">Game is under construction</code></td>';
					} else if (games[index].state == 'READY') {
						html += '<td><code class="label" style="font-size: 15px;">' + games[index].code + '</code></td>';
						html += '<td><code class="label label-success" style="font-size: 15px;">Game is ready to be played</code></td>';
					} else if (games[index].state == 'IN_PROGRESS') {
						html += '<td>&nbsp;</td>';
						html += '<td><code class="label label-warning" style="font-size: 15px;">Game is in progress</code></td>';
					}
					html += '<td>';
						html += '<a href="' + GAME_SAVE_PAGE_URL + '?id=' + games[index].id + '"><i class="icon-edit"> </i></a>&nbsp';
						html += '<a href="javascript:gameListController.removeGame(' + games[index].id + ');"><i class="icon-remove"> </i></a>';
					html += '</td>';
				html += '</tr>';
			}
			html += '</tbody>';
		html += '</table>';
		
		// render HTML
		GAME_LIST_CONTAINER.html(html);
	}
	
	function _removeGame(id, callback) {
		$.ajax({
			type: 'DELETE',
			url: GAME_LIST_REMOVE_ACTION_URL.replace('{id}', id),
			success: function() {
				if (callback != null) {
					callback();
				}
			},
			error: function(xhr) {
				alert('Error on removing game!');
			}
		});
	}
	
	return {
		init: function(url1, url2, url3, container, translations) {
			GAME_SAVE_PAGE_URL = url1;
			GAME_LIST_GET_LIST_ACTION_URL = url2;
			GAME_LIST_REMOVE_ACTION_URL = url3;
			GAME_LIST_CONTAINER = container;
			
			_initTranslations(translations);
			_getGameList(_renderGameList);
		},
		removeGame: function(id) {
			var callback = function() {
				_getGameList(_renderGameList);
			};
			_removeGame(id, callback);
		}
	};
	
}();
