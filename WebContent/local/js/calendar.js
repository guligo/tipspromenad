/**
 * JS component responsible for performing actions on calendar. 
 * 
 * @author guligo
 */
var calendarController = function() {
	
	var GET_ALL_ENTRIES = null;
	var SHOW_ENTRIES = null;
	
	function _displayCalendar() {
		$.ajax({
			type: 'GET',
			url: GET_ALL_ENTRIES,
			success: function(wrappers) {
				var events = [];
				for (i in wrappers) {
					var eventType  = 'Unknown';
					var eventColor = '#3366cc';
					if (wrappers[i].type == 1) {
						eventType  = 'Sport: ' + wrappers[i].entry.sport.name;
						eventColor = '#3bda00';
					} else if (wrappers[i].type == 2) {
						eventType  = 'Body';
						eventColor = '#ffd740';
					} else if (wrappers[i].type == 3) {
						eventType  = 'Sick day';
						eventColor = '#ef002a';
					} else if (wrappers[i].type == 4) {
						eventType  = 'Note';
						eventColor = '#3366cc';
					}
					events[events.length] = {
						title: eventType,
						start: new Date(wrappers[i].entry.date),
						color: eventColor
					};
				}
				
				$('#calendar').fullCalendar({
					firstDay: 1,
					header: {
						left: 'prev,next today',
						center: 'title',
						right: null
						// right: 'month,agendaWeek,agendaDay'
					},
					dayClick: function(date, allDay, jsEvent, view) {
						var format = date.getDate() + '-' + (date.getMonth() + 1) + '-' + date.getFullYear();
						$(location).attr('href', SHOW_ENTRIES + '?date=' + format);
				    },
					editable: false,
					events: events
				});
			},
			error: function(xhr) {
				errorHandler.handle('Error on creating sport entry', xhr);
			}
		});
	}
	
	return {
		init: function(url1, url2) {
			GET_ALL_ENTRIES = url1;
			SHOW_ENTRIES = url2;
			
			_displayCalendar();
		}
	};
	
}();
