package se.tipspromenad.controllers.question;

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
	
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.QUESTION_SAVE_LIST_ACTION)
	public @ResponseBody List<Question> saveQuestions(@RequestBody QuestionListSaveRequest request) {
		return questionService.saveQuestions(request.getGameId(), request.getQuestions());
	}
	
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.QUESTION_LIST_ACTION)
	public @ResponseBody List<Question> getQuestions(@PathVariable Long gameId) {
		return questionService.getQuestionsByGameId(gameId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.PLACEMARK_SAVE_ACTION)
	public void savePlacemark() {
		// TODO
	}
	
}
