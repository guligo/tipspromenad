<%@ page import="se.tipspromenad.globals.Constants" %>

<%@ include file="../../commons/commons.jsp" %>

<!-- scripts -->
<script type="text/javascript" src="local/js/game-save/game.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		gameController.init(
			<%= request.getParameter("id") %>,
			'<%= Constants.URL.GAME_GET_ACTION %>',
			'<%= Constants.URL.GAME_SAVE_ACTION %>',
			'<%= Constants.URL.GAME_LIST_PAGE %>'
		);
	});
</script>

<!-- html -->
<form id="gameForm" class="form-horizontal">
	<div class="control-group">
		<label for="gameName" class="control-label">
			<spring:message code="gamesave.game.label.name" />
		</label>
		<div class="controls">
			<input id="gameName" placeholder="Name" type="text" />
		</div>
	</div>
	<div class="control-group">
		<label for="gameDate" class="control-label">
			<spring:message code="gamesave.game.label.date" />
		</label>
		<div class="controls">
			<input id="gameDate" placeholder="Date" type="text" />
		</div>
	</div>
</form>
