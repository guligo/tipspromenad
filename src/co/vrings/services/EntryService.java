package co.vrings.services;

import java.util.Date;
import java.util.List;

import co.vrings.entities.Entry;

/**
 * Interface of service layer component that provides business logic for {@link Entry}.
 * 
 * @author guligo
 */
public interface EntryService {
	
	public Entry getEntry(Long id);
	
	public List<? extends Entry> getEntriesByUsername(String username);
	
	public List<? extends Entry> getEntriesByUsername(String username, Date date);
	
	public Long createSportEntry(String username, Date date, long sportId, long duration, String note, int intensity, int mood, boolean isPrivate);
	
	public Long createBodyEntry(String username, Date date, String note, boolean isPrivate);
	
	public Long createSickDayEntry(String username, Date date, String note, boolean isPrivate);
	
	public Long createNoteEntry(String username, Date date, String note, boolean isPrivate);
	
	public void removeEntry(Long id);
	
}
