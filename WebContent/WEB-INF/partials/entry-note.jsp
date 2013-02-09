<%@ page import="co.vrings.globals.Constants" %>
<%@ page import="co.vrings.beans.DataTransferBean" %>

<!-- scripts -->
<script type="text/javascript" src="local/js/entry-note.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		noteEntryController.init('<%= Constants.URL.ENTRY_NOTE_CREATE_ACTION %>');
	});
</script>

<!-- html -->
<div class="modal" id="noteEntryDialog" tabindex="-1" role="dialog" aria-labelledby="noteEntryLabel" aria-hidden="true" style="display: none;">
	<div class="modal-header">
		<a href="javascript:noteEntryController.hideDialog();" class="close">×</a>
		<h3 id="noteEntryLabel">Create note entry</h3>
	</div>
	<div class="modal-body">
		<form class="form-horizontal">
			<div id="dateNoteEntryControlGroup" class="control-group">
				<label class="control-label" for="dateNoteEntryInput">Date</label>
				<div class="controls">
					<div class="input-append date" id="dateNoteEntryInput" data-date="${param['date']}" data-date-format="dd-mm-yyyy">
						<input class="span2" size="16" type="text" value="${param['date']}">
						<span class="add-on"><i class="icon-th"></i></span>
					</div>
				</div>
			</div>
			<div id="noteNoteEntryControlGroup" class="control-group">
				<label class="control-label" for="noteNoteEntryInput">Notes</label>
				<div class="controls">
					<textarea rows="3" id="noteNoteEntryInput" placeholder="Notes"></textarea>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="isPrivateNoteEntryInput">Private</label>
				<div class="controls">
					<input type="checkbox" id="isPrivateNoteEntryInput">
				</div>
			</div>
		</form>
	</div>
	<div class="modal-footer">
		<a href="javascript:noteEntryController.hideDialog();" class="btn">Close</a>
		<a href="javascript:noteEntryController.createEntry();" class="btn btn-primary">Create entry</a>
	</div>
</div>
