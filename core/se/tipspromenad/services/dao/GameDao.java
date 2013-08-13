package se.tipspromenad.services.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import se.tipspromenad.entities.Game;
import se.tipspromenad.services.dao.GameDao;

/**
 * See {@link GameDao}.
 * 
 * @author guligo
 */
@Component
@Transactional
public class GameDao {

	@Autowired
	private CommonDao commonDao;

	public Game getGame(Long id) {
		return (Game) commonDao.getEntity(Game.class, id);
	}

	@SuppressWarnings("unchecked")
	public Game getGameByCode(String code) {
        List<Game> games = commonDao.getEntityManager().createQuery("from Game g where g.code = :code")
			.setParameter("code", code)
			.getResultList();
		if (games != null && !games.isEmpty()) {
			return games.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Game> getGamesByEmail(String email) {
		return commonDao.getEntityManager().createQuery("from Game g where g.creator.email = :email")
			.setParameter("email", email)
			.getResultList();
	}

	public Long createGame(Game game) {
		return commonDao.createEntity(game);
	}

	public void updateGame(Game game) {
		commonDao.updateEntity(game);
	}

	public void removeGame(Long id) {
		commonDao.removeEntity(Game.class, id);
	}

}
