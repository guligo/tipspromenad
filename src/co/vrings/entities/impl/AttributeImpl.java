package co.vrings.entities.impl;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import co.vrings.entities.Attribute;
import co.vrings.entities.Measurement;
import co.vrings.entities.MeasurementUnit;
import co.vrings.entities.User;

/**
 * Database entity for {@link Attribute}.
 * 
 * @author guligo
 */
@Entity
@Table(name = "attributes")
@Inheritance(strategy = InheritanceType.JOINED)
public class AttributeImpl extends EntityImpl implements Attribute {
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany	
	private List<MeasurementImpl> measurements;
	@Column(nullable = false)
	private String name;
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private MeasurementUnitImpl measurementUnit;
	@ManyToOne(optional = true)
	private UserImpl user;
	
	@Override
	public List<MeasurementImpl> getMeasurements() {
		return measurements;
	}
	
	@Override
	@SuppressWarnings("unchecked")	
	public void setMeasurements(List<? extends Measurement> measurements) {
		this.measurements = (List<MeasurementImpl>) measurements;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public MeasurementUnit getMeasurementUnit() {
		return measurementUnit;
	}
	
	@Override
	public void setMeasurementUnit(MeasurementUnit measurementUnit) {
		this.measurementUnit = (MeasurementUnitImpl) measurementUnit;
	}
	
	@Override
	@JsonIgnore
	public UserImpl getUser() {
		return user;
	}
	
	@Override
	public void setUser(User user) {
		this.user = (UserImpl) user;
	}
	
}
