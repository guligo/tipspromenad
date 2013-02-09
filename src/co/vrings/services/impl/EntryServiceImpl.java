package co.vrings.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.vrings.entities.Entry;
import co.vrings.entities.impl.EntryBodyImpl;
import co.vrings.entities.impl.EntryImpl;
import co.vrings.entities.impl.EntryNoteImpl;
import co.vrings.entities.impl.EntrySickDayImpl;
import co.vrings.entities.impl.EntrySportImpl;
import co.vrings.services.EntryService;
import co.vrings.services.dao.EntryDao;
import co.vrings.services.dao.MeasurementDao;
import co.vrings.services.dao.UserDao;

/**
 * See {@link EntryService}.
 * 
 * @author guligo
 */
@Component
@Transactional
public class EntryServiceImpl implements EntryService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private EntryDao entryDao;
	@Autowired
	private MeasurementDao measurementDao;
	
	@Override
	public Entry getEntry(Long id) {
		return entryDao.getEntry(id);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<EntryImpl> getEntriesByUsername(String username) {
		return (List<EntryImpl>) entryDao.getEntriesByUsername(username);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<? extends Entry> getEntriesByUsername(String username, Date date) {
		return (List<EntryImpl>) entryDao.getEntriesByUsername(username, date);
	}
	
	@Override
	public Long createSportEntry(String username, Date date, long sportId, long duration, String note, int intensity, int mood, boolean isPrivate) {
		EntrySportImpl sportEntry = new EntrySportImpl();
		sportEntry.setUser(userDao.getUserByUsername(username));
		sportEntry.setDate(getBeginningOfDay(date));
		sportEntry.setSequence(entryDao.getSequence(username, getBeginningOfDay(date)));
		sportEntry.setSport(measurementDao.getSport(sportId));
		sportEntry.setNote(note);
		sportEntry.setIntensity(intensity);
		sportEntry.setMood(mood);
		sportEntry.setIsPrivate(isPrivate);
		return entryDao.createSportEntry(sportEntry);
	}
	
	@Override
	public Long createBodyEntry(String username, Date date, String note, boolean isPrivate) {		
		EntryBodyImpl bodyEntry = new EntryBodyImpl();
		bodyEntry.setUser(userDao.getUserByUsername(username));
		bodyEntry.setDate(getBeginningOfDay(date));
		bodyEntry.setNote(note);
		bodyEntry.setSequence(entryDao.getSequence(username, getBeginningOfDay(date)));
		bodyEntry.setIsPrivate(isPrivate);
		return entryDao.createBodyEntry(bodyEntry);
	}
	
	@Override
	public Long createSickDayEntry(String username, Date date, String note, boolean isPrivate) {		
		EntrySickDayImpl sickDayEntry = new EntrySickDayImpl();
		sickDayEntry.setUser(userDao.getUserByUsername(username));
		sickDayEntry.setDate(getBeginningOfDay(date));
		sickDayEntry.setSequence(entryDao.getSequence(username, getBeginningOfDay(date)));
		sickDayEntry.setNote(note);
		sickDayEntry.setIsPrivate(isPrivate);
		return entryDao.createSickDayEntry(sickDayEntry);
	}
	
	@Override
	public Long createNoteEntry(String username, Date date, String note, boolean isPrivate) {		
		EntryNoteImpl noteEntry = new EntryNoteImpl();
		noteEntry.setUser(userDao.getUserByUsername(username));
		noteEntry.setDate(getBeginningOfDay(date));
		noteEntry.setNote(note);
		noteEntry.setSequence(entryDao.getSequence(username, getBeginningOfDay(date)));
		noteEntry.setIsPrivate(isPrivate);
		return entryDao.createNoteEntry(noteEntry);
	}
	
	@Override
	public void removeEntry(Long id) {
		entryDao.removeEntry(id);
	}
	
	private Date getBeginningOfDay(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(date.getTime());
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
}
