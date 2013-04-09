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
<script type="text/javascript" src="local/js/question.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		gameController.init(
			'<%= Constants.URL.GAME_SAVE_ACTION %>',
			'<%= Constants.URL.GAME_LIST_PAGE %>'
		);
		questionController.init();
	});
</script>

<!-- html -->
<form id="gameForm" class="form-horizontal">
	<div class="control-group">
		<label for="gameName" class="control-label">Name</label>
		<div class="controls">
			<input id="gameName" placeholder="Name" type="text" />
		</div>
	</div>
	<div class="control-group">
		<label for="gameDate" class="control-label">Date</label>
		<div class="controls">
			<input id="gameDate" placeholder="Date" type="text" />
		</div>
	</div>
	<div class="control-group">
		<div class="controls">
			<a href="javascript:gameController.saveGame();" class="btn">Save</a>
		</div>
	</div>
</form>

<form id="questionForm" class="form-horizontal">
	<div class="control-group">
		<div id="questionContainer" class="controls">
			
		</div>
	</div>
	<div class="control-group">
		<div class="controls">
			<a href="javascript:questionController.addQuestion();" class="btn">Add Question</a>
			<a href="javascript:questionController.saveQuestions();" class="btn">Save</a>
		</div>
	</div>
</form>

<div id="mapContainer"></div>
