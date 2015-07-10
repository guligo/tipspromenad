package se.tipspromenad.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.tipspromenad.entities.Answer;
import se.tipspromenad.entities.Game;
import se.tipspromenad.entities.Question;
import se.tipspromenad.entities.enums.QuestionType;
import se.tipspromenad.services.dao.GameDao;
import se.tipspromenad.services.dao.QuestionDao;

/**
 * @author guligo
 * @author pavelefimov
 */
@Component
public class QuestionService {
	
	private static Logger logger = Logger.getLogger(QuestionService.class);
	
	@Autowired
	private GameDao gameDao;
	
	@Autowired
	private QuestionDao questionDao;
	
	public Question getQuestion(Long id) {
		return questionDao.getQuestion(id);
	}
	
	public Question saveQuestion(Long gameId, Question question) {
		// avoiding detached entity
		if (question.getId() != null) {
			Question questionInDb = questionDao.getQuestion(question.getId());
			if (question.getAnswers() != null
				&& questionInDb.getAnswers() != null) {
				for (int i = 0; i < questionInDb.getAnswers().size(); i++) {
					Answer answer = question.getAnswers().get(i);
					Answer answerInDb = questionInDb.getAnswers().get(i);
					answerInDb.setText(answer.getText());
					answerInDb.setCorret(answer.getCorrect());
					questionDao.updateAnswer(answerInDb);
				}
			}
			questionInDb.setText(question.getText());
			questionDao.updateQuestion(questionInDb);
			return questionInDb;
		} else {
			question.setType(QuestionType.QUESTION);
			if (question.getAnswers() != null) {
				for (int i = 0; i < question.getAnswers().size(); i++) {
					questionDao.createAnswer(question.getAnswers().get(i));
				}
			}
			questionDao.createQuestion(question);
			Game game = gameDao.getGame(gameId);
			game.getQuestions().add(question);
			gameDao.updateGame(game);
			
			Integer sequence = questionDao.getSequence(gameId);
			if (sequence != null) {
				questionDao.setSequence(gameId, question.getId(), sequence + 1);
			} else {
				questionDao.setSequence(gameId, question.getId(), 1);
			}
			return question;
		}
	}
	
	public List<Question> saveQuestions(Long gameId, Question[] questions) {
		logger.debug("Saving questions for game with id = " + gameId);
		
		List<Question> result = new ArrayList<Question>();
		if (gameId != null && questions != null) {
			for (Question question : questions) {
				result.add(saveQuestion(gameId, question));
			}
		}
		return result;
	}
	
	public List<Question> getQuestionsByGameId(Long gameId) {
		logger.debug("Retrieving question list for game with id = " + gameId);
		
		List<Question> questions = questionDao.getQuestionsByGameId(gameId);
		logger.debug("Totally " + questions.size() + " questions retrieved");
		return questions;
	}
	
	public void removeQuestion(Long id) {
		logger.debug("Removing question with id = " + id);
		questionDao.removeQuestion(id);
	}

	public Integer getSequence(Long gameId, Long questionId) {
		logger.info("Getting sequence for game with id = " + gameId + " and question with id = " + questionId);
		return questionDao.getSequence(gameId, questionId);
	}
	
	public void moveUp(Long gameId, Long questionId) {
		questionDao.moveUp(gameId, questionId);
	}
	
	public void moveDown(Long gameId, Long questionId) {
		questionDao.moveDown(gameId, questionId);
	}
	
}
