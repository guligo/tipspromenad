<%@ page import="se.tipspromenad.globals.Constants" %>
<%@ page import="se.tipspromenad.beans.DataTransferBean" %>

<!-- styles -->
<style>
	body {		
		background: url('local/img/image-new-one.jpg') no-repeat center center fixed;
		-webkit-background-size: cover;
		-moz-background-size: cover;
		-o-background-size: cover;
		background-size: cover;
	}
	
	.content {
		visibility: hidden;
		padding: 0px;
	}
</style>

<!-- scripts -->
<script type="text/javascript" src="http://www.google.com/recaptcha/api/js/recaptcha_ajax.js"></script>
<script type="text/javascript" src="local/js/facebook.js"></script>
<script type="text/javascript" src="local/js/login.js"></script>
<script type="text/javascript">		
	$(document).ready(function() {
		Recaptcha.create("6LfXxesSAAAAALkWPknL_5TYVDAKSySk0MLXGeV3", "captcha", {
			theme: "red",
			callback: Recaptcha.focus_response_field
		});
		
		facebookController.init('<%= Constants.URL.HOME_PAGE %>', function() {
			loginController.init(
				'${pageContext.request.contextPath}/j_spring_security_check',
				'<%= Constants.URL.USER_REGISTRATION_ACTION %>',
				'<%= Constants.URL.HOME_PAGE %>',
				'<%= Constants.URL.LOGIN_GET_MY_IP_ACTION %>'
			);
		});
	});	
</script>

<!-- html -->
<div style="visibility: visible;">
	<form class="form-horizontal" style="float: left; margin-right: 10px; border-radius: 10px; background-color: #F5F5F5; padding: 20px;">
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
	<form class="form-horizontal" style="float: right; margin-left: 10px; border-radius: 10px; background-color: #F5F5F5; padding: 20px;">
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
		    <div id="captcha" class="controls">
		    </div>
   		</div>
   		<div class="control-group">
		    <div class="controls">			    	
		    	<a class="btn" href="javascript:loginController.doRegistration();">Register</a>
		    </div>
		</div>
	</form>
</div>
