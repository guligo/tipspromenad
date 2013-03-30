var gameListController = function() {
	
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
					html += '<th>Name</th>';
					html += '<th>Creation date</th>';
					html += '<th>Action</th>';
				html += '</tr>';
			html += '</thead>';
			html += '<tbody>';
			for (index in games) {
				html += '<tr>'; 
					html += '<td>' + games[index].name + '</td>';
					html += '<td>' + commonUtils.formatDate(games[index].creationDate) + '</td>';
					html += '<td>';
						html += '<a href="game"><i class="icon-edit"> </i></a>&nbsp';
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
			type: 'POST',
			url: GAME_LIST_REMOVE_ACTION_URL,
			data: {
				id: id
			},
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
		init: function(url1, url2, container) {
			GAME_LIST_GET_LIST_ACTION_URL = url1;
			GAME_LIST_REMOVE_ACTION_URL = url2;
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
