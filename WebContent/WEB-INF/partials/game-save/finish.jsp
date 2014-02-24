<%@ page import="se.tipspromenad.globals.Constants" %>

<%@ include file="../../commons/commons.jsp" %>

<!-- scripts -->
<script type="text/javascript" src="local/js/game-save/finish.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		finishController.init('<%= Constants.URL.GAME_FINALIZE_ACTION %>', '<%= Constants.URL.GAME_DISCARD_ACTION %>', '<%= Constants.URL.GAME_LIST_PAGE %>');
	});
</script>

<!-- html -->
<form class="form-horizontal" style="width: 750px;">
	<div>
		<p>
			<spring:message code="gamesave.finish.description" />
		</p>
	</div>
	<br />
	<div>
		<a href="javascript:finishController.finalize();" class="btn">
			<spring:message code="gamesave.finish.finalize.label" />
		</a>
		<a href="javascript:finishController.draft();" class="btn">
			<spring:message code="gamesave.finish.draft.label" />
		</a>
		<a href="javascript:finishController.discard();" class="btn">
			<spring:message code="gamesave.finish.discard.label" />
		</a>
	</div>
</form>
