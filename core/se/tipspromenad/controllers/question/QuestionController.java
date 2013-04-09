package se.tipspromenad.controllers.question;

import java.util.List;

import org.springframework.stereotype.Controller;
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
	
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.QUESTION_LIST_PAGE)
	public String showQuestionListPage() {
		return Constants.Views.QUESTION_LIST;
	}
	
	
}
