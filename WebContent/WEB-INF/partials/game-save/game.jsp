<%@ page import="se.tipspromenad.globals.Constants" %>

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
</form>

<!-- dialogs -->
<%--
<a href="#myModal" role="button" class="btn" data-toggle="modal">
	Launch demo modal
</a>

<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="myModalLabel">Modal header</h3>
	</div>
	<div class="modal-body">
		<p>One fine body</p>
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
		<button class="btn btn-primary">Save changes</button>
	</div>
</div>
--%>
