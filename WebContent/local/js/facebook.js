var facebookController = function() {
	
	var APP_ID     = '${fb.appId}';
	var APP_SECRET = '${fb.appSecret}';
	
	var HOME_PAGE_URL = null;
	
	var _userId = null;
	
	function _init(callback) {
		window.fbAsyncInit = function() {
			FB.init({
				appId : APP_ID,
				status: true,
				cookie: true,
				xfbml : true
			});
			
			FB.Event.subscribe('auth.authResponseChange', function(response) {
				if (response.status === 'connected') {
					console.debug('User is connected with FB');
					_userId = response.authResponse.userID;
			    } else {
			    	FB.login(function(resonse) {
			    		console.log(response);
			    	}, {perms: 'manage_notifications,email,user_likes'});
			    }
				if (callback != null) {
					console.debug('Triggering FB callback function');
					callback();
				} else {
					console.debug('FB callback function is not present');
				}
			});
		};
		
		(function(d) {
			var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
			if (d.getElementById(id)) {return;}
			js = d.createElement('script'); js.id = id; js.async = true;
			js.src = "//connect.facebook.net/en_US/all.js";
			ref.parentNode.insertBefore(js, ref);
		} (document));
		console.debug('Qutting FB controller init function');
	}
	
	function _showDialog(callback) {
		FB.login(function(response) {
			_userId = response.authResponse.userID;
			if (callback != null) {
				if (response.authResponse) {
					callback(response.authResponse);
				} else {
					console.log('User cancelled authentication request');
				}
			}
		}, {scope: 'manage_notifications, email, user_likes'});
	}
	
	function _foobar(response) {
		log.console(response);
	}
	
	function _getUserId() {
		return _userId;
	}
	
	function _getFriends(callback) {
		FB.api('/me/friends?fields=id,name,picture', function(response) {
			var friends = response.data;
			if (callback != null) {
				callback(friends);
			}
		});
	}
	
	function _sendNotification(userId, text, callback) {
		_getAppAccessToken(function(appAccessToken) {
			FB.api('/' + userId + '/notifications', 'post', {access_token: appAccessToken, template: text}, function(response) {
				var friends = response.data;
				if (callback != null) {
					callback(friends);
				}
			});
		});
	}
	
	function _getAppAccessToken(callback) {
		$.ajax({
			url: "https://graph.facebook.com/oauth/access_token",
			data: {
				client_id: APP_ID,
				client_secret: APP_SECRET,
				grant_type: 'client_credentials'
			},
			success: function (response) {
				// returns something like access_token=XYZ
				var appAccessToken = response.replace('access_token=', '');
				if (callback != null) {
					callback(appAccessToken);
				}
			}
		});
	}
	
	function _verifyAccessToken(accessToken) {
		$.ajax({
			url: 'login/verify',
			type: 'post',
			data: {
				accessToken: accessToken
			},
			success: function(response) {			
				$.ajax({
					url: 'j_spring_security_check',
					type: 'POST',
					data: {
						j_username: response.email,
						j_password: response.password
					},
				    success: function(status) {
				    	if (loginController.getRedirectUrl() != null) {
				    		$(location).attr('href', loginController.getRedirectUrl());
				    	} else {
				    		$(location).attr('href', HOME_PAGE_URL);
				    	}
					}
				});
			}
		});
	}
	
	function _connectUserProfile(accessToken, callback) {
		$.ajax({
			url: 'user/profile/facebook',
			type: 'post',
			data: {
				accessToken: accessToken
			},
			success: function(response) {			
				if (callback != null) {
					callback();
				}
			}
		});		
	}
	
	return {
		init: function(url, callback) {
			console.debug('Initializing FB controller');
			
			HOME_PAGE_URL = url;
			_init(callback);
		},
		getUserId: function() {
			return _getUserId();
		},
		getFriends: function(callback) {
			_getFriends(callback);
		},
		sendNotification: function(userId, text, callback) {
			_sendNotification(userId, text, callback);
		},
		showDialog: function() {
			_showDialog(function(response) {
				_verifyAccessToken(response.accessToken);
			});
		},
		showDialogAndConnect: function() {
			_showDialog(function(response) {
				_connectUserProfile(response.accessToken, function() {
					userProfileController.hideDialog();
					userProfileController.getUserProfile();
				});
			});
		}
	};
	
}();
