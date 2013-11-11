/**
 * JS component responsible for performing actions around user's personal information. 
 * 
 * @author guligo
 */
var userProfileController = function() {
	
	// constants
	var GET_PROFILE_URL = null;
	var UPDATE_PROFILE_URL = null;
	
	var dialog = null;
	
	function _init() {
		dialog = $('#userProfileDialog');
		$('#birthDateUserProfileInput').datepicker();
	}
	
	function _getUserProfile() {
		$.ajax({
			type: 'GET',
			url: GET_PROFILE_URL,
			success: function(userProfile) {
				// populate form
				$('#nameUserProfileInput').val(userProfile.name);
				$('input[name=genderUserProfileInput]').filter('[value="' + userProfile.gender + '"]').prop('checked', true);								
				$('#birthDateUserProfileInput').datepicker("setValue", userProfile.birthDate);
				$('#countryUserProfileInput').val(userProfile.country),
				$('#cityUserProfileInput').val(userProfile.city);
				
				// show FB connect button
				if (userProfile.user.fbUserId != null) {
					$('#facebookConnectButton').parent().parent().css('display', 'none');
				}
				_showDialog();
			},
			error: function(xhr) {
				// errorHandler.handle('Error on getting user profile', xhr);
				alert('Error on getting user profile');
			}
		});
	}
	
	function _updateUserProfile() {
		$.ajax({
			type: 'post',
			contentType: 'application/json',
		    dataType: 'json',
			url: UPDATE_PROFILE_URL,
			data: JSON.stringify({
				name     : $('#nameUserProfileInput').val(),
				gender   : $('input[name=genderUserProfileInput]:checked').val(),
				birthDate: $('#birthDateUserProfileInput').val(),
				country  : $('#countryUserProfileInput').val(),
				city     : $('#cityUserProfileInput').val()
			}),
			success: function() {
				_hideDialog();
			},
			error: function(xhr) {
				// errorHandler.handle('Error on updating user profile', xhr);
				alert('Error on updating user profile');
			}
		});
	}
	
	function _showDialog() {
		if (dialog != null) {
			dialog.modal('show');
		}
	}
	
	function _hideDialog() {
		if (dialog != null) {
			dialog.modal('hide');
		}
	}
	
	return {
		init: function(url1, url2) {
			GET_PROFILE_URL = url1;
			UPDATE_PROFILE_URL = url2;
			
			_init();
		},
		getUserProfile: function() {
			_getUserProfile();
		},
		updateUserProfile: function() {
			_updateUserProfile();
		},
		showDialog: function() {
			_showDialog();
		},
		hideDialog: function() {
			_hideDialog();
		}
	};
	
}();
