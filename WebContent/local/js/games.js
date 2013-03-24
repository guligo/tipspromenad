var gamesController = function() {
	
	var GAMES_LIST_GET_URL = null;
	var GAMES_LIST_CONTAINER = null;
	
	function _getGamesList(callback) {
		$.ajax({
			type: 'GET',
			url: GAMES_LIST_GET_URL,
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
	
	function _renderGamesList(games) {
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
					html += '<td>' + games[index].creationDate + '</td>';
					html += '<td>';
						html += '<a href="#"><i class="icon-edit"> </i></a>&nbsp';
						html += '<a href="#"><i class="icon-remove"> </i></a>';
					html += '</td>';
				html += '</tr>';
			}
			html += '</tbody>';
		html += '</table>';
		
		// render HTML
		GAMES_LIST_CONTAINER.html(html);
	}
	
	return {
		init: function(url1, container) {
			GAMES_LIST_GET_URL = url1;
			GAMES_LIST_CONTAINER = container;
			_getGamesList(_renderGamesList);
		}
	};
	
}();
