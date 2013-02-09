package co.vrings.services;

import java.util.Date;
import java.util.List;

import co.vrings.entities.Attribute;
import co.vrings.entities.AttributeBody;
import co.vrings.entities.AttributePerformance;
import co.vrings.entities.Measurement;
import co.vrings.entities.MeasurementUnit;

/**
 * Interface of service layer component that provides business logic for {@link MeasurementUnit}, {@link Attribute}
 * and {@link Measurement}.
 * 
 * @author guligo
 */
public interface MeasurementService {
	
	public List<Measurement> getMeasurements(Long entryId);
	
	public List<Measurement> getMeasurementsByAttribute(Long attributeId);
	
	public List<Measurement> getMeasurementsByAttribute(Long attributeId, Date date);
	
	public List<Measurement> getMeasurementsByAttribute(Long attributeId, Date start, Date end);
	
	public List<MeasurementUnit> getMeasurementUnits();
	
	public AttributePerformance getPerformanceAttributeByName(String username, String name);
	
	public AttributeBody getBodyAttributeByName(String username, String name);
	
	public List<AttributePerformance> getPerformanceAttributes(String username);
	
	public List<AttributePerformance> getPerformanceAttributes(String username, long sportId);
	
	public List<AttributeBody> getBodyAttributes(String username);
	
	public Long createPerformanceAttribute(String username, long sportId, String name, long measurementUnitId);
	
	public Long createBodyAttribute(String username, String name, long measurementUnitId);
	
	public Long createMeasurement(long entryId, long attributeId, Double value);
	
	public void removeMeasurement(long measurementId);
	
}
