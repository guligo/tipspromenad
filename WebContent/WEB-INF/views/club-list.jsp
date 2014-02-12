<%@ page import="se.tipspromenad.globals.Constants" %>

<%@ include file="../commons/commons.jsp" %>

<!-- styles -->
<style>
	#mapContainer {
		width: 100%;
		height: 450px;
	}

	#mapContainer img {
		max-width: inherit;
	}
	
	#mapContainer .clubWindow th {
		text-align: right;
	}
	
	#mapContainer .clubWindow td {
		text-align: center;
	}
</style>

<!-- scripts -->
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAaLIt-WZIIb1WHNklEUjC6wOMUBXPwDOQ&sensor=true"></script>
<script type="text/javascript" src="local/js/club.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		clubController.init(
			'<%= Constants.URL.CLUB_LIST_ACTION %>', {
				'clubs.map.label.name': '<spring:message code="clubs.map.label.name" />',
			}
		);
	});
</script>

<!-- html -->
<div>
	<spring:message code="clubs.description" />
</div>
<br />
<div id="mapContainer">
	<!-- a place for Google Map -->
</div>
<br />
<div>
	<spring:message code="clubs.contact" />
</div>
