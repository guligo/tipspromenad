package se.tipspromenad.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.tipspromenad.entities.Play;
import se.tipspromenad.services.dao.PlayDao;

/**
 * Partially provides business-logic related to {@link Play}.
 * 
 * @author guligo
 * @author pavelefimov
 */
@Component
public class PlayService {
	
	@Autowired
	private PlayDao playDao;
	
	public Play getPlay(Long id) {
		return null;
	}
	
	public List<Play> getPlaysByUser(Long userId) {
		return null;
	}
	
	public List<Play> getPlaysByGame(Long gameId) {
		return null;
	}
	
	public void createPlay(Long userId, Date start) {
		
	}
	
	public void updatePlay(Long id, Date end) {
		
	}
	
	public void removePlay(Long id) {
		
	}
	
}
