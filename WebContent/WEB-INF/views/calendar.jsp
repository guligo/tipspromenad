<%@ page import="co.vrings.globals.Constants" %>

<!-- styles -->
<link href="3p/fullcalendar/css/fullcalendar.css" rel="stylesheet">
<style>
	#calendar {
		margin: 3em 0;
		font-size: 13px;
	}
</style>

<!-- javascript -->
<script type="text/javascript" src="3p/fullcalendar/js/fullcalendar.js"></script>
<script type="text/javascript" src="local/js/calendar.js"></script>
<script type='text/javascript'>
	$(document).ready(function() {		
		calendarController.init('<%= Constants.URL.ENTRY_ALL_ACTION %>', '<%= Constants.URL.ENTRIES_PAGE %>');
	});
</script>

<!-- html -->
<div id="calendar"></div>
