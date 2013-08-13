<%@ page import="se.tipspromenad.globals.Constants" %>

<!-- scripts -->
<script type="text/javascript" src="local/js/game-save/friend.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		friendController.init();
	});
</script>

<!-- html -->
<p style="width: 750px;">
	You can login with Facebook to invite your contacts. If you continue without Facebook login, in
	the next step you will receive an event code that you can share with your family and friends!
</p>

<c:if test="${empty param.id}">
	<a href="javascript:unlockNextTab();" class="btn">Next</a>
</c:if>
