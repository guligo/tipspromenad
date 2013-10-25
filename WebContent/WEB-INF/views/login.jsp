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

<!-- scripts -->
<script type="text/javascript" src="http://www.google.com/recaptcha/api/js/recaptcha_ajax.js"></script>
<script type="text/javascript" src="local/js/facebook.js"></script>
<script type="text/javascript" src="local/js/login.js"></script>
<script type="text/javascript">		
	$(document).ready(function() {
		Recaptcha.create("6LeC6eESAAAAAAGbjL53AJyBEGyNIVlPl9tTbr2D", "captcha", {
			theme: "red",
			callback: Recaptcha.focus_response_field
		});
		
		facebookController.init('<%= Constants.URL.HOME_PAGE %>', function() {
			loginController.init(
				'${pageContext.request.contextPath}/j_spring_security_check',
				'<%= Constants.URL.USER_REGISTRATION_ACTION %>',
				'<%= Constants.URL.HOME_PAGE %>'
			);
		});
	});	
</script>

<!-- html -->
<div class="container">
	<div style="float: right;">		
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
				New to <span style="color: #999999;">tipspromenad.se</span>? Sign up
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
</div>
