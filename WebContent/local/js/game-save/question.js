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
	
	function _saveQuestions(gameId, callback) {
		if (gameId != null) {
			$.ajax({
				url: QUESTION_SAVE_LIST_ACTION,
				type: 'post',
			    contentType: 'application/json',
			    dataType: 'json',
				data: JSON.stringify({
					gameId: gameId,
					questions: _questions
				}),
			    success: function(response) {
			    	if (response != null) {
			    		_questions = response;
			    	}
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
			    success: function(response) {
			    	if (callback != null) {
			    		callback(response);
			    	}
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
		saveQuestions: function(gameId, callback) {
			_saveQuestions(gameId, callback);
		},
		getQuestions: function(gameId, callback) {
			return _getQuestions(gameId, callback);
		}
	};
	
}();
