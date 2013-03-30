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
		
		return _formatNumber(currentDate, 2)  + '-' + _formatNumber(currentMonth, 2)  + '-' + _formatNumber(currentYear, 4) + ' '
			+ _formatNumber(currentHour, 2) + ':' + _formatNumber(currentMinute, 2) + ':'+ _formatNumber(currentSecond, 2);
	}
	
	return {
		formatDate: function(ms) {
			return _formatDate(ms);
		}
	};
	
}();
