package co.vrings.beans;

import co.vrings.controllers.EntriesController;
import co.vrings.entities.Entry;
import co.vrings.entities.EntryBody;
import co.vrings.entities.EntryNote;
import co.vrings.entities.EntrySickDay;
import co.vrings.entities.EntrySport;

/**
 * Data transfer bean between {@link EntriesController#getEntries(String)} and view.
 * 
 * @author guligo
 */
public class EntryBean {
	
	public final static int ENTRY_SPORT    = 1;
	public final static int ENTRY_BODY     = 2;
	public final static int ENTRY_SICK_DAY = 3;
	public final static int ENTRY_NOTE     = 4;
	
	private int   type;
	private Entry entry;
	
	public EntryBean() {
		// used for serialization purposes only
	}
	
	public EntryBean(Entry entry) {
		if (entry instanceof EntrySport) {
			type = ENTRY_SPORT;
		} else if (entry instanceof EntryBody) {
			type = ENTRY_BODY;
		} else if (entry instanceof EntrySickDay) {
			type = ENTRY_SICK_DAY;
		} else if (entry instanceof EntryNote) {
			type = ENTRY_NOTE;
		}
		this.entry = entry;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public Entry getEntry() {
		return entry;
	}
	
	public void setEntry(Entry entry) {
		this.entry = entry;
	}
	
}
