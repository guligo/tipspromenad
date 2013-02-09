/**
 * JS component responsible for performing sick day user entry related functions. 
 * 
 * @author guligo
 */
var sickDayEntryController = function() {
	
	// constants
	var STATUS_OK  = 0;
	var STATUS_NOK = 1;
	
	var CREATE_ENTRY_ACTION = null;
	
	// variables
	var dialog = null;
	
	function _createEntry(url) {
		$('#dateSickDayEntryControlGroup').removeClass('error'); $('#dateSickDayEntryControlGroup .controls .help-block').remove();
		$('#noteSickDayEntryControlGroup').removeClass('error'); $('#noteSickDayEntryControlGroup .controls .help-block').remove();
		$.ajax({
			type: 'POST',
			url: CREATE_ENTRY_ACTION,
			data: {
				date     : $('#dateSickDayEntryInput input').val(),
				note     : $('#noteSickDayEntryInput').val(),
				isPrivate: $('#isPrivateSickDayEntryInput').is(':checked')
			},
			success: function(noteEntryBean) {
				if (noteEntryBean.status == STATUS_NOK) {
					for (var field in noteEntryBean.errors) {
						$('#' + field + 'SickDayEntryControlGroup').addClass('error');
						$('#' + field + 'SickDayEntryControlGroup .controls').append(
							'<span class="help-block">' +
								noteEntryBean.errors[field] +
							'</span>'
						);
					}
				} else if (noteEntryBean.status == STATUS_OK) {
					_hideDialog();
					entryListController.refreshEntryList();
				}
			},
			error: function(xhr) {
				alert('Error on creating sick day entry');
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
		init: function(url) {
			CREATE_ENTRY_ACTION = url;
			
			dialog = $('#sickDayEntryDialog'); 
		},
		createEntry: function(url) {
			_createEntry(url);
		},
		showDialog: function() {
			_showDialog();
		},
		hideDialog: function() {
			_hideDialog();
		}
	};
	
}();
