<%@ page import="co.vrings.globals.Constants" %>

<!-- scripts -->
<script type="text/javascript" src="local/js/entry-sport.js"></script>
<script type="text/javascript">	
	$(document).ready(function() {
		sportEntryController.init(
			'<%= Constants.URL.ENTRY_GET_PERFORMANCE_ATTRIBUTES_ACTION %>',
			'<%= Constants.URL.ENTRY_GET_MEASUREMENT_UNITS %>',
			'<%= Constants.URL.ENTRY_CREATE_PERFORMANCE_MEASUREMENT_FOR_PREDEFINED_ATTRIBUTE_ACTION %>',
			'<%= Constants.URL.ENTRY_CREATE_PERFORMANCE_MEASUREMENT_FOR_CUSTOM_ATTRIBUTE_ACTION %>',
			'<%= Constants.URL.ENTRY_SPORT_CREATE_ACTION %>'
		);
		sportEntryController.getPredefinedAttributesForm();
	});
</script>

<!-- html -->
<div class="modal" id="sportEntryDialog" tabindex="-1" role="dialog" aria-labelledby="sportEntryLabel" aria-hidden="true" style="display: none;">
	<div class="modal-header">
		<a href="javascript:sportEntryController.hideEntryDialog();" class="close">×</a>
		<h3 id="sportEntryLabel">Create sports entry</h3>
	</div>
	<div class="modal-body">
		<form class="form-horizontal">
			<div id="dateSportEntryControlGroup" class="control-group">
				<label class="control-label" for="dateSportEntryInput">Date</label>
				<div class="controls">
					<div class="input-append date" id="dateSportEntryInput" data-date="${param['date']}" data-date-format="dd-mm-yyyy">
						<input class="span2" size="16" type="text" value="${param['date']}">
						<span class="add-on"><i class="icon-th"></i></span>
					</div>
				</div>
			</div>
			<div id="sportSportEntryControlGroup"  class="control-group">				
				<label class="control-label" for="sportSportEntryInput">Sport</label>
				<div class="controls">
					<select id="sportSportEntryInput">
						<option value="1">Gym</option>
						<option value="2">Running</option>
						<option value="3">Swimming</option>
						<option value="4">Cycling</option>
						<option value="5">Home training</option>
					</select>
				</div>				
			</div>
			<div id="durationSportEntryControlGroup"  class="control-group">
				<label class="control-label" for="durationSportEntryInput">Duration</label>
				<div class="controls">
					<input type="text" id="durationHoursSportEntryInput"   placeholder="&lt;hr.&gt;"  style="width: 50px;" />
					<input type="text" id="durationMinutesSportEntryInput" placeholder="&lt;min.&gt;" style="width: 50px; margin-left: 10px;" />
					<input type="text" id="durationSecondsSportEntryInput" placeholder="&lt;sec.&gt;" style="width: 50px; margin-left: 10px;" />
				</div>
			</div>
			<div id="noteSportEntryControlGroup" class="control-group">
				<label class="control-label" for="noteSportEntryInput">Notes</label>
				<div class="controls">
					<textarea rows="3" id="noteSportEntryInput" placeholder="&lt;notes&gt;"></textarea>
				</div>
			</div>
			<div id="intensitySportEntryControlGroup" class="control-group">
				<label class="control-label" for="intensitySportEntryInput">Intensity</label>
				<div class="controls">
					<select id="intensitySportEntryInput">
						<option value="1">Very low</option>
						<option value="2">Low</option>
						<option value="3" selected="selected">Normal</option>
						<option value="4">High</option>
						<option value="5">Very high</option>
					</select>
				</div>
			</div>
			<div id="moodSportEntryControlGroup" class="control-group">
				<label class="control-label" for="moodtEntryInput">Mood</label>
				<div class="controls">
					<select id="moodSportEntryInput">
						<option value="1">Very bad</option>
						<option value="2">Bad</option>
						<option value="3" selected="selected">Neutral</option>
						<option value="4">Good</option>
						<option value="5">Very good</option>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="isPrivateSportEntryInput">Private</label>
				<div class="controls">
					<input type="checkbox" id="isPrivateSportEntryInput">
				</div>
			</div>
		</form>
	</div>
	<div class="modal-footer">
		<a href="javascript:sportEntryController.hideEntryDialog();" class="btn">Close</a>
		<a href="javascript:sportEntryController.createSportEntry();" class="btn btn-primary">Create entry</a>
	</div>
</div>

<div class="modal" id="measurementDialog" tabindex="-1" role="dialog" aria-labelledby="measuremenetLabel" aria-hidden="true" style="display: none;">
	<div class="modal-header">
		<a href="javascript:sportEntryController.hideMeasurementDialog();" class="close">×</a>
		<h3 id="measuremenetLabel">Add measurement</h3>
	</div>
	<div class="modal-body">
		<form class="form-horizontal">
			<input id="entryMeasurementInput" type="hidden" />
			<div id="attributeMeasurementControlGroup"  class="control-group">
				<label class="control-label" for="attributeMeasurementInput">Attribute</label>
				<div class="controls">
					<!-- controls here are dynamically created by JS -->
				</div>
			</div>
			<div id="valueMeasurementControlGroup"  class="control-group">
				<label class="control-label" for="valueMeasurementInput">Value</label>
				<div class="controls">
					<input type="text" id="valueMeasurementInput" placeholder="&lt;value&gt;" />
				</div>
			</div>
			<div id="unitMeasurementControlGroup"  class="control-group">
				<label class="control-label" for="unitMeasurementInput">Measurement unit</label>
				<div class="controls">
					<!-- controls here are dynamically created by JS -->
				</div>
			</div>
		</form>
	</div>
	<div class="modal-footer">
		<a href="javascript:sportEntryController.hideMeasurementDialog();" class="btn">Close</a>
		<a href="javascript:sportEntryController.createMeasurement();" class="btn btn-primary">Add measurement</a>
	</div>
</div>
