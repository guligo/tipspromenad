<form id="gameForm" class="form-horizontal">
	<div class="control-group">
		<label for="gameName" class="control-label">Name</label>
		<div class="controls">
			<input id="gameName" placeholder="Name" type="text" />
		</div>
	</div>
	<div class="control-group">
		<label for="gameDate" class="control-label">Date</label>
		<div class="controls">
			<input id="gameDate" placeholder="Date" type="text" />
		</div>
	</div>
	<%--
	<div class="control-group">
		<div class="controls">
			<a href="javascript:gameController.saveGame();" class="btn">Save</a>
		</div>
	</div>
	--%>
</form>
<form id="questionForm" class="form-horizontal">
		<div class="control-group">
			<label for="questionText" class="control-label">Question</label>
			<div class="controls">
				<textarea id="questionText" placeholder="Enter question here..."></textarea>
			</div>
		</div>
		<div class="control-group">
			<div id="questionContainer" class="controls">
				No questions.
			</div>
		</div>
		<div class="control-group">
		<div class="controls">
			<a href="javascript:questionController.addQuestion();" class="btn">Add Question</a>
			<a href="javascript:questionController.saveQuestions(gameController.getGameId());" class="btn">Save</a>
		</div>
	</div>
</form>
