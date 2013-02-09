package co.vrings.entities;

import java.util.Set;

/**
 * User entry that represents measurements of one or many body attributes like weight, hip, waist size, etc.
 * 
 * @author guligo
 */
public interface EntryBody extends Entry {

	public String getNote();
	
	public void setNote(String note);
	
	public Set<? extends Measurement> getMeasurements();
	
	public void setMeasurements(Set<? extends Measurement> measurements);
	
}
