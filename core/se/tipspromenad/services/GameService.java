package se.tipspromenad.services;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.tipspromenad.entities.Game;
import se.tipspromenad.entities.Placemark;
import se.tipspromenad.entities.Question;
import se.tipspromenad.entities.User;
import se.tipspromenad.entities.enums.GameState;
import se.tipspromenad.services.GameService;
import se.tipspromenad.services.dao.GameDao;
import se.tipspromenad.services.dao.PlacemarkDao;
import se.tipspromenad.services.dao.QuestionDao;
import se.tipspromenad.utils.CommonUtils;

/**
 * See {@link GameService}.
 * 
 * @author guligo
 */
@Component
public class GameService {
	
	private static Logger logger = Logger.getLogger(GameService.class);
	
	@Autowired
	private GameDao gameDao;
	@Autowired
	private QuestionDao questionDao;
	@Autowired
	private PlacemarkDao placemarkDao;
	
	public Game getGame(Long id) {
		return gameDao.getGame(id);
	}

	public List<Game> getGamesByEmail(String email) {
		return (List<Game>) gameDao.getGamesByEmail(email);
	}

	public Long saveGame(Long id, User creator, String name, Date date) {
		logger.debug("Saving game with id = " + id);
		
		if (id == null) {
			Game game = new Game();
			game.setCreator(creator);
			game.setCreationDate(new Date());
			game.setName(name);
			game.setDate(date);
			game.setCode(CommonUtils.generateCode());
			game.setState(GameState.UNDER_CONSTRUCTION);
			return gameDao.createGame(game);
		} else {
			Game game = gameDao.getGame(id);
			game.setName(name);
			game.setDate(date);
			gameDao.updateGame(game);
			return id;
		}
	}

	public Long saveGame(Long id, GameState state) {
		if (id == null) {
			throw new RuntimeException("This operation is not supported for entity which is not in database");
		} else {
			Game game = gameDao.getGame(id);
			game.setState(state);
			gameDao.updateGame(game);
			return id;
		}
	}

	public Long saveGame(Long id, Set<Question> questions) {
		if (id == null) {
			throw new RuntimeException("This operation is not supported for entity which is not in database");
		} else {
			Game game = gameDao.getGame(id);
			if (game.getQuestions() == null) {
				game.setQuestions(new HashSet<Question>());
			}
			
			if (questions != null) {
				for (Question question : questions) {
					questionDao.createQuestion(question);
					game.getQuestions().add(question);
				}
			}
			gameDao.updateGame(game);
			
			/*
			if (game.getQuestions() != null) {
				int i = 1;
				for (Question question : game.getQuestions()) {
					questionDao.setSequence(id, question.getId(), i);
					i++;
				}
			}
			*/
			
			return id;
		}
	}

	public void removeGame(Long id) {
		Game game = gameDao.getGame(id);
		
		// remove placemarks
		if (game.getQuestions() != null) {
			for (Question question : game.getQuestions()) {
				Placemark placemark = placemarkDao.getPlacemarkByGameAndQuestionId(id, question.getId());
				if (placemark != null) {
					placemarkDao.removePlacemark(placemark.getId());
				}
			}
		}
		
		// remove game
		gameDao.removeGame(id);
	}

}
