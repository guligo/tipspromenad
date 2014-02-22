/**
 * JS component responsible for performing login actions. 
 * 
 * @author guligo
 */
var loginController = function() {
	
	// tipspromenad.nu
	var CAPTCHA_PUBLIC_KEY = '6LfXxesSAAAAALkWPknL_5TYVDAKSySk0MLXGeV3';
	
	// localhost
	// var CAPTCHA_PUBLIC_KEY = '6Ledy-sSAAAAADwUlmDRqZ-ETPjQzSAeEsp3YSnX';
	
	var STATUS_OK  = 0;
	var STATUS_NOK = 1;
	
	var LOGIN_URL        = null;
	var REGISTRATION_URL = null;
	var HOME_URL         = null;
	var CAPTCHA_URL      = null;
	
	var _redirectUrl = null;
	
	function _init() {
		$("#linkShowLoginDialog").fancybox({
			'autoDimensions': false,
			'scrolling': 'no',
			'width': 1000,
			'height': 550,
			'padding': 10
		});
	}
	
	function _doLogin() {
		commonUtils.hideError($('#loginEmail'   ));
		commonUtils.hideError($('#loginPassword'));
		$.ajax({
			url: LOGIN_URL,
			type: 'post',
			data: {
				j_username: $('#loginEmail'   ).val(),
				j_password: $('#loginPassword').val()
			},
		    success: function(status) {				    	
				if (status == STATUS_OK) {
					_hideDialog();
					if (_redirectUrl != null) {
						$(location).attr('href', _redirectUrl);
					} else {
						$(location).attr('href', HOME_URL);
					}
				} else if (status == STATUS_NOK) {
					commonUtils.showError($('#loginEmail'), 'Wrong email or password');
				}
			}
		});
	}
	
	function _initCaptcha() {
		Recaptcha.create(CAPTCHA_PUBLIC_KEY, "captcha", {
			theme: "red",
			callback: Recaptcha.focus_response_field
		});
	}
	
	function _verifyCaptcha(callback) {
	   	$.ajax({
			url: CAPTCHA_URL,
			type: 'post',
			data: {
				challenge: Recaptcha.get_challenge(),
				response : Recaptcha.get_response()
			},
		    success: function(status) {
		    	if (callback != null) {
		    		callback(true);
		    	}
			},
			error: function(xhr) {
				alert(xhr.status);
			}
		});
	}
	
	function _doRegistration() {
		commonUtils.hideError($('#emailInput'   )); commonUtils.hideSuccess($('#emailInput'));
		commonUtils.hideError($('#nameInput'    ));
		commonUtils.hideError($('#passwordInput'));
		commonUtils.hideError($('#confirmInput' ));
		commonUtils.hideError($('#captcha'      ));
		
		_verifyCaptcha(function(status) {
			if (status == false) {
				commonUtils.showError($('#captcha'), 'Wrong captcha value');
				_initCaptcha();
			} else {
				$.ajax({
					type: 'post',
					contentType: 'application/json',
				    dataType: 'json',
					url: REGISTRATION_URL,
					data: JSON.stringify({
						email   : $('#emailInput'   ).val(),
						name    : $('#nameInput'    ).val(),
						password: $('#passwordInput').val(),
						confirm : $('#confirmInput' ).val()
					}),
					success: function(response) {
						if (response.errors == null || response.errors.length == 0) {
							$('#loginEmail'   ).val($('#emailInput'   ).val());
							$('#loginPassword').val($('#passwordInput').val());
							_doLogin();
						} else {
					   		if ($.inArray('EMAIL_TOO_SHORT', response.errors) > -1) {
					   			commonUtils.showError($('#emailInput'), 'Email is too short');
					   		} else if ($.inArray('EMAIL_TOO_LONG', response.errors) > -1) {
					   			commonUtils.showError($('#emailInput'), 'Email is too long');
					   		} else if ($.inArray('EMAIL_EMPTY', response.errors) > -1) {
					   			commonUtils.showError($('#emailInput'), 'Email may not be empty');
					   		} else if ($.inArray('DUBLICATED_USER', response.errors) > -1) {
					   			commonUtils.showError($('#emailInput'), 'User with such email already exist');
					   		} else if ($.inArray('NAME_TOO_SHORT', response.errors) > -1) {
					   			commonUtils.showError($('#nameInput'), 'Name is too short');
					   		} else if ($.inArray('NAME_TOO_LONG', response.errors) > -1) {
					   			commonUtils.showError($('#nameInput'), 'Name is too long');
					   		} else if ($.inArray('NAME_EMPTY', response.errors) > -1) {
					   			commonUtils.showError($('#nameInput'), 'Name may not be empty');
					   		} else if ($.inArray('PASSWORD_TOO_SHORT', response.errors) > -1) {
					   			commonUtils.showError($('#passwordInput'), 'Password is too short');
					   		} else if ($.inArray('PASSWORD_TOO_LONG', response.errors) > -1) {
					   			commonUtils.showError($('#passwordInput'), 'Password is too long');
					   		} else if ($.inArray('PASSWORD_EMPTY', response.errors) > -1) {
					   			commonUtils.showError($('#passwordInput'), 'Password may not be empty');
					   		} else if ($.inArray('PASSWORD_NOT_MATCH', response.errors) > -1) {
					   			commonUtils.showError($('#passwordInput'), 'Passwords do not match');
					   		}
					   	}
					},
					error: function(xhr) {
						errorHandler.handle('Error on performing user registration', xhr);
					}
				});
			}
		});
	}
	
	function _showDialog(redirectUrl) {
		_redirectUrl = redirectUrl;
		
		console.debug('Showing login dialog');
		$("#linkShowLoginDialog").click();
	}
	
	function _hideDialog() {
		console.debug('Hiding login dialog');
		$.fancybox.close();
	}
	
	return {
		init: function(url1, url2, url3, url4) {
			LOGIN_URL        = url1;
			REGISTRATION_URL = url2;
			HOME_URL         = url3;
			CAPTCHA_URL      = url4;
			
			_init();
			_initCaptcha();
		},
		checkLink: function(link) {
			_checkLink(link);
		},
		doLogin: function() {
			_doLogin();
		},
		doRegistration: function() {
			_doRegistration();
		},
		showDialog: function(redirectUrl) {
			_showDialog(redirectUrl);
		},
		hideDialog: function() {
			_hideDialog();
		},
		getRedirectUrl: function() {
			return _redirectUrl;
		}
	};
	
}();
