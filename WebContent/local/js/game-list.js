var gameListController = function() {
	
	var GAME_SAVE_PAGE_URL = null;
	var GAME_LIST_GET_LIST_ACTION_URL = null;
	var GAME_LIST_REMOVE_ACTION_URL = null;
	var GAME_LIST_CONTAINER = null;
	
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
					html += '<th width="25%">Name</th>';
					html += '<th width="25%">Date</th>';
					html += '<th width="25%">State</th>';
					html += '<th width="25%">Action</th>';
				html += '</tr>';
			html += '</thead>';
			html += '<tbody>';
			for (index in games) {
				html += '<tr>'; 
					html += '<td>' + games[index].name + '</td>';
					html += '<td>' + commonUtils.formatDateShort(games[index].date) + '</td>';
					if (games[index].state == 'UNDER_CONSTRUCTION') {
						html += '<td>Game is under construction</td>';
					} else if (games[index].state == 'READY') {
						html += '<td>Game is ready to be played</td>';
					} else if (games[index].state == 'IN_PROGRESS') {
						html += '<td>Game is in progress</td>';
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
		init: function(url1, url2, url3, container) {
			GAME_SAVE_PAGE_URL = url1;
			GAME_LIST_GET_LIST_ACTION_URL = url2;
			GAME_LIST_REMOVE_ACTION_URL = url3;
			GAME_LIST_CONTAINER = container;
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
