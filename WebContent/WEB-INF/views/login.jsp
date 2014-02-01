<%@ page import="org.springframework.security.web.savedrequest.SavedRequest" %>
<%@ page import="org.springframework.security.web.WebAttributes" %>
<%@ page import="se.tipspromenad.globals.Constants" %>

<%-- jsp --%>
<% session.setAttribute(Constants.Attributes.SHOW_LOGIN_DIALOG, Boolean.TRUE); %>

<!-- scripts -->
<script type="text/javascript">
	window.location = document.referrer;
</script>
