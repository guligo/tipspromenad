package co.vrings.entities;

import java.util.List;

/**
 * Represents measurable attribute.
 * 
 * @author guligo
 */
public interface Attribute extends Entity {
	
	public List<? extends Measurement> getMeasurements();
	
	public void setMeasurements(List<? extends Measurement> measurements);
	
	public String getName();
	
	public void setName(String name);
	
	public MeasurementUnit getMeasurementUnit();
	
	public void setMeasurementUnit(MeasurementUnit measurementUnit);
	
	public User getUser();
	
	public void setUser(User user);
	
}
