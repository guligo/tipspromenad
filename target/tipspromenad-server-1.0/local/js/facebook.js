var facebookController = function() {
	
	var APP_ID = '203913813008290';
	var APP_SECRET = 'c91e96ffca4c5465c3353baaaaf883e3';
	
	var _userId = null;
	var _accessToken = null;
	
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
					_userId = response.authResponse.userID;
					_accessToken = response.authResponse.accessToken;
					if (callback != null) {
						callback();
					}
			    } else {
			    	FB.login(function(resonse) {
			    		console.log(response);
			    	}, {perms: 'manage_notifications,email,user_likes'});
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
	}
	
	function _showDialog(callback) {
		FB.login(function(response) {
			_userId = response.authResponse.userID;
			_accessToken = response.authResponse.accessToken;
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
	
	function _sendNotification(text, callback) {
		_getAppAccessToken(function(appAccessToken) {
			FB.api('/' + _userId + '/notifications', 'post', {access_token: appAccessToken, template: text}, function(response) {
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
				    	// $(location).attr('href', 'home.page');
				    	alert('cool!');
					}
				});
			}
		});
	}
	
	return {
		init: function(callback) {
			_init(callback);
		},
		getUserId: function() {
			return _getUserId();
		},
		getFriends: function(callback) {
			_getFriends(callback);
		},
		sendNotification: function(callback) {
			_sendNotification(callback);
		},
		showDialog: function() {
			_showDialog(function(response) {
				_verifyAccessToken(response.accessToken);
			});
		}
	};
	
}();
