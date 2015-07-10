package se.tipspromenad.controllers.question;

import se.tipspromenad.controllers.RequestBean;
import se.tipspromenad.entities.Question;

/**
 * See {@link QuestionController#saveQuestions()}.
 * 
 * @author guligo
 * @author pavelefimov
 */
public class QuestionListSaveRequest extends RequestBean {

	private static final long serialVersionUID = 1L;

	private Long       gameId;
	private Question[] questions;

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public void setQuestions(Question[] questions) {
		this.questions = questions;
	}

	public Question[] getQuestions() {
		return this.questions;
	}

}
