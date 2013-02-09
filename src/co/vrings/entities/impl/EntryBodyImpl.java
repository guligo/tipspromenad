package co.vrings.entities.impl;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import co.vrings.entities.EntryBody;
import co.vrings.entities.Measurement;

/**
 * Database entity for {@link EntryBody}.
 * 
 * @author guligo
 */
@Entity
@Table(name = "body_entries")
public class EntryBodyImpl extends EntryImpl implements EntryBody {
	
	@Column(nullable = false, length = 300)
	private String note;
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Set<MeasurementImpl> measurements;
	
	@Override
	public String getNote() {
		return note;
	}
	
	@Override
	public void setNote(String note) {
		this.note = note;
	}
	
	@Override
	public Set<? extends Measurement> getMeasurements() {
		return measurements;
	}
		
	@Override
	@SuppressWarnings("unchecked")
	public void setMeasurements(Set<? extends Measurement> measurements) {
		this.measurements = (Set<MeasurementImpl>) measurements;
	}
	
}
