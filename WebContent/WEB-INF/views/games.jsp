<%@ page import="se.tipspromenad.globals.Constants" %>

<!-- scripts -->
<script type="text/javascript" src="local/js/games.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		gamesController.init(
			'<%= Constants.URL.GAMES_GET_LIST %>',
			$('#gamesTableContainer')
		)
	});
</script>

<!-- html -->
<button class="btn btn-primary" type="button">New Game</button>
<br />
<br />
<div id="gamesTableContainer"></div>
