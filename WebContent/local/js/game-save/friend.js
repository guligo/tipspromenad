/**
 * Contains various "friends" tab related functions.
 */
var friendController = function() {
	
	var COLUMNS = 3;
	
	var INVITATION_LIST_ACTION   = 'invitation/list/{gameId}';
	var INVITATION_SAVE_ACTION   = 'invitation/save';
	var INVITATION_REMOVE_ACTION = 'invitation/remove/{gameId}/{fbUserId}';
	
	function _renderFriends(fbUserIds) {
		$('#friendsFacebookConnect').css('display', 'none');
		facebookController.getFriends(function(friends) {
			var html = '<table id="friendsList" class="table table-bordered" style="width: 100%;" cellpadding="10">';
			if (friends != null) {
				for (var i = 0; i < friends.length; i++) {
					html += '<tr>';
					for (var j = 0; j < COLUMNS; j++) {
						html += '<td>';
							html += '<img src="' + friends[i].picture.data.url + '" />';
							html += '<br />';
							
							var checked = '';
							if (fbUserIds != null) {
								if ($.inArray(friends[i].id, fbUserIds) > -1) {
									checked = 'checked="true"';
								}
							}
							html += '<div style="margin-top: 5px;">';
								html += '<input type="checkbox" value="' + friends[i].id + '" style="margin-top: -1px;" onclick="javascript:friendController.changeInvitation(this);" ' + checked + ' />&nbsp;<span>' + friends[i].name + '</span>';
							html += '</div>';
						html += '</td>';
						
						if (i >= friends.length - 1) {
							while (i % COLUMNS != 0) {
								html += '<td>&nbsp;</td>';
								i++;
							}
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
		if (gameId != null) {
			$.ajax({
				url: INVITATION_LIST_ACTION.replace('{gameId}', gameId),
				type: 'get',
			    contentType: 'application/json',
			    dataType: 'json',
			    success: function(response) {
			    	if (callback != null) {
			    		callback(response.fbUserIds);
			    	}
			    }
			});
		} else {
			if (callback != null) {
	    		callback();
	    	}
		}
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
				if (typeof gameController != 'undefined') {
					_getInvitationList(gameController.getGameId(), function(fbUserIds) {
						_renderFriends(fbUserIds);
					});
				}
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
