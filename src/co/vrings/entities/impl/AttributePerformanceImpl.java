package co.vrings.entities.impl;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import co.vrings.entities.AttributePerformance;
import co.vrings.entities.Sport;

/**
 * Database entity {@link AttributePerformance}.
 * 
 * @author guligo
 */
@Entity
@Table(name = "performance_attributes")
public class AttributePerformanceImpl extends AttributeImpl implements AttributePerformance {
	
	@ManyToOne(optional = false)
	private SportImpl sport;
		
	@Override
	public Sport getSport() {
		return sport;
	}
	
	@Override
	public void setSport(Sport sport) {
		this.sport = (SportImpl) sport;
	}
	
}
