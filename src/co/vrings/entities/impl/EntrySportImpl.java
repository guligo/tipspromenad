package co.vrings.entities.impl;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import co.vrings.entities.EntrySport;
import co.vrings.entities.Measurement;
import co.vrings.entities.Sport;

/**
 * Database entity for {@link EntrySport}.
 * 
 * @author guligo
 */
@Entity
@Table(name = "sport_entries")
public class EntrySportImpl extends EntryImpl implements EntrySport {
	
	@ManyToOne(optional = false)
	private SportImpl sport;
	@Column(nullable = false)
	private long duration;
	@Column(length = MAX_NOTES_LENGTH)
	private String note;
	@Column(nullable = false)
	private Integer intensity;
	@Column(nullable = false)
	private Integer mood;
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
	private List<MeasurementImpl> measurements;
	
	@Override
	public Sport getSport() {
		return sport;
	}
	
	@Override
	public void setSport(Sport sport) {
		this.sport = (SportImpl) sport;
	}
	
	@Override
	public long getDuration() {
		return duration;
	}
	
	@Override
	public void setDuration(long duration) {
		this.duration = duration;
	}
	
	@Override
	public String getNote() {
		return note;
	}
	
	@Override
	public void setNote(String note) {
		this.note = note;
	}
	
	@Override
	public Integer getIntensity() {
		return intensity;
	}
	
	@Override
	public void setIntensity(Integer intensity) {
		this.intensity = intensity;
	}
	
	@Override
	public Integer getMood() {
		return mood;
	}
	
	@Override
	public void setMood(Integer mood) {
		this.mood = mood;
	}

	@Override
	public List<MeasurementImpl> getMeasurements() {
		return measurements;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void setMeasurements(List<? extends Measurement> measurements) {
		this.measurements = (List<MeasurementImpl>) measurements;
	}
	
}
