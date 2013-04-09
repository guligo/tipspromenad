/**
 * Contains various question entity related functions.
 */
var questionController = function() {
	
	var _questions = null;
	
	function _addQuestion(callback) {
		_questions.push({text: 'Abrakadabra?'});
		if (callback != null) {
			callback();
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
					html += i + ' ' + _questions[i].text + ' <a href="javascript:questionController.removeQuestion(' + i + ');">[-]</a>';
				html += '<div>';
				$('#questionContainer').append(html);
			}
		}
	}
	
	return {
		init: function() {
			_questions = [];
		},
		addQuestion: function() {
			_addQuestion(_renderQuestions);
		},
		removeQuestion: function(index) {
			_removeQuestion(index, _renderQuestions);
		},
		saveQuestions: function() {
			alert('Save questions!');
		}
	};
	
}();
