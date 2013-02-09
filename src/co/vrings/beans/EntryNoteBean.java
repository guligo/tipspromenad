package co.vrings.beans;

import co.vrings.controllers.EntriesController;

/**
 * Data transfer bean between {@link EntriesController#createNoteEntry(String, String, String, boolean)} and view.
 * 
 * @author guligo
 */
public class EntryNoteBean extends DataTransferBean {
	
	private String date;
	private String note;
	
	public EntryNoteBean() {
		// used for serialization purposes only
	}
	
	public EntryNoteBean(String date, String note) {
		this.date = date;
		this.note = note;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
}
