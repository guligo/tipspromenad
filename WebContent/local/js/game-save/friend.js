/**
 * Contains various "friends" tab related functions.
 */
var friendController = function() {
	
	function _renderFriends() {
		facebookController.getFriends(function(friends) {
			var html = '';
			if (friends != null) {
				for (var i = 0; i < friends.length; i++) {
					html += '<input type="checkbox" />&nbsp;' + friends[i].name + '<br />';
				}
			}
			$('#friends').html(html);
		});
	}
	
	return {
		init: function() {
			if (facebookController.getUserId() != null) {
				_renderFriends();
			}
		},
	};
	
}();
