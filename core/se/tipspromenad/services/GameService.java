package se.tipspromenad.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.tipspromenad.entities.Game;
import se.tipspromenad.entities.Placemark;
import se.tipspromenad.entities.Question;
import se.tipspromenad.entities.User;
import se.tipspromenad.services.GameService;
import se.tipspromenad.services.dao.GameDao;
import se.tipspromenad.services.dao.PlacemarkDao;

/**
 * See {@link GameService}.
 * 
 * @author guligo
 */
@Component
public class GameService {

	@Autowired
	private GameDao gameDao;
	@Autowired
	private PlacemarkDao placemarkDao;
	
	public Game getGame(Long id) {
		return gameDao.getGame(id);
	}

	public List<Game> getGamesByUsername(String username) {
		return (List<Game>) gameDao.getGamesByUsername(username);
	}

	public Long saveGame(Long id, User creator, String name, Date date) {
		if (id == null) {
			Game game = new Game();
			game.setCreator(creator);
			game.setCreationDate(new Date());
			game.setName(name);
			game.setDate(date);
			return gameDao.createGame(game);
		} else {
			Game game = gameDao.getGame(id);
			game.setName(name);
			game.setDate(date);
			gameDao.updateGame(game);
			return id;
		}
	}
	
	public void removeGame(Long id) {
		gameDao.removeGame(id);
	}
	
	// placemarks
	public void savePlacemark(Long id, Long questionId, Double latitude, Double longitude) {
		Placemark placemark = new Placemark();
		placemark.setQuestion(new Question(questionId));
		placemark.setLatitude(latitude);
		placemark.setLongitude(longitude);
		if (id == null) {
			placemarkDao.createPlacemark(placemark);
		} else {
			placemark.setId(id);
			placemarkDao.updatePlacemark(placemark);
		}
	}
	
	public void removePlacemark(Long id) {
		placemarkDao.removePlacemark(id);
	}
	// END! placemarks
	
}
