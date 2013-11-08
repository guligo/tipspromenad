package se.tipspromenad.controllers.question;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.tipspromenad.entities.Question;
import se.tipspromenad.globals.Constants;
import se.tipspromenad.services.QuestionService;

/**
 * Responsible for actions around question entity.
 * 
 * @author guligo
 */
@Controller
public class QuestionController {
	
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(QuestionController.class);
	
	@Autowired
	private QuestionService questionService;
	
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.QUESTION_GET_ACTION)
	public @ResponseBody Question getQuestion(@PathVariable Long id) {
		return questionService.getQuestion(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.QUESTION_SAVE_ACTION)
	public @ResponseBody Long saveQuestion(@RequestBody QuestionSaveRequest request) {
		questionService.saveQuestion(request.getGameId(), request.getQuestion());
		return request.getQuestion().getId();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.QUESTION_SAVE_LIST_ACTION)
	public @ResponseBody List<Question> saveQuestions(@RequestBody QuestionListSaveRequest request) {
		return questionService.saveQuestions(request.getGameId(), request.getQuestions());
	}
	
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.QUESTION_LIST_ACTION)
	public @ResponseBody List<Question> getQuestions(@PathVariable Long gameId) {
		List<Question> result = questionService.getQuestionsByGameId(gameId);
		if (result != null) {
			for (Question question : result) {
				question.setSequence(questionService.getSequence(gameId, question.getId()));
			}
		}
		
		Collections.sort(result, new Comparator<Question>() {
			@Override
			public int compare(Question q1, Question q2) {
				if (q1.getSequence() > q2.getSequence()) {
					return 1;
				} else if (q1.getSequence() < q2.getSequence()) {
					return -1;
				}
				return 0;
			}
		});
		return result;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.QUESTION_MOVEUP_ACTION)
	public @ResponseBody Integer moveUpQuestion(@PathVariable Long gameId, @PathVariable Long questionId) {
		questionService.moveUp(gameId, questionId);
		return questionService.getSequence(gameId, questionId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.QUESTION_MOVEDOWN_ACTION)
	public @ResponseBody Integer moveDownQuestion(@PathVariable Long gameId, @PathVariable Long questionId) {
		questionService.moveDown(gameId, questionId);
		return questionService.getSequence(gameId, questionId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.QUESTION_REMOVE_ACTION)
	public @ResponseBody void removeQuestion(@PathVariable Long id) {
		questionService.removeQuestion(id);
	}
	
}
