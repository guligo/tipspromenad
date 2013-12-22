/**
 * Contains various "friends" tab related functions.
 */
var friendController = function() {
	
	var COLUMNS = 3;
	
	var INVITATION_LIST_ACTION   = "invitation/list/{gameId}";
	var INVITATION_SAVE_ACTION   = "invitation/save";
	var INVITATION_REMOVE_ACTION = "invitation/remove/{gameId}/{fbUserId}";
	
	function _renderFriends() {
		$('#friendsFacebookConnect').css('display', 'none');
		facebookController.getFriends(function(friends) {
			var html = '<table id="friendsList" style="width: 100%;">';
			if (friends != null) {
				for (var i = 0; i < friends.length; i++) {
					html += '<tr>';
					for (var j = 0; j < COLUMNS; j++) {
						html += '<td>';
							html += '<input type="checkbox" value="' + friends[i].id + '" style="margin-top: -1px;" onclick="javascript:friendController.changeInvitation(this);" />&nbsp;<span>' + friends[i].name + '</span><br />';
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
	
	function _getInvitationList(gameId, callback) {
		$.ajax({
			url: INVITATION_LIST_ACTION.replace('{gameId}', gameId),
			type: 'get',
		    contentType: 'application/json',
		    dataType: 'json',
			data: JSON.stringify({
				gameId: gameId
			}),
		    success: function(response) {
		    	if (callback != null) {
		    		callback(response.fbUserIds);
		    	}
		    }
		});
	}
	
	function _saveInvitation(gameId, fbUserId, callback) {
		$.ajax({
			url: INVITATION_SAVE_ACTION,
			type: 'post',
		    contentType: 'application/json',
		    dataType: 'json',
			data: JSON.stringify({
				gameId: gameId,
				fbUserId: fbUserId
			}),
		    success: function(response) {
		    	if (callback != null) {
		    		callback();
		    	}
		    }
		});
	}
	
	function _removeInvitation(gameId, fbUserId, callback) {
		$.ajax({
			url: INVITATION_REMOVE_ACTION.replace('{gameId}', gameId).replace('{fbUserId}', fbUserId),
			type: 'post',
		    contentType: 'application/json',
		    dataType: 'json',
		    success: function(response) {
		    	if (callback != null) {
		    		callback();
		    	}
		    }
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
		changeInvitation: function(checkbox) {
			if (checkbox.checked) {
				_saveInvitation(gameController.getGameId(), checkbox.value);
			} else {
				_removeInvitation(gameController.getGameId(), checkbox.value);
			}
		},
		getUserIds: function() {
			return _getUserIds();
		}
	};
	
}();
