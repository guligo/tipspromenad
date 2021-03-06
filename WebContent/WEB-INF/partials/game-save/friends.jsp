<%@ page import="se.tipspromenad.globals.Constants" %>

<%@ include file="../../commons/commons.jsp" %>

<!-- styles -->
<style>
	/**
	 * http://stackoverflow.com/questions/11396478/facebook-login-button-custom-text-reverting
	 */
	a.fb-button {
	    color: #FFF;
	    display: inline-block;
	    text-decoration: none;
	}
	
	.fb-button {
	    background: #5F78AB;
	    background-image: url('http://static.ak.fbcdn.net/rsrc.php/v2/yf/r/S-DbSHszr4D.png');
	    background-repeat: no-repeat;
	    background-position: -1px -133px;
	    border-top: 1px solid #29447E;
	    border-right: 1px solid #29447E;
	    border-bottom: 1px solid #1A356E;
	    border-left: 1px solid #29447E;
	    -webkit-box-shadow: 0 1px 0 rgba(0, 0, 0, 0.1), inset 0 1px 0 #8A9CC2;
	    -moz-box-shadow: 0 1px 0 rgba(0, 0, 0, 0.1), inset 0 1px 0 #8a9cc2;
	    box-shadow: 0 1px 0 rgba(0, 0, 0, 0.1), inset 0 1px 0 #8A9CC2;
	    cursor: pointer;
	    font-family: 'lucida grande', tahoma, verdana, arial, sans-serif;
	    font-size: 12px;
	    font-weight: bold;
	    height: 20px;
	    line-height: 20px;
	    padding: 0px 5px 0px 30px;
	    text-align: left;
	}
</style>

<!-- html -->
<div id="friendsFacebookConnect">
	<p style="width: 750px;">
		<spring:message code="gamesave.friends.description" />
	</p>
	<a class="fb-button" href="javascript:facebookController.showDialogAndConnect();">
		<spring:message code="gamesave.friends.fbconnect.label" />
	</a>
	<br />
</div>

<div id="friends"></div>
<br />

<c:if test="${empty param.id}">
	<a href="javascript:unlockNextTab();" class="btn">
		<spring:message code="gamesave.friends.next.label" />
	</a>
</c:if>
