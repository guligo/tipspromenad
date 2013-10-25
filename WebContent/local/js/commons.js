/**
 * Contains various helper functions.
 */
var commonUtils = function() {
	
	function _formatNumber(num, len) {
		num = num.toString();
		while (num.length < len) {
			num = '0' + num;
		}
		return num;
	}
	
	function _formatDate(ms) {
		date = new Date(ms);
		
		var currentDate   = date.getDate();
		var currentMonth  = date.getMonth() + 1;
		var currentYear   = date.getFullYear();
		var currentHour   = date.getHours();
		var currentMinute = date.getMinutes();
		var currentSecond = date.getSeconds();
		
		return _formatNumber(currentDate, 2) + '-' + _formatNumber(currentMonth, 2) + '-' + _formatNumber(currentYear, 4) + ' '
			+ _formatNumber(currentHour, 2) + ':' + _formatNumber(currentMinute, 2) + ':'+ _formatNumber(currentSecond, 2);
	}
	
	function _formatDateShort(ms) {
		date = new Date(ms);
		
		var currentDate   = date.getDate();
		var currentMonth  = date.getMonth() + 1;
		var currentYear   = date.getFullYear();
		
		return _formatNumber(currentDate, 2) + '-' + _formatNumber(currentMonth, 2) + '-' + _formatNumber(currentYear, 4);
	}
	
	function _showError(field, errorMsg) {
		field.parent().parent().addClass('error');
		field.parent().append(
			'<span class="help-block">' +
				errorMsg +
			'</span>'
		);
	}
	
	function _showSuccess(field, successMsg) {
		field.parent().parent().addClass('success');
		field.parent().append(
			'<span class="help-block">' +
				successMsg +
			'</span>'
		);
	}
	
	function _hideError(field) {
		field.parent().parent().removeClass('error');
		field.parent().find('.help-block').remove();
	}
	
	function _hideSuccess(field) {
		field.parent().parent().removeClass('success');
		field.parent().find('.help-block').remove();
	}
	
	return {
		formatDate: function(ms) {
			return _formatDate(ms);
		},
		formatDateShort: function(ms) {
			return _formatDateShort(ms);
		},
		showError: function(field, errorMsg) {
			_showError(field, errorMsg);
		},
		showSuccess: function(field, successMsg) {
			_showSuccess(field, successMsg);
		},
		hideError: function(field) {
			_hideError(field);
		},
		hideSuccess: function(field) {
			_hideSuccess(field);
		}
	};
	
}();
