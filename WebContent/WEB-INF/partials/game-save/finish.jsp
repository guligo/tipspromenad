<%@ include file="../../commons/commons.jsp" %>
<%@ page import="se.tipspromenad.globals.Constants" %>

<!-- scripts -->
<script type="text/javascript" src="local/js/game-save/finish.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		finishController.init();
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
		<a href="#" class="btn">Finalize</a>
		<a href="#" class="btn">Discard</a>
	</div>
</form>
