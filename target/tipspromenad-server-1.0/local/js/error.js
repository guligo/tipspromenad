/**
 * JS component responsible for error handling. 
 * 
 * @author guligo
 */
var errorHandler = function() {
	
	var dialog = null;
	
	function _init() {
		dialog = $('#errorDialog');
	}
	
	function _handle(message, xhr) {
		_showDialog();
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
		init: function() {
			_init();
		},
		handle: function(message, xhr) {
			_handle(message, xhr);
		},
		showDialog: function() {
			_showDialog();
		},
		hideDialog: function() {
			_hideDialog();
		}
	};
	
}();
