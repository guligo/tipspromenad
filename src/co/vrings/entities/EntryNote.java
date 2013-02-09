package co.vrings.entities;

/**
 * User entry that represents simple note.
 * 
 * @author guligo
 */
public interface EntryNote extends Entry {
	
	public final static int MIN_NOTES_LENGTH = 1;
	public final static int MAX_NOTES_LENGTH = 300;
	
	public String getNote();
	
	public void setNote(String note);
	
}
