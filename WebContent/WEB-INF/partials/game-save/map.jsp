<%@ include file="../../commons/commons.jsp" %>
<%@ page import="se.tipspromenad.globals.Constants" %>

<!-- scripts -->
<script type="text/javascript" src="local/js/game-save/map.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		mapController.init(
			'<%= Constants.URL.PLACEMARK_LIST_ACTION %>',
			'<%= Constants.URL.PLACEMARK_SAVE_ACTION %>',
			'<%= Constants.URL.PLACEMARK_REMOVE_ACTION %>'
		);
	});
</script>

<!-- html -->
<div id="mapContainer"></div>
<br />
<div id="questionsContainer"></div>
<c:if test="${empty param.id}">
	<a href="javascript:unlockNextTab();" class="btn">Next</a>
</c:if>

<!-- dialogs -->
<a id="myModalButton" href="#myModal" role="button" class="btn" data-toggle="modal" style="display: none;">
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
