package co.vrings.services.impl.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.vrings.entities.Entry;
import co.vrings.entities.EntryBody;
import co.vrings.entities.EntryNote;
import co.vrings.entities.EntrySickDay;
import co.vrings.entities.EntrySport;
import co.vrings.entities.impl.EntryImpl;
import co.vrings.services.dao.EntryDao;

/**
 * See {@link EntryDao}.
 * 
 * @author guligo
 */
@Component
public class EntryDaoImpl implements EntryDao {
	
	@Autowired
	private CommonDao commonDao;
	
	@Override
	public Entry getEntry(Long id) {
		return (Entry) commonDao.getEntity(EntryImpl.class, id);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<EntryImpl> getEntriesByUsername(String username) {
		return commonDao.getEntityManager().createQuery("from EntryImpl e where e.user.username = :username order by e.date desc, e.sequence asc")
			.setParameter("username", username)
			.getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<EntryImpl> getEntriesByUsername(String username, Date date) {
		return commonDao.getEntityManager().createQuery("from EntryImpl e where e.user.username = :username and date = :date order by e.date desc, e.sequence asc")
			.setParameter("username", username)
			.setParameter("date", date)
			.getResultList();
	}
	
	@Override
	public Long createSportEntry(EntrySport sportEntry) {
		return commonDao.createEntity(sportEntry);
	}
	
	@Override
	public Long createBodyEntry(EntryBody bodyEntry) {
		return commonDao.createEntity(bodyEntry);
	}
	
	@Override
	public Long createSickDayEntry(EntrySickDay sickDayEntry) {
		return commonDao.createEntity(sickDayEntry);
	}
	
	@Override
	public Long createNoteEntry(EntryNote noteEntry) {
		return commonDao.createEntity(noteEntry);
	}
	
	@Override
	public void removeEntry(Long id) {
		commonDao.removeEntity(EntryImpl.class, id);
	}
	
	@Override
	public int getSequence(String username, Date date) {
		return commonDao.getEntityManager().createQuery("from EntryImpl e where e.user.username = :username and date = :date")
			.setParameter("username", username)
			.setParameter("date", date)
			.getResultList()
			.size();
	}
	
}
