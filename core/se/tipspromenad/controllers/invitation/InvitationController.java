package se.tipspromenad.controllers.invitation;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import se.tipspromenad.controllers.question.QuestionController;
import se.tipspromenad.services.GameService;
import se.tipspromenad.services.QuestionService;
import se.tipspromenad.utils.CommonUtils;

/**
 * MVC paradigm controller responsible for actions on invitation page.
 * 
 * @author guligo
 */
public class InvitationController {

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(InvitationController.class);

	@Autowired
	private GameService gameService;
	

}
