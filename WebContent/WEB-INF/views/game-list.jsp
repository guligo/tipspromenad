<%@ page import="se.tipspromenad.globals.Constants" %>

<!-- scripts -->
<script type="text/javascript" src="local/js/game-list.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		gameListController.init(
			'<%= Constants.URL.GAME_LIST_GET_LIST %>',
			$('#gamesTableContainer')
		);
	});
</script>

<!-- html -->
<button class="btn btn-primary" type="button">New Game</button>
<br />
<br />
<div id="gamesTableContainer"></div>
