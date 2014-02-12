/**
 * JS component responsible for performing actions around user's personal information. 
 * 
 * @author guligo
 */
var userProfileController = function() {
	
	// constants
	var _GET_PROFILE_URL    = null;
	var _UPDATE_PROFILE_URL = null;
	
	var _dialog     = null;
	var _datepicker = null;
	var _dictionary = null;
	
	function _initUrls(GET_PROFILE_URL, UPDATE_PROFILE_URL) {
		_GET_PROFILE_URL    = GET_PROFILE_URL;
		_UPDATE_PROFILE_URL = UPDATE_PROFILE_URL;
	}
	
	function _initTranslations(dictionary) {
		_dictionary = dictionary;
		console.debug('UP dictionary = %o', _dictionary);
	}
	
	function _initComponents() {
		_dialog     = $('#userProfileDialog');
		_datepicker = $('#birthDateUserProfileInput').datepicker();
	}
	
	function _getUserProfile() {
		$.ajax({
			type: 'GET',
			url: _GET_PROFILE_URL,
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
				console.error('Error on getting user profile', xhr);
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
			url: _UPDATE_PROFILE_URL,
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
					if ($.inArray('NAME_EMPTY', response.errors) > -1) {
						commonUtils.showError($('#nameUserProfileInput'), _dictionary['userprofile.message.nameempty']);
					} else if ($.inArray('NAME_TOO_SHORT', response.errors) > -1) {
		    			commonUtils.showError($('#nameUserProfileInput'), _dictionary['userprofile.message.nametooshort']);
		    		} if ($.inArray('NAME_TOO_LONG', response.errors) > -1) {
		    			commonUtils.showError($('#nameUserProfileInput'), _dictionary['userprofile.message.nametoolong']);
		    		}
		    		
		    		if ($.inArray('BIRTH_DATE_WRONG_FORMAT', response.errors) > -1) {
		    			commonUtils.showError($('#birthDateUserProfileInput'), _dictionary['userprofile.message.wrongdate']);
		    		}
		    		
		    		if ($.inArray('COUNTRY_TOO_SHORT', response.errors) > -1) {
		    			commonUtils.showError($('#countryUserProfileInput'), _dictionary['userprofile.message.countrytooshort']);
		    		} if ($.inArray('COUNTRY_TOO_LONG', response.errors) > -1) {
		    			commonUtils.showError($('#countryUserProfileInput'), _dictionary['userprofile.message.countrytoolong']);
		    		}
		    		
		    		if ($.inArray('CITY_TOO_SHORT', response.errors) > -1) {
		    			commonUtils.showError($('#cityUserProfileInput'), _dictionary['userprofile.message.citytooshort']);
		    		} if ($.inArray('CITY_TOO_LONG', response.errors) > -1) {
		    			commonUtils.showError($('#cityUserProfileInput'), _dictionary['userprofile.message.citytoolong']);
		    		}
				}
			},
			error: function(xhr) {
				console.error('Error on updating user profile', xhr);
			}
		});
	}
	
	function _showDialog() {
		if (_dialog != null) {
			console.debug('Showing UP dialog');
			_dialog.modal('show');
		}
	}
	
	function _hideDialog() {
		if (_dialog != null) {
			console.debug('Hiding UP dialog');
			_dialog.modal('hide');
		}
	}
	
	return {
		init: function(GET_PROFILE_URL, UPDATE_PROFILE_URL, dictionary) {
			console.debug('Initializing UP controller');
			
			_initUrls(GET_PROFILE_URL, UPDATE_PROFILE_URL);
			_initComponents();
			_initTranslations(dictionary);
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
