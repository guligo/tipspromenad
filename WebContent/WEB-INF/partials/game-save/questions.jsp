<%@ page import="se.tipspromenad.globals.Constants" %>

<%@ include file="../../commons/commons.jsp" %>

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
			'<%= Constants.URL.QUESTION_GET_ACTION %>',
			'<%= Constants.URL.QUESTION_SAVE_ACTION %>',
			'<%= Constants.URL.QUESTION_LIST_ACTION %>',
			'<%= Constants.URL.QUESTION_REMOVE_ACTION %>',
			'<%= Constants.URL.QUESTION_MOVEUP_ACTION %>',
			'<%= Constants.URL.QUESTION_MOVEDOWN_ACTION %>'
		);
	});
</script>

<!-- html -->
<form id="questionForm" class="form-horizontal">
	<div class="control-group">
		<div id="questionContainer" class="controls">
			<spring:message code="gamesave.questions.empty" />
		</div>
	</div>
	<div class="control-group">
		<div class="controls">
			<a href="javascript:questionController.showDialog();" role="button" class="btn">
				<spring:message code="gamesave.questions.addquestion.label" />
			</a>
		</div>
	</div>
</form>
<c:if test="${empty param.id}">
	<a href="javascript:unlockNextTab();" class="btn">
		<spring:message code="gamesave.questions.next.label" />
	</a>
</c:if>

<!-- dialogs -->
<div id="addQuestionModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="addQuestionModalLabel" aria-hidden="true">
  	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h3 id="myModalLabel">
			<spring:message code="gamesave.questions.savequestion.label" />
		</h3>
	</div>
	<div class="modal-body form-horizontal">
		<div class="control-group">
			<input id="questionId" type="hidden" />
			<label for="questionText" class="control-label">
				<spring:message code="gamesave.questions.savequestion.question" />
			</label>
			<div class="controls">
				<textarea id="questionText" placeholder="Enter question..." style="width: 300px; height: 100px;"></textarea>
			</div>
		</div>
		<div class="control-group">
			<input id="answer1Id" type="hidden" />
			<label for="answer1Text" class="control-label radio">
				<input id="answer1Correct" name="answerCorrect" type="radio" style="margin-left: 125px;" />1.&nbsp;
			</label>
			<div class="controls">
				<input type="text" id="answer1Text" placeholder="Enter answer..."></input>
			</div>
		</div>
		<div class="control-group">
			<input id="answer2Id" type="hidden" />
			<label for="answer2Text" class="control-label radio">
				<input id="answer2Correct" name="answerCorrect" type="radio" style="margin-left: 125px;" />X.&nbsp;
			</label>
			<div class="controls">
				<input type="text" id="answer2Text" placeholder="Enter answer..."></input>
			</div>
		</div>
		<div class="control-group">
			<input id="answer3Id" type="hidden" />
			<label for="answer3Text" class="control-label radio">
				<input id="answer3Correct" name="answerCorrect" type="radio" style="margin-left: 125px;" />2.&nbsp;
			</label>
			<div class="controls">
				<input type="text" id="answer3Text" placeholder="Enter answer..."></input>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<a href="javascript:questionController.saveQuestion();" class="btn btn-primary">
			<spring:message code="gamesave.questions.savequestion.save.label" />
		</a>
		<button class="btn" data-dismiss="modal" aria-hidden="true">
			<spring:message code="gamesave.questions.savequestion.close.label" />
		</button>
	</div>
</div>
