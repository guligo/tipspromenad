package co.vrings.entities;

/**
 * User entry that represents a sick day.
 * 
 * @author guligo
 */
public interface EntrySickDay extends Entry {
	
	public final static int MAX_NOTES_LENGTH = 300;
	
	public String getNote();
	
	public void setNote(String note);
	
}
