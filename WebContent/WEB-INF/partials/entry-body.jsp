<%@ page import="co.vrings.globals.Constants" %>

<!-- scripts -->
<script type="text/javascript" src="local/js/entry-body.js"></script>
<script>
	$(document).ready(function() {		
		bodyEntryController.init('<%= Constants.URL.ENTRY_BODY_CREATE_ACTION %>');
	});	
</script>

<!-- html -->
<div class="modal" id="bodyEntryDialog" tabindex="-1" role="dialog" aria-labelledby="bodyEntryLabel" aria-hidden="true" style="display: none;">
	<div class="modal-header">
		<a href="javascript:bodyEntryController.hideEntryDialog();" class="close">×</a>
		<h3 id="bodyEntryLabel">Create body entry</h3>
	</div>
	<div class="modal-body">
		<form class="form-horizontal">
			<div id="dateBodyEntryControlGroup" class="control-group">
				<label class="control-label" for="dateBodyEntryInput">Date</label>
				<div class="controls">
					<div class="input-append date" id="dateBodyEntryInput" data-date-format="dd-mm-yyyy">
						<input class="span2" size="16" type="text">
						<span class="add-on"><i class="icon-th"></i></span>
					</div>
				</div>
			</div>
			<div id="noteBodyEntryControlGroup" class="control-group">
				<label class="control-label" for="noteBodyEntryInput">Notes</label>
				<div class="controls">
					<textarea rows="3" id="noteBodyEntryInput" placeholder="&lt;notes&gt;"></textarea>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="isPrivateBodyEntryInput">Private</label>
				<div class="controls">
					<input type="checkbox" id="isPrivateBodyEntryInput">
				</div>
			</div>
		</form>	
	</div>
	<div class="modal-footer">
		<a href="javascript:bodyEntryController.hideEntryDialog();" class="btn">Close</a>
		<a href="javascript:bodyEntryController.createEntry();" class="btn btn-primary">Create entry</a>
	</div>
</div>
