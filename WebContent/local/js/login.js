/**
 * JS component responsible for performing login actions. 
 * 
 * @author guligo
 */
var loginController = function() {
	
	// constants
	var STATUS_OK = 0;
	var STATUS_NOK = 1;
	
	var LOGIN_URL = null;
	var REGISTRATION_URL = null;
	var CALENDAR_URL = null;
	
	function _init() {
		window.fbAsyncInit = function() {
			// init the FB JS SDK
			FB.init({
				appId     : '203913813008290', // App ID from the App Dashboard
				channelUrl: '//WWW.YOUR_DOMAIN.COM/channel.html', // Channel File for x-domain communication
				status    : true, // check the login status upon init?
				cookie    : true, // set sessions cookies to allow your server to access the session?
				xfbml     : true  // parse XFBML tags on this page?
			});
			
			// additional initialization code such as adding Event Listeners goes here
			FB.getLoginStatus(function(response) {
				// alert(response.status)
				if (response.status === 'connected') {
					// authorized
				} else if (response.status === 'not_authorized') {
					// not authorized
				} else {
					// not logged in
				}
			});
		};
		
		// load the SDK's source asynchronously
		(function(d, debug){
			var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
			if (d.getElementById(id)) { return; }
			js = d.createElement('script'); js.id = id; js.async = true;
			js.src = "//connect.facebook.net/en_US/all" + (debug ? "/debug" : "") + ".js";
			ref.parentNode.insertBefore(js, ref);
		} (document, /*debug*/ false));
	}
	
	function _doLogin() {
		$('#usernameLoginControlGroup').removeClass('error'); $('#usernameLoginControlGroup .controls .help-block').remove();
		$.ajax({
			url: LOGIN_URL,
			type: 'POST',
			data: {
				j_username: $('#usernameLoginInput').val(),
				j_password: $('#passwordLoginInput').val()
			},
		    success: function(status) {				    	
				if (status == STATUS_OK) {
					$(location).attr('href', CALENDAR_URL);
				} else if (status == STATUS_NOK) {
					$('#usernameLoginControlGroup').addClass('error');
					$('#usernameLoginControlGroup .controls').append(
						'<span class="help-block">Wrong username or password</span>'
					);
				}
			}
		});
	}
	
	function _doRegistration() {				
		$('#emailControlGroup')   .removeClass('error')  ; $('#emailControlGroup    .controls .help-block').remove();
		$('#usernameControlGroup').removeClass('error')  ; $('#usernameControlGroup .controls .help-block').remove();
		$('#usernameControlGroup').removeClass('success');
		$('#passwordControlGroup').removeClass('error')  ; $('#passwordControlGroup .controls .help-block').remove();
		$('#confirmControlGroup') .removeClass('error')  ; $('#confirmControlGroup  .controls .help-block').remove();
		
		if ($('#passwordInput').val() == $('#confirmInput') .val()) {		
			$.ajax({
				type: 'post',
				contentType: 'application/json',
			    dataType: 'json',
				url: REGISTRATION_URL,
				data: JSON.stringify({
					email   : $('#emailInput')   .val(),
					username: $('#usernameInput').val(),
					password: $('#passwordInput').val()
				}),
				success: function(registrationBean) {
					if (registrationBean.status == STATUS_NOK) {
						for (var field in registrationBean.errors) {
							$('#' + field + 'ControlGroup').addClass('error');
							$('#' + field + 'ControlGroup .controls').append(
								'<span class="help-block">' +
									registrationBean.errors[field] +
								'</span>'
							);
						}
					} else if (registrationBean.status == STATUS_OK) {
						$('#usernameControlGroup').addClass('success');
						$('#usernameControlGroup .controls').append(
							'<span class="help-block">Now use your username to sign in!</span>'
						);
					}
				},
				error: function(xhr) {
					errorHandler.handle('Error on performing user registration', xhr);
				}
			});
		} else {
			alert('Email too short!');
		}		
	}
	
	return {
		init: function(url1, url2, url3) {
			LOGIN_URL = url1;
			REGISTRATION_URL = url2;
			CALENDAR_URL = url3;
			
			_init();
		},
		doLogin: function() {
			_doLogin();
		},
		doRegistration: function() {
			_doRegistration();
		}
	};
	
}();
