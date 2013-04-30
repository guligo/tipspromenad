package se.tipspromenad.controllers.question;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.tipspromenad.globals.Constants;

/**
 * Responsible for actions around question entity.
 * 
 * @author guligo
 */
@Controller
public class QuestionController {

	private static Logger logger = Logger.getLogger(QuestionController.class);

	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.QUESTION_LIST_PAGE)
	public String showQuestionListPage() {
		return Constants.Views.QUESTION_LIST;
	}

	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.QUESTION_SAVE_LIST_ACTION)
	public @ResponseBody void saveQuestions(@RequestBody QuestionListSaveRequest request) {
		logger.info(request.getGameId());
		logger.info(request.getQuestions());
	}

}
