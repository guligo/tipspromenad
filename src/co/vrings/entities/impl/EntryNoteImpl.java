package co.vrings.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import co.vrings.entities.EntryNote;

/**
 * Database entity for {@link EntryNote}.
 * 
 * @author guligo
 */
@Entity
@Table(name = "note_entries")
public class EntryNoteImpl extends EntryImpl implements EntryNote {
	
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
