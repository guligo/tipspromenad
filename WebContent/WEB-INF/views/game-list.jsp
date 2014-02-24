<%@ page import="se.tipspromenad.globals.Constants" %>

<%@ include file="../commons/commons.jsp" %>

<!-- scripts -->
<script type="text/javascript" src="local/js/game-list.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		gameListController.init(
			'<%= Constants.URL.GAME_SAVE_PAGE %>',
			'<%= Constants.URL.GAME_LIST_ACTION %>',
			'<%= Constants.URL.GAME_REMOVE_ACTION %>',
			$('#gameListContainer'),
			{
				'gamelist.label.newgame': '<spring:message code="gamelist.label.newgame" />',
				'gamelist.label.name': '<spring:message code="gamelist.label.name" />',
				'gamelist.label.date': '<spring:message code="gamelist.label.date" />',
				'gamelist.label.questions': '<spring:message code="gamelist.label.questions" />',
				'gamelist.label.code': '<spring:message code="gamelist.label.code" />',
				'gamelist.label.state': '<spring:message code="gamelist.label.state" />',
				'gamelist.label.action': '<spring:message code="gamelist.label.action" />'
			}
		);
	});
</script>

<!-- html -->
<form>
	<a class="btn btn-primary" href="<%= Constants.URL.GAME_SAVE_PAGE %>">
		<spring:message code="gamelist.label.newgame" />
	</a>
</form>
<div id="gameListContainer"></div>
