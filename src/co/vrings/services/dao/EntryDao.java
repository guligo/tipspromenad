package co.vrings.services.dao;

import java.util.Date;
import java.util.List;

import co.vrings.entities.Entry;
import co.vrings.entities.EntryBody;
import co.vrings.entities.EntryNote;
import co.vrings.entities.EntrySickDay;
import co.vrings.entities.EntrySport;

/**
 * Interface of data-access-object layer component for {@link Entry}.
 * 
 * @author guligo
 */
public interface EntryDao {
	
	public Entry getEntry(Long id);
	
	public List<? extends Entry> getEntriesByUsername(String username);
	
	public List<? extends Entry> getEntriesByUsername(String username, Date date);
			
	public Long createSportEntry(EntrySport sportEntry);
	
	public Long createBodyEntry(EntryBody bodyEntry);
	
	public Long createSickDayEntry(EntrySickDay sickDayEntry);
	
	public Long createNoteEntry(EntryNote noteEntry);
	
	public void removeEntry(Long id);
	
	public int getSequence(String username, Date date);
	
}
