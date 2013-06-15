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
