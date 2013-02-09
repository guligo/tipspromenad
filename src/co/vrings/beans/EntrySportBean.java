package co.vrings.beans;

import co.vrings.controllers.EntriesController;

/**
 * Data transfer bean between {@link EntriesController#createSportEntry(String, long, long, String, Integer, Integer, boolean)} and view.
 * 
 * @author guligo
 */
public class EntrySportBean extends DataTransferBean {
	
	private String  date;
	private long    sportId;
	private Long    duration;
	private String  note;
	private Integer intensity;
	private Integer mood;
	
	public EntrySportBean() {
		// used for serialization purposes only
	}
	
	public EntrySportBean(String date, long sportId, Long duration, String note, Integer intensity, Integer mood) {
		this.date      = date;
		this.sportId   = sportId;
		this.duration  = duration;
		this.note      = note;
		this.intensity = intensity;
		this.mood      = mood;		
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public long getSportId() {
		return sportId;
	}
	
	public void setSportId(long sportId) {
		this.sportId = sportId;
	}
	
	public Long getDuration() {
		return duration;
	}
	
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public Integer getIntensity() {
		return intensity;
	}
	
	public void setIntensity(Integer intensity) {
		this.intensity = intensity;
	}
	
	public Integer getMood() {
		return mood;
	}
	
	public void setMood(Integer mood) {
		this.mood = mood;
	}
	
}
