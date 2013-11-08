package se.tipspromenad.controllers.question;

import se.tipspromenad.controllers.RequestBean;
import se.tipspromenad.entities.Question;

public class QuestionSaveRequest extends RequestBean {

	private static final long serialVersionUID = 1L;

	private Long         gameId;
	private Question     question;

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Question getQuestion() {
		return this.question;
	}

}
