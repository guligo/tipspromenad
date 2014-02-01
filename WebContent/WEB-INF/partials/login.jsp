<%@ include file="../commons/commons.jsp" %>

<%@ page import="org.springframework.security.web.savedrequest.SavedRequest" %>
<%@ page import="org.springframework.security.web.WebAttributes" %>
<%@ page import="se.tipspromenad.globals.Constants" %>

<!-- styles -->
<link href="3p/fancybox/jquery.fancybox-1.3.4.css" rel="stylesheet" type="text/css" media="screen" />

<!-- scripts -->
<script type="text/javascript" src="http://www.google.com/recaptcha/api/js/recaptcha_ajax.js"></script>
<script type="text/javascript" src="3p/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<script type="text/javascript" src="local/js/facebook.js"></script>
<script type="text/javascript" src="local/js/login.js"></script>
<script type="text/javascript">		
	$(document).ready(function() {
		facebookController.init('<%= Constants.URL.HOME_PAGE %>');
		loginController.init(
			'${pageContext.request.contextPath}/j_spring_security_check',
			'<%= Constants.URL.USER_REGISTRATION_ACTION %>',
			'<%= Constants.URL.HOME_PAGE %>',
			'<%= Constants.URL.LOGIN_VERIFY_CAPTCHA %>'
		);
		
		<% if ("true".equals(request.getParameter("showLoginDialog"))) { %>
			loginController.showDialog('<%= ((SavedRequest) session.getAttribute(WebAttributes.SAVED_REQUEST)).getRedirectUrl() %>');
		<% } %>
	});
</script>

<!-- html -->
<a id="linkShowLoginDialog" class="btn btn-info btn-large" href="#loginDialog"></a>

<div style="display: none;">
	<div id="loginDialog">
		<table style="width: 100%;">
			<tr>
				<td style="vertical-align: top;">
					<form class="form-horizontal" style="border-radius: 10px; background-color: #f5f5f5; padding: 20px;">
						<legend>
							Login
						</legend>		
						<div class="control-group">
						    <label class="control-label" for="loginEmail">Email</label>
						    <div class="controls">
						    	<input type="text" id="loginEmail" placeholder="Email">
						    </div>
					    </div>
				   		<div class="control-group">
						    <label class="control-label" for="loginPassword">Password</label>
						    <div class="controls">
						    	<input type="password" id="loginPassword" placeholder="Password">
						    </div>
				   		</div>
				   		<div class="control-group">
						    <div class="controls">
						    	<a class="btn" href="javascript:loginController.doLogin();">
						    		Sign in
						    	</a>
						    	&nbsp;
								<a id="facebookLoginButton" class="fb-button" onclick="facebookController.showDialog();">Log in with FB</a>
						    </div>
						</div>
					</form>
				</td>
				<td style="width: 10px;">
					&nbsp;
				</td>
				<td style="vertical-align: top;">
					<form class="form-horizontal" style="border-radius: 10px; background-color: #f5f5f5; padding: 20px;">
						<legend>
							New to <span style="color: #999999;">tipspromenad.nu</span>? Sign up
						</legend>
						<div id="emailControlGroup" class="control-group">
						    <label class="control-label" for="emailInput">Email</label>
						    <div class="controls">
						    	<input type="text" id="emailInput" placeholder="Email">
						    </div>
					    </div>
					    <div id="nameControlGroup" class="control-group">
						    <label class="control-label" for="nameInput">Name</label>
						    <div class="controls">
						    	<input type="text" id="nameInput" placeholder="Name">
						    </div>
					    </div>
				   		<div id="passwordControlGroup" class="control-group">
						    <label class="control-label" for="passwordInput">Password</label>
						    <div class="controls">
						    	<input type="password" id="passwordInput" placeholder="Password">
						    </div>
				   		</div>
				   		<div id="confirmControlGroup" class="control-group">
						    <label class="control-label" for="confirmInput">Confirm</label>
						    <div class="controls">
						    	<input type="password" id="confirmInput" placeholder="Confirm password">
						    </div>
				   		</div>
						<div class="control-group">
							<label class="control-label" for="confirmInput">Human verification</label>
							<div class="controls">
								<div id="captcha"></div>
						    </div>
				   		</div>
				   		<div class="control-group">
						    <div class="controls">			    	
						    	<a class="btn" href="javascript:loginController.doRegistration();">Register</a>
						    </div>
						</div>
					</form>
				</td>
			</tr>
		</table>
	</div>
</div>
