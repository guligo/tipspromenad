package se.tipspromenad.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.tipspromenad.entities.Game;
import se.tipspromenad.entities.User;
import se.tipspromenad.entities.impl.GameImpl;
import se.tipspromenad.services.GameService;
import se.tipspromenad.services.dao.GameDao;

/**
 * See {@link GameService}.
 * 
 * @author guligo
 */
@Component
public class GameServiceImpl implements GameService {

	@Autowired
	private GameDao gameDao;

	@Override
	public Game getGame(Long id) {
		return gameDao.getGame(id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Game> getGamesByUsername(String username) {
		return (List<Game>) gameDao.getGamesByUsername(username);
	}

	@Override
	public Long saveGame(Long id, String name, User creator) {
		if (id == null) {
			Game game = new GameImpl();
			game.setName(name);
			game.setCreationDate(new Date());
			game.setCreator(creator);
			return gameDao.createGame(game);
		} else {
			Game game = gameDao.getGame(id);
			game.setName(name);
			gameDao.updateGame(game);
			return id;
		}
	}
	
	@Override
	public void removeGame(Long id) {
		gameDao.removeGame(id);
	}
	
}
