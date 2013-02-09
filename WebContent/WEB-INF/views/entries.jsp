<%@ include file="../commons/commons.jsp" %>

<%@ page import="co.vrings.globals.Constants" %>

<!-- styles -->
<style>
	body {
		background-color: #e1e1e8;
	}
	
	.datepicker {
		z-index: 10000;
	}
	
	#entries .entry {
		margin: 10px 0px;
		padding: 10px;
		background-color: white;
		border-radius: 10px;		
	}
</style>

<!-- scripts -->
<script type="text/javascript" src="local/js/entry-list.js"></script>
<script type="text/javascript" src="3p/flot/jquery.flot.js"></script>
<script type="text/javascript">	
	$(document).ready(function() {
		entryListController.init(
			'<%= Constants.URL.ENTRY_GET_LIST_ACTION %>',
			'<%= Constants.URL.ENTRY_REMOVE_MEASUREMENT_ACTION %>',
			'<%= Constants.URL.ENTRY_REMOVE_ACTION %>'
		);
		
		<c:choose>
			<c:when test="${not empty param['date']}">
				entryListController.refreshEntryList("${param['date']}");
			</c:when>
			<c:otherwise>
				entryListController.refreshEntryList();
			</c:otherwise>
		</c:choose>
	});
</script>

<!-- html -->
<a href="javascript:sportEntryController.showEntryDialog();" class="btn btn-primary">Sport</a>
<a href="javascript:bodyEntryController.showEntryDialog();" class="btn btn-primary">Body</a>
<a href="javascript:sickDayEntryController.showDialog();" class="btn btn-primary">Sick day</a>
<a href="javascript:noteEntryController.showDialog();" class="btn btn-primary">Note</a>

<jsp:include page="../partials/entry-sport.jsp" />
<jsp:include page="../partials/entry-body.jsp" />
<jsp:include page="../partials/entry-sick-day.jsp" />
<jsp:include page="../partials/entry-note.jsp" />

<div id="entries"></div>
