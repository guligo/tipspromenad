package se.tipspromenad.services;

import java.util.List;

import se.tipspromenad.entities.Game;
import se.tipspromenad.entities.User;

/**
 * Interface of service layer component that provides business logic for
 * {@link Game}.
 * 
 * @author guligo
 */
public interface GameService {

	public Game getGame(Long id);

	public List<Game> getGamesByUsername(String username);

	public Long saveGame(Long id, String name, User creator);
	
	public void removeGame(Long id);
	
}
