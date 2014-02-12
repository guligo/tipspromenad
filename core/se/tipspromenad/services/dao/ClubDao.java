package se.tipspromenad.services.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import se.tipspromenad.entities.Club;

/**
 * Persistence layer for {@link Club}.
 * 
 * @author guligo
 */
@Component
@Transactional
public class ClubDao {

	@Autowired
	private CommonDao commonDao;

	@SuppressWarnings("unchecked")
	public List<Club> getClubs() {
		return commonDao.getEntityManager().createQuery("from Club").getResultList();
	}

}
