/**
 * JS component responsible for performing note entry related functions. 
 * 
 * @author guligo
 */
var noteEntryController = function() {
	
	// constants
	var STATUS_OK  = 0;
	var STATUS_NOK = 1;
	
	var CREATE_ENTRY_ACTION = null;
	
	// variables
	var dialog = null;
	
	function _createEntry() {
		$('#dateNoteEntryControlGroup').removeClass('error'); $('#dateNoteEntryControlGroup .controls .help-block').remove();
		$('#noteNoteEntryControlGroup').removeClass('error'); $('#noteNoteEntryControlGroup .controls .help-block').remove();
		$.ajax({
			type: 'POST',
			url: CREATE_ENTRY_ACTION,
			data: {
				date     : $('#dateNoteEntryInput input').val(),
				note     : $('#noteNoteEntryInput').val(),
				isPrivate: $('#isPrivateNoteEntryInput').is(':checked')
			},
			success: function(noteEntryBean) {
				if (noteEntryBean.status == STATUS_NOK) {
					for (var field in noteEntryBean.errors) {
						$('#' + field + 'NoteEntryControlGroup').addClass('error');
						$('#' + field + 'NoteEntryControlGroup .controls').append(
							'<span class="help-block">' +
								noteEntryBean.errors[field] +
							'</span>'
						);
					}
				} else if (noteEntryBean.status == STATUS_OK) {
					$('#noteEntryDialog').modal('hide');
					entryListController.refreshEntryList();
				}
			},
			error: function(xhr) {
				alert('Error on creating note entry');
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
			
			$('#dateNoteEntryInput').datepicker();
			dialog = $('#noteEntryDialog'); 
		},
		showDialog: function() {
			_showDialog();
		},
		hideDialog: function() {
			_hideDialog();
		},
		createEntry: function() {
			_createEntry();
		}
	};
	
}();
