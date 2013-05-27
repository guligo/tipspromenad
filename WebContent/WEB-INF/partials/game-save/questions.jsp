<%@ include file="../../commons/commons.jsp" %>
<%@ page import="se.tipspromenad.globals.Constants" %>

<!-- scripts -->
<script type="text/javascript" src="local/js/game-save/question.js"></script>
<script type="text/javascript">
	var saveGameAndQuestions = function() {
		gameController.saveGame(function() {
			questionController.saveQuestions(gameController.getGameId(), function() {
				unlockNextTab();
			});
		});
	};
	
	$(document).ready(function() {
		questionController.init(
			<%= request.getParameter("id") %>,
			'<%= Constants.URL.QUESTION_SAVE_LIST_ACTION %>',
			'<%= Constants.URL.QUESTION_LIST_ACTION %>'
		);
	});
</script>

<!-- html -->
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
		</div>
	</div>
</form>
<c:if test="${empty param.id}">
	<a href="javascript:saveGameAndQuestions();" class="btn">Next</a>
</c:if>
