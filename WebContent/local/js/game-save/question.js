/**
 * Contains various question entity related functions.
 */
var questionController = function() {
	
	var QUESTION_SAVE_ACTION = null;
	var QUESTION_GET_LIST_ACTION = null;
	
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
	
	function _removeQuestion(index, callback) {
		_questions.splice(index, 1);
		if (callback != null) {
			callback();
		}
	}
	
	function _renderQuestions() {
		$('#questionContainer').html('');
		if (_questions != null) {
			for (var i = 0; i < _questions.length; i++) {
				html = '<div>';
					html += '<a href="javascript:questionController.editQuestion(' + i + ');"><i class="icon-edit"> </i></a> ';
					html += '<a href="javascript:questionController.removeQuestion(' + i + ');"><i class="icon-remove"> </i></a> ';
					html += '<a href="javascript:questionController.moveUp(' + i + ');"><i class="icon-arrow-up"> </i></a> ';
					html += '<a href="javascript:questionController.moveDown(' + i + ');"><i class="icon-arrow-down"> </i></a> ';
					html += (i + 1) + '. ' + _questions[i].text;
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
				text: $('#questionText').val(),
				answers: [
				    {text: $('#answer1Text').val(), correct: $('#answer1Correct').is(':checked')},
				    {text: $('#answer2Text').val(), correct: $('#answer2Correct').is(':checked')},
				    {text: $('#answer3Text').val(), correct: $('#answer3Correct').is(':checked')}
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
	
	function _initGUI() {
		$('#addQuestionModal').modal({
			show: false
		});
	}
	
	return {
		init: function(gameId, url1, url2) {
			QUESTION_SAVE_ACTION = url1;
			QUESTION_GET_LIST_ACTION = url2;
			
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
		showDialog: function() {
			if (gameController.getGameId() != null) {
				$('#addQuestionModal').modal('show');
			} else {
				gameController.saveGame(function() {
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
		removeQuestion: function(index) {
			_removeQuestion(index, _renderQuestions);
		},
		saveQuestion: function() {
			_saveQuestion(gameController.getGameId(), function() {
				_getQuestions(gameController.getGameId(), function(questions) {
					_questions = questions;
					_renderQuestions();
					questionController.hideDialog();
				});
			});
		}
	};
	
}();
