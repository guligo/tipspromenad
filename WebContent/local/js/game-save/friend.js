/**
 * Contains various "friends" tab related functions.
 */
var friendController = function() {
	
	var COLUMNS = 3;
	
	function _renderFriends() {
		$('#friendsFacebookConnect').css('display', 'none');
		facebookController.getFriends(function(friends) {
			var html = '<table id="friendsList" style="width: 100%;">';
			if (friends != null) {
				for (var i = 0; i < friends.length; i++) {
					html += '<tr>';
					for (var j = 0; j < COLUMNS; j++) {
						html += '<td>';
							html += '<input type="checkbox" value="' + friends[i].id + '" style="margin-top: -1px;" />&nbsp;<span>' + friends[i].name + '</span><br />';
							html += '<img src="' + friends[i].picture.data.url + '" />';
						html += '</td>';
						
						if (i >= friends.length - 1) {
							break;
						} else {
							i++;
						}
					}
					html += '</tr>';
				}
			}
			html += '</table>';
			$('#friends').html(html);
		});
	}
	
	function _getUserIds() {
		var result = [];
		$('#friendsList :checked').each(function() {
			result.push($(this).val());
		});
		result.push(facebookController.getUserId());
		return result;
	}
	
	return {
		init: function() {
			if (facebookController.getUserId() != null) {
				_renderFriends();
			}
		},
		getUserIds: function() {
			return _getUserIds();
		}
	};
	
}();
