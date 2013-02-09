package co.vrings.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import co.vrings.entities.MeasurementUnit;

/**
 * Database entity for {@link MeasurementUnit}.
 * 
 * @author guligo
 */
@Entity
@Table(name = "measurement_units")
public class MeasurementUnitImpl extends EntityImpl implements MeasurementUnit {
	
	@Column(nullable = false, unique = true)
	private String name;
	@Column(nullable = false, name = "abbreviation", unique = true)
	private String abbreviation;
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	
	@Override
	public String getAbbreviation() {
		return abbreviation;
	}
	
}
