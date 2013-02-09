package co.vrings.entities;

import java.util.List;

/**
 * User entry that represents sports activity record for particular day with some additional
 * information.
 * 
 * @author guligo
 */
public interface EntrySport extends Entry {
	
	public final static int MAX_NOTES_LENGTH = 300;
	
	public Sport getSport();
	
	public void setSport(Sport sport);
	
	public long getDuration();
	
	public void setDuration(long duration);
	
	public String getNote();
	
	public void setNote(String note);
	
	public Integer getIntensity();
	
	public void setIntensity(Integer intensity);
	
	public Integer getMood();
	
	public void setMood(Integer mood);
	
	public List<? extends Measurement> getMeasurements();
	
	public void setMeasurements(List<? extends Measurement> measurements);
	
}
