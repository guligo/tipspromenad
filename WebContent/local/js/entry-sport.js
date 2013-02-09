/**
 * JS component responsible for performing sport entry related functions. 
 * 
 * @author guligo
 */
var sportEntryController = function() {
	
	// constants
	var STATUS_OK  = 0;
	var STATUS_NOK = 1;
	
	var DEFAULT_DELTA = 3;
	
	var GET_ATTRIBUTES_ACTION_URL = null;
	var GET_MEASUREMENT_UNITS_ACTION_URL = null;
	var CREATE_MEASUREMENT_FOR_PREDEFINED_ATTRIBUTE_URL = null;
	var CREATE_MEASUREMENT_FOR_CUSTOM_ATTRIBUTE_URL = null;
	var CREATE_SPORT_ENTRY_ACTION_URL = null;
	
	// variables
	var _entryDialog = null;
	var _measurementDialog = null;
	var _createMeasurement = null;
	
	function _getPredefinedAttributesForm() {		
		$('#attributeMeasurementControlGroup .controls').html('<select id="attributeMeasurementInput"></select> <a href="javascript:sportEntryController.getCustomAttributesForm();">Custom?</a>');
		$('#unitMeasurementControlGroup .controls').html('<input id="unitMeasurementInput" type="text" readonly="readonly" />');		
		
		_createMeasurement = function() {
			_createMeasurementForPredefinedAttribute();
		};		
	}
	
	function _getCustomAttributesForm() {
		$('#attributeMeasurementControlGroup .controls').html('<input type="text" id="attributeMeasurementInput" placeholder="&lt;attribute&gt;"> <a href="javascript:sportEntryController.getPredefinedAttributesForm();">Predefined?</a>');
		$('#unitMeasurementControlGroup .controls').html('<select id="unitMeasurementInput"></select>');
		
		_getMeasurementUnits();
		_createMeasurement = function() {
			_createMeasurementForCustomAttribute();
		};
	}
	
	function _getAttributes(sportId) {
		$('#attributeMeasurementInput').html();
		$.ajax({
			type: 'GET',
			url: GET_ATTRIBUTES_ACTION_URL,
			data: {
				sportId: sportId
			},
			success: function(attributes) {
				$('#attributeMeasurementInput').html('');
				for (var i in attributes) {
					if (i == 0) {						
						$('#attributeMeasurementInput').append('<option value="' + attributes[i].id + '" selected="selected">' + attributes[i].name + '</option>');
						$('#unitMeasurementInput').val(attributes[i].measurementUnit.name);
					} else {
						$('#attributeMeasurementInput').append('<option value="' + attributes[i].id + '">' + attributes[i].name + '</option>');
					}										
				}
				$('#attributeMeasurementInput').change(function() {
					for (var i in attributes) {
						if (attributes[i].id == $('#attributeMeasurementInput').val()) {
							$('#unitMeasurementInput').val(attributes[i].measurementUnit.name);
							return;
						}
					}					
				});
			},
			error: function(xhr) {
				alert('Error on getting performance attributes');
			}
		});
	}
	
	function _getMeasurementUnits() {
		$.ajax({
			type: 'GET',
			url: GET_MEASUREMENT_UNITS_ACTION_URL,
			success: function(measurementUnits) {
				for (i in measurementUnits) {
					$('#unitMeasurementInput').append(
						'<option value="' + measurementUnits[i].id + '">' +
							measurementUnits[i].name +
						'</option>'
					);
				}
			},
			error: function(xhr) {
				alert('Error on getting measurement units');
			}
		});
	}
	
	function _createMeasurementForPredefinedAttribute() {
		$.ajax({
			type: 'POST',
			url: CREATE_MEASUREMENT_FOR_PREDEFINED_ATTRIBUTE_URL,
			data: {
				entryId         : $('#entryMeasurementInput').val(),
				attributeId     : $('#attributeMeasurementInput').val(),
				measurementValue: $('#valueMeasurementInput').val()
			},
			success: function(measurementUnits) {
				$('#measurementDialog').modal('hide');
				entryListController.refreshEntryList();
			},
			error: function(xhr) {
				alert('Error on creating measurement');
			}
		});
	}
	
	function _createMeasurementForCustomAttribute() {
		$.ajax({
			type: 'POST',
			url: CREATE_MEASUREMENT_FOR_CUSTOM_ATTRIBUTE_URL,
			data: {
				entryId          : $('#entryMeasurementInput').val(),
				attributeName    : $('#attributeMeasurementInput').val(),
				measurementValue : $('#valueMeasurementInput').val(),
				measurementUnitId: $('#unitMeasurementInput').val()
			},
			success: function(measurementUnits) {
				$('#measurementDialog').modal('hide');
				entryListController.refreshEntryList();
			},
			error: function(xhr) {
				alert('Error on creating measurement');
			}
		});
	}
	
	function _createSportEntry() {
		$.ajax({
			type: 'POST',
			url: CREATE_SPORT_ENTRY_ACTION_URL,
			data: {
				date     : $('#dateSportEntryInput input').val(),
				sportId  : $('#sportSportEntryInput').val(),
				duration : $('#durationHoursSportEntryInput').val() * 3600 + $('#durationMinutesSportEntryInput').val() * 60 + $('#durationSecondsSportEntryInput').val(),
				note     : $('#noteSportEntryInput').val(),
				intensity: $('#intensitySportEntryInput').val(),
				mood     : $('#moodSportEntryInput').val(),
				isPrivate: $('#isPrivateSportEntryInput').is(':checked')
			},
			success: function(sportEntryBean) {
				if (sportEntryBean.status == STATUS_NOK) {
					alert('Error on creating sport entry');
				} else if (sportEntryBean.status == STATUS_OK) {
					entryListController.refreshEntryList();
					$('#sportEntryDialog').modal('hide');
				}				
			},
			error: function(xhr) {
				alert('Error on creating sport entry');
			}
		});
	}
	
	function _showEntryDialog() {
		if (_entryDialog != null) {
			_entryDialog.modal('show');
		}
	}
	
	function _hideEntryDialog() {
		if (_entryDialog != null) {
			_entryDialog.modal('hide');
		}
	}
	
	function _showMeasurementDialog(sportId) {
		if (_measurementDialog != null) {
			_getAttributes(sportId);
			_measurementDialog.modal('show');
		}
	}
	
	function _hideMeasurementDialog() {
		if (_measurementDialog != null) {
			_measurementDialog.modal('hide');
		}
	}
	
	/**
	 * TODO: Refactoring.
	 */
	function _showChart(entryId, attributeId, delta) {
		var container = $('#entry' + entryId + 'attribute' + attributeId + 'chart');			
		container.html(
			'<span style="display: block;">'   +
				'<a href="javascript:sportEntryController.showChart(' + entryId + ', ' + attributeId + ', 3);" class="btn btn-small">Week</a>&nbsp;' +
				'<a href="javascript:sportEntryController.showChart(' + entryId + ', ' + attributeId + ', 15);" class="btn btn-small">Month</a>&nbsp;' + 
				'<a href="javascript:sportEntryController.showChart(' + entryId + ', ' + attributeId + ', 183);" class="btn btn-small">Year</a>&nbsp;' +
			'</span>' +		
			'<div style="position: absolute; margin-top: 5px; width: 500px; height: 100px;"></div>'
		);
		container.css('height', '130px');
		
		var element = $('#entry' + entryId + 'attribute' + attributeId + 'chart div');				
		var offset = -90;
		
		var start = new Date(new Date().getTime() - delta * 24 * 60 * 60 * 1000);
		start.setHours(0);
		start.setMinutes(0);
		start.setSeconds(0);
		start.setMilliseconds(0);
		
		var end = new Date(new Date().getTime() + delta * 24 * 60 * 60 * 1000);
		end.setHours(0);
		end.setMinutes(0);
		end.setSeconds(0);
		end.setMilliseconds(0);
					
		$.ajax({
			type: 'GET',
			url: 'stats',
			data: {
				attributeId: attributeId,
				start: start.getTime(),
				end: end.getTime()
			},
			success: function(response) {					
				// create chart
				var options = {
					grid: {
						borderWidth: 1
					},
					xaxis: {
			    		mode       : "time",
			    		minTickSize: [1, "day"],
			    		tickSize   : [1, "day"],
			    		min        : start.getTime() - offset * 60 * 1000,
			    		max        : end.getTime() - offset * 60 * 1000
			    	},
			    	yaxis: {
			    		min: 0,
			    		minTickSize: 1
			    	}
				};
				
				var dataset1 = [];
				var dataset2 = [];
				var dataset3 = [];
				for (var date in response) {
					var tmp1 = null;
					var tmp2 = 0;
					for (var measurement in response[date]) {
						if (tmp1 == null || response[date][measurement].value > tmp1) {
							tmp1 = response[date][measurement].value;
						}
						dataset1.push([date - offset * 60 * 1000, response[date][measurement].value]);
						tmp2 += response[date][measurement].value;
					}
					if (tmp1 != null) {
						dataset2.push([date - offset * 60 * 1000, tmp1]);
						dataset3.push([date - offset * 60 * 1000, tmp2]);
					}
				}
				
				var data1 = {
					data: dataset1,
					points: {
						show: true
					}
				};
				
				var data2 = {
					data: dataset2,
					lines: {
						show: true
					}
				};

				var data3 = {
					data: dataset3,
					lines: {
						show: true
					}
				};
				
				$.plot(element, [data1, data2, data3], options);
			}
		});
	}
	
	function _hideChart(entryId, attributeId) {
		var container = $('#entry' + entryId + 'attribute' + attributeId + 'chart');
		container.html('');
		container.css('height', '0px');
	}
	
	function _toggleChart(entryId, attributeId, delta) {
		if (delta == null) {
			delta = DEFAULT_DELTA;
		}
		
		var container = $('#entry' + entryId + 'attribute' + attributeId + 'chart');
		if (container.html() != '') {
			_hideChart(entryId, attributeId);
		} else {
			_showChart(entryId, attributeId, delta);
		}
	}
	
	return {
		init: function(url1, url2, url3, url4, url5) {
			GET_ATTRIBUTES_ACTION_URL = url1;
			GET_MEASUREMENT_UNITS_ACTION_URL = url2;
			CREATE_MEASUREMENT_FOR_PREDEFINED_ATTRIBUTE_URL = url3;
			CREATE_MEASUREMENT_FOR_CUSTOM_ATTRIBUTE_URL = url4;
			CREATE_SPORT_ENTRY_ACTION_URL = url5;
			
			$('#dateSportEntryInput').datepicker();
			_entryDialog = $('#sportEntryDialog');
			_measurementDialog = $('#measurementDialog'); 			
		},
		showEntryDialog: function() {
			_showEntryDialog();
		},
		hideEntryDialog: function() {
			_hideEntryDialog();
		},
		showMeasurementDialog: function(entryId, sportId) {
			$('#entryMeasurementInput').val(entryId);
			_showMeasurementDialog(sportId);
		},
		hideMeasurementDialog: function() {
			_hideMeasurementDialog();
		},
		getPredefinedAttributesForm: function() {
			_getPredefinedAttributesForm();
		},
		getCustomAttributesForm: function() {
			_getCustomAttributesForm();
		},
		getAttributes: function(sportId) {
			_getAttributes(sportId);
		},
		createMeasurement: function() {
			if (_createMeasurement != null) {
				_createMeasurement();
			}
		},
		createSportEntry: function() {
			_createSportEntry();
		},
		toggleChart: function(entryId, attributeId, delta) {
			_toggleChart(entryId, attributeId, delta);
		},
		showChart: function(entryId, attributeId, delta) {
			_showChart(entryId, attributeId, delta);
		}
	};
	
}();
