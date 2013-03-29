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
		gameController.init($('#mapContainer')[0]);
	});
</script>

<!-- html -->
<form class="form-inline">
	<input type="text" placeholder="Name">
	<button class="btn btn-primary">Save</button>
</form>
<div id="mapContainer"></div>
