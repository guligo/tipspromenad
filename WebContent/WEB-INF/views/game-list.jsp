<%@ page import="se.tipspromenad.globals.Constants" %>

<!-- scripts -->
<script type="text/javascript" src="local/js/game-list.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		gameListController.init(
			'<%= Constants.URL.GAME_LIST_GET_LIST %>',
			$('#gameListContainer')
		);
	});
</script>

<!-- html -->
<form>
	<a class="btn btn-primary" href="game">New Game</a>
</form>
<div id="gameListContainer"></div>
