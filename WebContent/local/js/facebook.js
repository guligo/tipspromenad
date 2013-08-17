var facebookController = function() {
	
	var _userId = null;
	
	function _init(callback) {
		window.fbAsyncInit = function() {
			FB.init({
				appId : '203913813008290',
				status: true,
				cookie: true,
				xfbml : true
			});
			
			FB.Event.subscribe('auth.authResponseChange', function(response) {
				if (response.status === 'connected') {
					_userId = response.authResponse.userID;
					if (callback != null) {
						callback();
					}
			    } else if (response.status === 'not_authorized') {
			    	FB.login();
			    } else {
			    	FB.login();
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
	
	function _getUserId() {
		return _userId;
	}
	
	function _getFriends(callback) {
		FB.api('/me/friends', function(response) {
			var friends = response.data;
			if (callback != null) {
				callback(friends);
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
		}
	};
	
}();
