/**
 * JS component responsible for performing body entry related functions. 
 * 
 * @author guligo
 */
var bodyEntryController = function() {

	// constants
	var STATUS_OK  = 0;
	var STATUS_NOK = 1;

	var CREATE_ENTRY_ACTION = null;
	
	// variables
	var entryDialog = null;
	
	function _createEntry(url) {
		$('#dateBodyEntryControlGroup').removeClass('error'); $('#dateBodyEntryControlGroup .controls .help-block').remove();
		$('#noteBodyEntryControlGroup').removeClass('error'); $('#noteBodyEntryControlGroup .controls .help-block').remove();
		$.ajax({
			type: 'POST',
			url: CREATE_ENTRY_ACTION,
			data: {
				date     : $('#dateBodyEntryInput input').val(),
				note     : $('#noteBodyEntryInput').val(),
				isPrivate: $('#isPrivateBodyEntryInput').is(':checked')
			},
			success: function(status) {
				if (status == STATUS_NOK) {
					alert('Error on creating body day entry');
					// errorHandler.handle('Error on creating body entry', xhr);
				} else if (status == STATUS_OK) {
					_hideEntryDialog();
					entryListController.refreshEntryList();
				}
			},
			error: function(xhr) {
				errorHandler.handle('Error on creating body entry', xhr);
			}
		});
	}
	
	function _showEntryDialog() {
		if (entryDialog != null) {
			entryDialog.modal('show');
		}
	}
	
	function _hideEntryDialog() {
		if (entryDialog != null) {
			entryDialog.modal('hide');
		}
	}
	
	return {
		init: function(url) {
			CREATE_ENTRY_ACTION = url;
			
			$('#dateBodyEntryInput').datepicker();
			entryDialog = $('#bodyEntryDialog'); 
		},
		createEntry: function(url) {
			_createEntry(url);
		},
		showEntryDialog: function() {
			_showEntryDialog();
		},
		hideEntryDialog: function() {
			_hideEntryDialog();
		}
	};
	
}();
