package se.tipspromenad.services.impl.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import se.tipspromenad.entities.Game;
import se.tipspromenad.entities.impl.GameImpl;
import se.tipspromenad.services.dao.GameDao;

/**
 * See {@link GameDao}.
 * 
 * @author guligo
 */
@Component
@Transactional
public class GameDaoImpl implements GameDao {

	@Autowired
	private CommonDao commonDao;

	@Override
	public Game getGame(Long id) {
		return (Game) commonDao.getEntity(GameImpl.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<? extends Game> getGamesByUsername(String username) {
		return commonDao.getEntityManager().createQuery("from GameImpl g where g.creator.username = :username")
			.setParameter("username", username)
			.getResultList();
	}

	@Override
	public Long createGame(Game game) {
		return commonDao.createEntity(game);
	}

	@Override
	public void updateGame(Game game) {
		commonDao.updateEntity(game);
	}

	@Override
	public void removeGame(Long id) {
		commonDao.removeEntity(GameImpl.class, id);
	}

}
