package se.tipspromenad.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.tipspromenad.entities.Game;
import se.tipspromenad.entities.Placemark;
import se.tipspromenad.entities.Question;
import se.tipspromenad.services.dao.GameDao;
import se.tipspromenad.services.dao.PlacemarkDao;
import se.tipspromenad.services.dao.QuestionDao;

/**
 * @author guligo
 */
@Component
public class QuestionService {
	
	private static Logger logger = Logger.getLogger(QuestionService.class);
	
	@Autowired
	private GameDao gameDao;
	
	@Autowired
	private QuestionDao questionDao;
	
	@Autowired
	private PlacemarkDao placemarkDao;
	
	public List<Question> saveQuestions(Long gameId, Question[] questions) {
		logger.debug("Saving questions for game with id = " + gameId);
		
		List<Question> result = new ArrayList<Question>();
		if (gameId != null && questions != null) {
			for (Question question : questions) {
				// avoiding detached entity
				if (question.getId() != null) {
					Question questionInDb = questionDao.getQuestion(question.getId());
					questionInDb.setText(question.getText());
					questionDao.updateQuestion(questionInDb);
					result.add(questionInDb);
				} else {
					questionDao.createQuestion(question);
					Game game = gameDao.getGame(gameId);
					game.getQuestions().add(question);
					gameDao.updateGame(game);
					result.add(question);
					
					// FIXME: So f*****g ugly!
					if (!questionDao.sequenceColumnExist()) {
						questionDao.createSequenceColumn();
					}
					
					Integer sequence = questionDao.getSequence(gameId);
					if (sequence != null) {
						questionDao.setSequence(gameId, question.getId(), sequence + 1L);
					} else {
						questionDao.setSequence(gameId, question.getId(), 1L);
					}
				}
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
	
	public Placemark getPlacemarkByGameAndQuestionId(Long gameId, Long questionId) {
		logger.debug("Retrieving placemarks for game with id = " + gameId + " and question with id = " + questionId);
		
		Placemark placemark = placemarkDao.getPlacemarkByGameAndQuestionId(gameId, questionId);
		return placemark;
	}
	
	public List<Placemark> getPlacemarksByGameId(Long gameId) {
		logger.debug("Retrieving placemark list for game with id = " + gameId);
		
		List<Placemark> placemarks = placemarkDao.getPlacemarksByGameId(gameId);
		logger.debug("Totally " + placemarks.size() + " placemarks retrieved");
		return placemarks;
	}
	
	public Placemark savePlacemark(Long id, Long gameId, Long questionId, Double latitude, Double longitude) {
		logger.debug("Saving placemark with id = " + id + ", gameId = " + gameId + ", questionId = " + questionId + ", latitude = " + latitude + " and longitude = " + longitude);
		
		Placemark placemark;
		if (id == null) {
			placemark = new Placemark();
			placemark.setQuestion(new Question(questionId));
			placemark.setGame(new Game(gameId));
			placemark.setLatitude(latitude);
			placemark.setLongitude(longitude);
			placemarkDao.createPlacemark(placemark);
		} else {
			placemark = placemarkDao.getPlacemark(id);
			placemark.setLatitude(latitude);
			placemark.setLongitude(longitude);
			placemarkDao.updatePlacemark(placemark);
		}
		return placemark;
	}
	
}
