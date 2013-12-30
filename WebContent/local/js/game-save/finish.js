/**
 * Contains various "finish" tab related functions.
 */
var finishController = function() {
	
	var GAME_FINALIZE_ACTION = null;
	var GAME_DISCARD_ACTION  = null;
	var GAME_LIST_PAGE       = null;
	
	function _finalize() {
		var userIds = friendController.getUserIds();
		
		var j = 0;
		for (var i = 0; i < userIds.length; i++) {
			facebookController.sendNotification(userIds[i], '@[' + facebookController.getUserId() + '] invited you to play Tipspromenad!', function() {
				j++;
			});
		}
		
		var check = function() {
			if (j == userIds.length) {
				$.ajax({
					url: GAME_FINALIZE_ACTION.replace('{id}', gameController.getGameId()),
					type: 'post',
					success: function () {
						window.location.replace(GAME_LIST_PAGE);
					}
				});
			} else {
				setTimeout(function() { check(); }, 500);
			}
		};
		check();
	}
	
	function _draft() {
		window.location.replace(GAME_LIST_PAGE);
	}
	
	function _discard() {
		$.ajax({
			url: GAME_DISCARD_ACTION.replace('{id}', gameController.getGameId()),
			type: 'post',
			success: function () {
				window.location.replace(GAME_LIST_PAGE);
			}
		});
	}
	
	return {
		init: function(url1, url2, url3) {
			GAME_FINALIZE_ACTION = url1;
			GAME_DISCARD_ACTION  = url2;
			GAME_LIST_PAGE       = url3;
		},
		finalize: function() {
			_finalize();
		},
		draft: function() {
			_draft();
		},
		discard: function() {
			_discard();
		}
	};
	
}();
