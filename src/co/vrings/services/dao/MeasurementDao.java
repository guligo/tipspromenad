package co.vrings.services.dao;

import java.util.Date;
import java.util.List;

import co.vrings.entities.Attribute;
import co.vrings.entities.AttributeBody;
import co.vrings.entities.AttributePerformance;
import co.vrings.entities.Measurement;
import co.vrings.entities.MeasurementUnit;
import co.vrings.entities.Sport;

/**
 * Interface of data-access-object layer component for {@link MeasurementUnit}, {@link Attribute} and
 * {@link Measurement}.
 * 
 * @author guligo
 */
public interface MeasurementDao {
	
	public List<Measurement> getMeasurements(Long entryId);
	
	public List<Measurement> getMeasurementsByAttribute(Long attributeId);
	
	public List<Measurement> getMeasurementsByAttribute(Long attributeId, Date start, Date end);
	
	public MeasurementUnit getMeasurementUnit(Long id);
	
	public List<MeasurementUnit> getMeasurementUnits();
	
	public Long createMeasurement(Measurement measurement);
	
	public Attribute getAttribute(Long id);
	
	public AttributePerformance getPerformanceAttributeByName(String username, String name);
	
	public AttributeBody getBodyAttributeByName(String username, String name);
	
	public List<AttributePerformance> getPerformanceAttributes(String username);
	
	public List<AttributePerformance> getPerformanceAttributes(String username, long sportId);
	
	public List<AttributeBody> getBodyAttributes(String username);
	
	public Long createAttribute(Attribute attribute);
	
	public Sport getSport(Long id);
	
}
