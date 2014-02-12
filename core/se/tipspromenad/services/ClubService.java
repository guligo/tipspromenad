package se.tipspromenad.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.tipspromenad.entities.Club;
import se.tipspromenad.services.dao.ClubDao;

/**
 * Provides partial business logic for {@link Club} entity.
 * 
 * @author guligo
 */
@Component
public class ClubService {

	@Autowired
	private ClubDao clubDao;

	public List<Club> getClubs() {
		return clubDao.getClubs();
	}

}
