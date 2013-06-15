/**
 * Contains various question entity related functions.
 */
var questionController = function() {
	
	var DIRECTION_UP = 0;
	var DIRECTION_DOWN = 1;
	
	var QUESTION_GET_ACTION = null;
	var QUESTION_SAVE_ACTION = null;
	var QUESTION_GET_LIST_ACTION = null;
	var QUESTION_REMOVE_ACTION = null;
	var QUESTION_MOVEUP_ACTION = null;
	var QUESTION_MOVEDOWN_ACTION = null;
	
	function _getQuestion(questionId, callback) {
		$.ajax({
			url: QUESTION_GET_ACTION.replace('{id}', questionId),
			type: 'get',
		    contentType: 'application/json',
		    dataType: 'json',
		    success: function(question) {
		    	if (callback != null) {
		    		callback(question);
		    	}
			}
		});
	}
	
	function _addQuestion(callback) {
		commonUtils.hideError($('#questionText'));
		if ($('#questionText').val() == null || $('#questionText').val() == '') {
			commonUtils.showError($('#questionText'), 'Question may not be empty');
		} else {
			_questions.push({text: $('#questionText').val()});
			if (callback != null) {
				callback();
			}
		}
	}
	
	function _removeQuestion(id, callback) {
		$.ajax({
			url: QUESTION_REMOVE_ACTION.replace('{id}', id),
			type: 'post',
		    contentType: 'application/json',
		    dataType: 'json',
		    success: function() {
		    	if (callback != null) {
		    		callback();
		    	}
			}
		});
	}
	
	function _renderQuestions() {
		$('#questionContainer').html('');
		if (_questions != null) {
			for (var i = 0; i < _questions.length; i++) {
				html = '<div>';
					html += '<a href="javascript:questionController.showDialog(' + _questions[i].id + ');"><i class="icon-edit"> </i></a> ';
					html += '<a href="javascript:questionController.removeQuestion(' + _questions[i].id + ');"><i class="icon-remove"> </i></a> ';
					html += '<a href="javascript:questionController.moveUpQuestion(' + gameController.getGameId() + ', ' + _questions[i].id + ');"><i class="icon-arrow-up"> </i></a> ';
					html += '<a href="javascript:questionController.moveDownQuestion(' + gameController.getGameId() + ', ' + _questions[i].id + ');"><i class="icon-arrow-down"> </i></a> ';
					html += '#' + _questions[i].sequence + '. ' + _questions[i].text;
					if (_questions[i].answers != null) {
						html += '<ol>';
						for (var j = 0; j < _questions[i].answers.length; j++) {
							html += '<li>' + _questions[i].answers[j].text + ',' + _questions[i].answers[j].correct + '</li>';
						}
						html += '</ol>';
					}
				html += '<div>';
				$('#questionContainer').append(html);
			}
		}
	}
	
	function _saveQuestion(gameId, callback) {
		if (gameId != null) {
			var question = {
				id: $('#questionId').val(),
				text: $('#questionText').val(),
				answers: [
				    {id: $('#answer1Id').val(), text: $('#answer1Text').val(), correct: $('#answer1Correct').is(':checked')},
				    {id: $('#answer2Id').val(), text: $('#answer2Text').val(), correct: $('#answer2Correct').is(':checked')},
				    {id: $('#answer3Id').val(), text: $('#answer3Text').val(), correct: $('#answer3Correct').is(':checked')}
				]};
			
			$.ajax({
				url: QUESTION_SAVE_ACTION,
				type: 'post',
			    contentType: 'application/json',
			    dataType: 'json',
				data: JSON.stringify({
					gameId: gameId,
					question: question
				}),
			    success: function() {
			    	if (callback != null) {
			    		callback();
			    	}
				}
			});
		}
	}
	
	function _getQuestions(gameId, callback) {
		if (gameId != null) {
			$.ajax({
				url: QUESTION_GET_LIST_ACTION.replace('{gameId}', gameId),
				type: 'get',
			    contentType: 'application/json',
			    dataType: 'json',
			    success: function(questions) {
			    	if (callback != null) {
			    		callback(questions);
			    	}
				}
			});
		}
	}
	
	function _moveQuestion(gameId, questionId, direction, callback) {
		if (gameId != null && questionId != null) {
			var url = null;
			if (direction == DIRECTION_UP) {
				url = QUESTION_MOVEUP_ACTION;
			} else if (direction == DIRECTION_DOWN) {
				url = QUESTION_MOVEDOWN_ACTION;
			}
			
			if (url != null) {
				$.ajax({
					url: url.replace('{gameId}', gameId).replace('{questionId}', questionId),
					type: 'post',
				    contentType: 'application/json',
				    dataType: 'json',
				    success: function() {
				    	if (callback != null) {
				    		callback();
				    	}
					}
				});
			}
		}
	}
	
	function _initGUI() {
		$('#addQuestionModal').modal({
			show: false
		});
	}
	
	return {
		init: function(gameId, url1, url2, url3, url4, url5, url6) {
			QUESTION_GET_ACTION = url1;
			QUESTION_SAVE_ACTION = url2;
			QUESTION_GET_LIST_ACTION = url3;
			QUESTION_REMOVE_ACTION = url4;
			QUESTION_MOVEUP_ACTION = url5;
			QUESTION_MOVEDOWN_ACTION = url6;
			
			_initGUI();
			if (gameId == null) {
				_questions = [];
			} else {
				_getQuestions(gameId, function(questions) {
					_questions = questions;
					_renderQuestions();
				});
			}
		},
		showDialog: function(questionId, onhide) {
			if (questionId == null) {
				if (gameController.getGameId() != null) {
					$('#addQuestionModal').modal('show');
				} else {
					gameController.saveGame(function() {
						$('#addQuestionModal').modal('show');
					});
				}
			} else {
				_getQuestion(questionId, function(question) {
					$('#questionId').val(question.id);
					$('#questionText').val(question.text);
					if (question.answers != null) {
						for (var i = 0; i < question.answers.length; i++) {
							$('#answer' + (i + 1) + 'Id').val(question.answers[i].id);
							$('#answer' + (i + 1) + 'Text').val(question.answers[i].text);
							$('#answer' + (i + 1) + 'Correct').attr('checked', question.answers[i].correct);
						}
					}
					$('#addQuestionModal').modal('show');
				});
			}
		},
		hideDialog: function() {
			$('#addQuestionModal').modal('hide');
		},
		getQuestions: function(gameId, callback) {
			_getQuestions(gameId, callback);
		},
		removeQuestion: function(id) {
			_removeQuestion(id, function() {
				_getQuestions(gameController.getGameId(), function(questions) {
					_questions = questions;
					_renderQuestions();
				});
			});
		},
		saveQuestion: function() {
			_saveQuestion(gameController.getGameId(), function() {
				_getQuestions(gameController.getGameId(), function(questions) {
					_questions = questions;
					_renderQuestions();
					questionController.hideDialog();
				});
			});
		},
		moveUpQuestion: function(gameId, questionId) {
			_moveQuestion(gameId, questionId, DIRECTION_UP, function() {
				_getQuestions(gameId, function(questions) {
					_questions = questions;
					_renderQuestions();
				});
			});
		},
		moveDownQuestion: function(gameId, questionId) {
			_moveQuestion(gameId, questionId, DIRECTION_DOWN, function() {
				_getQuestions(gameId, function(questions) {
					_questions = questions;
					_renderQuestions();
				});
			});
		}
	};
	
}();
