/**
 * Contains various "finish" tab related functions.
 */
var finishController = function() {
	
	function _getCode(callback) {
		if (callback != null) {
			callback(code);
		}
	}
	
	return {
		init: function() {
			_getCode(function(code) {
				$('#code').html('12345678');
			});
		}
	};
	
}();
