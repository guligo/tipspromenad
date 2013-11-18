/**
 * JS component responsible for performing actions around user's personal information. 
 * 
 * @author guligo
 */
var userProfileController = function() {
	
	// constants
	var GET_PROFILE_URL    = null;
	var UPDATE_PROFILE_URL = null;
	
	var dialog     = null;
	var datepicker = null;
	
	function _init() {
		dialog     = $('#userProfileDialog');
		datepicker = $('#birthDateUserProfileInput').datepicker();
	}
	
	function _getUserProfile() {
		$.ajax({
			type: 'GET',
			url: GET_PROFILE_URL,
			success: function(userProfile) {
				// populate form
				$('#nameUserProfileInput').val(userProfile.name);
				$('input[name=genderUserProfileInput]').filter('[value="' + userProfile.gender + '"]').prop('checked', true);	
				if (userProfile.birthDate != null) {
					$('#birthDateUserProfileInput').datepicker("setValue", userProfile.birthDate);
				} else {
					$('#birthDateUserProfileInput').val(null);
				}
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
		commonUtils.hideError($('#nameUserProfileInput'));
		commonUtils.hideError($('#birthDateUserProfileInput'));
		commonUtils.hideError($('#countryUserProfileInput'));
		commonUtils.hideError($('#cityUserProfileInput'));
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
			success: function(response) {
				if (response.errors == null || response.errors.length == 0) {
					_hideDialog();
				} else {
					if ($.inArray('NAME_TOO_SHORT', response.errors) > -1) {
		    			commonUtils.showError($('#nameUserProfileInput'), 'Name too short');
		    		} if ($.inArray('NAME_TOO_LONG', response.errors) > -1) {
		    			commonUtils.showError($('#nameUserProfileInput'), 'Name too long');
		    		}
		    		
		    		if ($.inArray('BIRTH_DATE_WRONG_FORMAT', response.errors) > -1) {
		    			commonUtils.showError($('#birthDateUserProfileInput'), 'Wrong date');
		    		}
		    		
		    		if ($.inArray('COUNTRY_TOO_SHORT', response.errors) > -1) {
		    			commonUtils.showError($('#countryUserProfileInput'), 'Country name too short');
		    		} if ($.inArray('COUNTRY_TOO_LONG', response.errors) > -1) {
		    			commonUtils.showError($('#countryUserProfileInput'), 'Country name too long');
		    		}
		    		
		    		if ($.inArray('CITY_TOO_SHORT', response.errors) > -1) {
		    			commonUtils.showError($('#cityUserProfileInput'), 'City name too short');
		    		} if ($.inArray('CITY_TOO_LONG', response.errors) > -1) {
		    			commonUtils.showError($('#cityUserProfileInput'), 'City name too long');
		    		}
				}
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
