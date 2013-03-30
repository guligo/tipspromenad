package se.tipspromenad.services.dao;

import java.util.List;

import se.tipspromenad.entities.Game;

/**
 * Interface of data-access-object layer component for {@link Game}.
 * 
 * @author guligo
 */
public interface GameDao {

	public Game getGame(Long id);

	public List<? extends Game> getGamesByUsername(String username);

	public Long createGame(Game game);

	public void updateGame(Game game);

	public void removeGame(Long id);

}
