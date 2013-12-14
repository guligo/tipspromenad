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
			Invitations have been sent to out all people that you have selected in previous step. Invitations can be
			found on Facebook or in Tipspromenad application and must be confirmed by participants. New members
			can be added by editing the event or by sharing the event's code
			<code id="code" class="label" style="font-size: 15px;"></code>.
		</p>
		<p>
			Finalize creation of a game by pressing "Save"! By discarding the game it will be erased from the system. 
		</p>
	</div>
	<div>
		<a href="javascript:finishController.finalize();" class="btn">Finalize</a>
		<a href="javascript:finishController.draft();"    class="btn">Draft</a>
		<a href="javascript:finishController.discard();"  class="btn">Discard</a>
	</div>
</form>
