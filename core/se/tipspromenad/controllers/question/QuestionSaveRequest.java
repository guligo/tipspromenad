package se.tipspromenad.controllers.question;

import java.util.List;

import se.tipspromenad.controllers.RequestBean;
import se.tipspromenad.entities.Answer;
import se.tipspromenad.entities.Question;

public class QuestionSaveRequest extends RequestBean {

	private static final long serialVersionUID = 1L;

	private Long         gameId;
	private Question     question;
	private List<Answer> answers;
	
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

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
}
