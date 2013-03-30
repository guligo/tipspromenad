<%@ page import="se.tipspromenad.globals.Constants" %>

<!-- styles -->
<style>
	#mapContainer {
		width: 100%;
		height: 650px;
	}

	#mapContainer img {
		max-width: inherit;
	}
</style>

<!-- scripts -->
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAaLIt-WZIIb1WHNklEUjC6wOMUBXPwDOQ&sensor=true"></script>
<script type="text/javascript" src="local/js/game.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		gameController.init(
			'<%= Constants.URL.GAME_SAVE %>',
			'<%= Constants.URL.GAME_LIST_PAGE %>',
			$('#mapContainer')[0]
		);
	});
</script>

<!-- html -->
<form class="form-inline">
	<input id="gameName" placeholder="Name" type="text">
	<a href="javascript:gameController.saveGame($('#gameName').val());" class="btn btn-primary">Save</a>
</form>
<div id="mapContainer"></div>
