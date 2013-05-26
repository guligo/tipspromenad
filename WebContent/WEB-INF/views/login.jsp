<%@ page import="se.tipspromenad.globals.Constants" %>
<%@ page import="se.tipspromenad.beans.DataTransferBean" %>

<!-- styles -->
<style>
	body {
		background-image: url('local/img/image.jpg');
		background-position: 50%;
	}
</style>

<!-- scripts -->
<script type="text/javascript" src="http://www.google.com/recaptcha/api/js/recaptcha_ajax.js"></script>
<script type="text/javascript" src="local/js/login.js"></script>
<script type="text/javascript">		
	$(document).ready(function() {
		Recaptcha.create("6LeC6eESAAAAAAGbjL53AJyBEGyNIVlPl9tTbr2D", "captcha", {
			theme: "red",
			callback: Recaptcha.focus_response_field
		});
		
		loginController.init(
			'${pageContext.request.contextPath}/j_spring_security_check',
			'<%= Constants.URL.USER_REGISTRATION_ACTION %>',
			'<%= Constants.URL.HOME_PAGE %>'
		);
	});	
</script>

<!-- html -->
<div class="container">
	<div style="float: right;">		
		<form class="form-horizontal" style="border-radius: 10px; background-color: #F5F5F5; padding: 20px;">
			<legend>Login</legend>		
			<div id="usernameLoginControlGroup"  class="control-group">
			    <label class="control-label" for="usernameLoginInput">Username</label>
			    <div class="controls">
			    	<input type="text" id="usernameLoginInput" placeholder="Username">
			    </div>
		    </div>
    		<div class="control-group">
			    <label class="control-label" for="passwordLoginInput">Password</label>
			    <div class="controls">
			    	<input type="password" id="passwordLoginInput" placeholder="Password">
			    </div>
    		</div>
    		<div class="control-group">
			    <div class="controls">
			    	<a class="btn" href="javascript:loginController.doLogin();">
			    		Sign in
			    	</a>
					<div class="fb-login-button"></div>
			    </div>
			</div>
		</form>
		<form class="form-horizontal" style="border-radius: 10px; background-color: #F5F5F5; padding: 20px;">
			<legend>
				New to <span style="color: #999999;">tipspromenad.se</span>? Sign up
			</legend>
			<div id="emailControlGroup" class="control-group">
			    <label class="control-label" for="emailInput">Email</label>
			    <div class="controls">
			    	<input type="text" id="emailInput" placeholder="Email">
			    </div>
		    </div>
		    <div id="usernameControlGroup" class="control-group">
			    <label class="control-label" for="usernameInput">Username</label>
			    <div class="controls">
			    	<input type="text" id="usernameInput" placeholder="Username">
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
</div>
