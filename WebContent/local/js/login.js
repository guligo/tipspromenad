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
	
	var _fbUserId = null;
	
	function _init() {
		window.fbAsyncInit = function() {
			FB.init({
				appId : '203913813008290', // App ID
				status: true, // check login status
				cookie: true, // enable cookies to allow the server to access the session
				xfbml : true  // parse XFBML
			});
			
			FB.Event.subscribe('auth.authResponseChange', function(response) {
				if (response.status === 'connected') {
					FB.api('/me', function(user) {
						console.log(user.first_name);
						console.log(user.last_name);
						console.log(user.gender);
					});
					FB.api('/me?fields=picture', function(user) {
						console.log(user.picture);
					});
					FB.api('/me/friends', function(response) {
						var friends = response.data;
						if (friends != null) {
							for (var i = 0; i < friends.length; i++) {
								console.log(friends[i]);
							}
						}
					});
			    } else if (response.status === 'not_authorized') {
			    	FB.login();
			    } else {
			    	FB.login();
			    }
			});
		};
		
		(function(d){
			var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
			if (d.getElementById(id)) {return;}
			js = d.createElement('script'); js.id = id; js.async = true;
			js.src = "//connect.facebook.net/en_US/all.js";
			ref.parentNode.insertBefore(js, ref);
		}(document));
	}
	
	function _doLogin() {
		commonUtils.hideError($('#loginEmail'));
		commonUtils.hideError($('#loginPassword'));
		$.ajax({
			url: LOGIN_URL,
			type: 'POST',
			data: {
				j_username: $('#loginEmail').val(),
				j_password: $('#loginPassword').val()
			},
		    success: function(status) {				    	
				if (status == STATUS_OK) {
					$(location).attr('href', CALENDAR_URL);
				} else if (status == STATUS_NOK) {
					commonUtils.showError($('#loginEmail'), 'Wrong email or password');
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
