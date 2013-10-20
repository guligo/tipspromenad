<!-- styles -->
<style>
	/* http://www.catswhocode.com/blog/how-to-create-a-bsod-like-404-page */
	#errorDialog {
		display: none;
	}
</style>

<!-- scripts -->
<script type="text/javascript" src="local/js/error.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		errorHandler.init();
	});
</script>

<!-- html -->
<div class="modal" id="errorDialog" tabindex="-1" role="dialog" aria-labelledby="errorDialogLabel" aria-hidden="true">
	<div class="modal-header">
		<a href="javascript:errorHandler.hideDialog();" class="close">×</a>
		<h3 id="errorDialogLabel">Error</h3>
	</div>
	<div class="modal-body">
		An unexpected program behavior - this situation will be reported to team. Sorry for inconvenience! 
	</div>
	<div class="modal-footer">
		<a href="javascript:errorHandler.hideDialog();" class="btn">Close</a>
	</div>
</div>
