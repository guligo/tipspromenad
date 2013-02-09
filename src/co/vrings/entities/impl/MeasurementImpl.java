package co.vrings.entities.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import co.vrings.entities.Attribute;
import co.vrings.entities.Entry;
import co.vrings.entities.Measurement;

/**
 * Database entity for {@link Measurement}.
 * 
 * @author guligo
 */
@Entity
@Table(name = "measurements")
public class MeasurementImpl extends EntityImpl implements Measurement {
	
	@ManyToOne(optional = false)
	private AttributeImpl attribute;
	@ManyToOne(optional = false)
	private EntryImpl entry;
	@Column(nullable = false)
	private Date date;
	@Column(nullable = false)
	private Double value;
	
	@Override
	public Attribute getAttribute() {
		return attribute;
	}
	
	@Override
	public void setAttribute(Attribute attribute) {
		this.attribute = (AttributeImpl) attribute;
	}
	
	@Override
	public Entry getEntry() {
		return entry;
	}
	
	@Override
	public void setEntry(Entry entry) {
		this.entry = (EntryImpl) entry;
	}
	
	@Override
	public Date getDate() {
		return date;
	}
	
	@Override
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public Double getValue() {
		return value;
	}
	
	@Override
	public void setValue(Double value) {
		this.value = value;
	}
	
}
