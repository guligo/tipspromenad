<%@ page import="co.vrings.globals.Constants" %>

<!-- scripts -->
<script type="text/javascript" src="local/js/entry-sick-day.js"></script>
<script>
	$(document).ready(function() {		
		sickDayEntryController.init('<%= Constants.URL.ENTRY_SICK_DAY_CREATE_ACTION %>');
	});
</script>

<!-- html -->
<div class="modal" id="sickDayEntryDialog" tabindex="-1" role="dialog" aria-labelledby="sickDayEntryLabel" aria-hidden="true" style="display: none;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="sickDayEntryLabel">Create sick-day entry</h3>
	</div>
	<div class="modal-body">
		<form class="form-horizontal">
			<div id="dateSickDayEntryControlGroup" class="control-group">
				<label class="control-label" for="dateSickDayEntryInput">Date</label>
				<div class="controls">
					<div class="input-append date" id="dateSickDayEntryInput" data-date="${param['date']}" data-date-format="dd-mm-yyyy">
						<input class="span2" size="16" type="text" value="${param['date']}">
						<span class="add-on"><i class="icon-th"></i></span>
					</div>
				</div>
			</div>
			<div id="noteSickDayEntryControlGroup" class="control-group">
				<label class="control-label" for="noteSickDayEntryInput">Notes</label>
				<div class="controls">
					<textarea rows="3" id="noteSickDayEntryInput" placeholder="Notes"></textarea>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="isPrivateSickDayEntryInput">Private</label>
				<div class="controls">
					<input type="checkbox" id="isPrivateSickDayEntryInput">
				</div>
			</div>
		</form>	
	</div>
	<div class="modal-footer">
		<a href="javascript:sickDayEntryController.hideDialog();" class="btn">Close</a>
		<a href="javascript:sickDayEntryController.createEntry();" class="btn btn-primary">Create entry</a>
	</div>
</div>
