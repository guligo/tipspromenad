<%@ include file="../../commons/commons.jsp" %>
<%@ page import="se.tipspromenad.globals.Constants" %>

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
			Game will be created and invitations sent out as soon as you press "Finalize" button. "Draft" button will simply
			save the game for further modifications whereas "Discard" button will delete the game.
		</p>
	</div>
	<br />
	<div>
		<a href="javascript:finishController.finalize();" class="btn">Finalize</a>
		<a href="javascript:finishController.draft();"    class="btn">Draft</a>
		<a href="javascript:finishController.discard();"  class="btn">Discard</a>
	</div>
</form>
