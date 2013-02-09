/**
 * JS component responsible for performing actions on entries list. 
 * 
 * @author guligo
 */
var entryListController = function() {
	
	// constants
	// action url:s
	var GET_ENTRY_LIST_ACTION = null;
	var REMOVE_MEASUREMENT_ACTION = null;
	var REMOVE_ENTRY_ACTION = null;
	
	// entry types
	var TYPE_ENTRY_SPORT = 1;
	var TYPE_ENTRY_BODY = 2;
	var TYPE_ENTRY_SICK_DAY = 3;
	var TYPE_ENTRY_NOTE = 4;
	
	// variable
	var _date = null;
	
	function _refreshEntryList(date) {
		if (date != null) {
			_date = date;
		} else {
			_date = new Date();
			_date = _date.getDate() + '-' + _date.getMonth() + '-' + (1900 + _date.getYear());			
		}
		$('#dateBodyEntryInput').attr('data-date', _date);
		$('#dateBodyEntryInput input').attr('value', _date);
		
		$.ajax({
			type: 'GET',
			url: GET_ENTRY_LIST_ACTION,
			data: {
				date: _date
			},
			success: function(wrappers) {
				$('#entries').html('');
				
				for (var i in wrappers) {
					var size = 0;
					
					// put measurements into dictionary
					var dictionary = [];
					for (var j in wrappers[i].entry.measurements) {
						var key = wrappers[i].entry.measurements[j].attribute.name;
						var value = wrappers[i].entry.measurements[j];
						if (dictionary[key] == null) {
							dictionary[key] = [];
							size++;
						}
						dictionary[key][dictionary[key].length] = value;						
					}					
					
					// regroup measurements
					entryBody = 'No performance measurements made';
					if (size > 0) {
						entryBody = '<table>';					
						j = 0;
						for (var key in dictionary) {
							j++;
							
							// group values of measurements for the same performance attribute
							var values = dictionary[key][0].value;
							for (var k = 1; k < dictionary[key].length; k++) {
								values = values + ', ' + dictionary[key][k].value;
							}
							
							entryBody += '<tr>' +
								'<td style="padding-right: 10px; text-align: right;">' + parseFloat(j) + '.</td>' +
								'<td style="padding-right: 10px; text-align: left;">' + dictionary[key][0].attribute.name + '</td>' +
								'<th style="padding-right: 10px; text-align: center;">' + values + '</td>' +
								'<td style="padding-right: 10px; text-align: left;">' + dictionary[key][0].attribute.measurementUnit.name + '</td>' +
								'<td>' +
									'<a href="javascript:sportEntryController.toggleChart(' + dictionary[key][0].entry.id + ', ' + dictionary[key][0].attribute.id + ');" class="icon-tasks"></a>&nbsp;' +
									'<a href="javascript:entryListController.removeMeasurement(' + dictionary[key][dictionary[key].length - 1].id + ');" class="icon-remove-sign"></a>&nbsp;' +									
								'</td>' +
							'</tr>' +
							'<tr>' +
								'<td colspan="5" id="entry' + dictionary[key][0].entry.id + 'attribute' + dictionary[key][0].attribute.id + 'chart" style="vertical-align: top;">' +									
								'</td>' +
							'</tr>';
						}
						entryBody += '</table>';
					}
					
					// format date
					var date = new Date(wrappers[i].entry.date);
					var format = date.getDate() + '-' + (date.getMonth() + 1) + '-' + date.getFullYear();
					
					// pick right image for user entry
					var image = null;
					if (wrappers[i].type == 1) {
						image = wrappers[i].entry.sport.image;
					} else if (wrappers[i].type == 2) {
						image = 'local/img/body.png';
					} else if (wrappers[i].type == 3) {
						image = 'local/img/sick-day.png';
						entryBody = wrappers[i].entry.note;
					} else if (wrappers[i].type == 4) {
						image = 'local/img/note.png';
						entryBody = wrappers[i].entry.note;
					}
					$.ajax({
						type: 'GET',
						url: 'user/get-profile',
						success: function(userProfile) {
							// construct footer
							var entryFooter = '';
							entryFooter += '<div style="margin-top: 10px;">';
							entryFooter += '<a href="#" class="btn btn-small">Edit</a>&nbsp;<a href="javascript:entryListController.removeEntry(' + wrappers[i].entry.id + ');" class="btn btn-small">Remove</a>';
							if (wrappers[i].type == TYPE_ENTRY_SPORT) {
								entryFooter += '&nbsp;<a href="javascript:sportEntryController.showMeasurementDialog(' + wrappers[i].entry.id + ', ' + wrappers[i].entry.sport.id + ');" class="btn btn-small">Add measurement</a>';
							} else if (wrappers[i].type == TYPE_ENTRY_BODY) {
								entryFooter += '&nbsp;<a href="javascript:bodyEntryController.showMeasurementDialog(' + wrappers[i].entry.id + ');" class="btn btn-small">Add measurement</a>';
							}
							entryFooter += '&nbsp;<a href="#" class="btn btn-small">Move Up</a>&nbsp;<a href="#" class="btn btn-small">Move Down</a>';
							entryFooter += '</div>';
							
							if (userProfile != null) {
								// construct html for user entry
								$('#entries').append('<div class="entry" style="padding-left: 60px;">' +
									'<img src="' + userProfile.image + '" class="img-polaroid" style="float: left; margin-left: -50px; margin-top: 0px; width: 33px; height: 33px;" />' +
									'<img src="' + image + '" style="float: left; margin-left: -50px; margin-top: 45px;" />' +						
									'<div>' + wrappers[i].entry.username +
										'&nbsp<span class="label">' + (wrappers[i].type == 1 ? wrappers[i].entry.sport.name + ', ' : '') + format + '</span>' +
									'</div>' +
									'<div style="margin-top: 10px;">' + entryBody + '</div>' +
									entryFooter +
								'</div>');
							}
						}
					});
				}
			},
			error: function(xhr) {
				errorHandler.handle('Error on refreshing entry list', xhr);
			}
		});	
	}	
	
	function _removeMeasurement(measurementId) {
		$.ajax({
			type: 'POST',
			url: REMOVE_MEASUREMENT_ACTION,
			data: {
				measurementId: measurementId
			},
			success: function() {
				_refreshEntryList(_date);
			},
			error: function(xhr) {
				alert('Error on removing measurement');
			}
		});
	}
	
	function _removeEntry(entryId) {
		$.ajax({
			type: 'POST',
			url: REMOVE_ENTRY_ACTION,
			data: {
				id: entryId
			},
			success: function() {
				_refreshEntryList(_date);
			},
			error: function(xhr) {
				alert('Error on removing entry');
			}
		});
	}
	
	return {
		init: function(url1, url2, url3) {
			GET_ENTRY_LIST_ACTION = url1;
			REMOVE_MEASUREMENT_ACTION = url2;
			REMOVE_ENTRY_ACTION = url3;
		},
		refreshEntryList: function(date) {
			_refreshEntryList(date);
		},
		removeMeasurement: function(measurementId) {
			_removeMeasurement(measurementId);
		},
		removeEntry: function(entryId) {
			_removeEntry(entryId);
		}
	};
	
}();
