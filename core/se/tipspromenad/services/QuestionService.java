package se.tipspromenad.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.tipspromenad.entities.Game;
import se.tipspromenad.entities.Question;
import se.tipspromenad.services.dao.QuestionDao;

/**
 * @author guligo
 */
@Component
public class QuestionService {
	
	@Autowired
	private QuestionDao questionDao;
	
	public void saveQuestions(Long gameId, Question[] questions) {
		if (gameId != null && questions != null) {
			for (Question question : questions) {
				Game game = new Game();
				game.setId(gameId);
				question.setGame(game);
				questionDao.createQuestion(question);
			}
		}
	}
	
	public List<Question> getQuestionsByGameId(Long gameId) {
		return questionDao.getQuestionsByGameId(gameId);
	}
	
}
