/**
 * Contains various question entity related functions.
 */
var questionController = function() {
	
	var QUESTION_SAVE_LIST_ACTION = null;
	var QUESTION_GET_LIST_ACTION = null;
	
	var _questions = null;
	
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
					html += '<a href="javascript:questionController.removeQuestion(' + i + ');">[-]</a> ' + (i + 1) + '. ' + _questions[i].text;
				html += '<div>';
				$('#questionContainer').append(html);
			}
		}
	}
	
	function _saveQuestions(gameId, questions) {
		if (gameId != null) {
			$.ajax({
				url: QUESTION_SAVE_LIST_ACTION,
				type: 'post',
			    contentType: 'application/json',
			    dataType: 'json',
				data: JSON.stringify({
					gameId: gameId,
					questions: questions
				}),
			    success: function(response) {
			    	alert('Questions successfully saved');
				}
			});
		}
	}
	
	function _getQuestions(gameId) {
		if (gameId != null) {
			$.ajax({
				url: QUESTION_GET_LIST_ACTION,
				type: 'get',
			    contentType: 'application/json',
			    dataType: 'json',
				data: {
					gameId: _gameId,
				},
			    success: function(response) {
			    	
				}
			});
		}
	}
	
	return {
		init: function(url1, url2) {
			QUESTION_SAVE_LIST_ACTION = url1;
			QUESTION_GET_LIST_ACTION = url2;
			_questions = [];
		},
		addQuestion: function() {
			var callback = function() {
				$('#questionText').val('');
				_renderQuestions();
			};
			_addQuestion(callback);
		},
		removeQuestion: function(index) {
			_removeQuestion(index, _renderQuestions);
		},
		saveQuestions: function(gameId) {
			_saveQuestions(gameId, _questions);
		},
		getQuestions: function(gameId) {
			return _getQuestions(gameId);
		}
	};
	
}();
