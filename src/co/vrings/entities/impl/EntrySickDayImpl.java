package co.vrings.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import co.vrings.entities.EntrySickDay;

/**
 * Database entity for {@link EntrySickDay}.
 * 
 * @author guligo
 */
@Entity
@Table(name = "sick_day_entries")
public class EntrySickDayImpl extends EntryImpl implements EntrySickDay {
	
	@Column(nullable = false, length = MAX_NOTES_LENGTH)
	private String note;
	
	@Override
	public String getNote() {
		return note;
	}
	
	@Override
	public void setNote(String note) {
		this.note = note;
	}
	
}
