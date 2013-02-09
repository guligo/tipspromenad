package co.vrings.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.vrings.entities.AttributeBody;
import co.vrings.entities.AttributePerformance;
import co.vrings.entities.Entry;
import co.vrings.entities.Measurement;
import co.vrings.entities.MeasurementUnit;
import co.vrings.entities.impl.AttributeBodyImpl;
import co.vrings.entities.impl.AttributePerformanceImpl;
import co.vrings.entities.impl.MeasurementImpl;
import co.vrings.services.MeasurementService;
import co.vrings.services.dao.EntryDao;
import co.vrings.services.dao.MeasurementDao;
import co.vrings.services.dao.UserDao;
import co.vrings.services.impl.dao.CommonDao;

/**
 * See {@link MeasurementService}.
 * 
 * @author guligo
 */
@Component
@Transactional
public class MeasurementServiceImpl implements MeasurementService {
	
	@Autowired
	private CommonDao commonDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private EntryDao entryDao;
	@Autowired
	private MeasurementDao measurementDao;
	
	@Override
	public List<Measurement> getMeasurements(Long entryId) {
		return measurementDao.getMeasurements(entryId);
	}
	
	@Override
	public List<Measurement> getMeasurementsByAttribute(Long attributeId) {
		return measurementDao.getMeasurementsByAttribute(attributeId);
	}
	
	@Override
	public List<Measurement> getMeasurementsByAttribute(Long attributeId, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date start = calendar.getTime();
		
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		Date end = calendar.getTime();
		
		return measurementDao.getMeasurementsByAttribute(attributeId, start, end);
	}
	
	@Override
	public List<Measurement> getMeasurementsByAttribute(Long attributeId, Date start, Date end) {
		return measurementDao.getMeasurementsByAttribute(attributeId, start, end);
	}
	
	@Override
	public List<MeasurementUnit> getMeasurementUnits() {
		return measurementDao.getMeasurementUnits();
	}
	
	@Override
	public List<AttributePerformance> getPerformanceAttributes(String username) {
		return measurementDao.getPerformanceAttributes(username);
	}
	
	@Override
	public AttributePerformance getPerformanceAttributeByName(String username, String name) {
		return measurementDao.getPerformanceAttributeByName(username, name);
	}
	
	@Override
	public AttributeBody getBodyAttributeByName(String username, String name) {
		return measurementDao.getBodyAttributeByName(username, name);
	}
	
	@Override
	public List<AttributePerformance> getPerformanceAttributes(String username, long sportId) {
		return measurementDao.getPerformanceAttributes(username, sportId);
	}
	
	@Override
	public List<AttributeBody> getBodyAttributes(String username) {
		return measurementDao.getBodyAttributes(username);
	}
	
	@Override
	public Long createPerformanceAttribute(String username, long sportId, String name, long measurementUnitId) {
		AttributePerformanceImpl attribute = new AttributePerformanceImpl();
		attribute.setSport(measurementDao.getSport(sportId));
		attribute.setMeasurementUnit(measurementDao.getMeasurementUnit(measurementUnitId));
		attribute.setName(name);
		attribute.setUser(userDao.getUserByUsername(username));
		return measurementDao.createAttribute(attribute);
	}
	
	@Override
	public Long createBodyAttribute(String username, String name, long measurementUnitId) {
		AttributeBodyImpl attribute = new AttributeBodyImpl();
		attribute.setMeasurementUnit(measurementDao.getMeasurementUnit(measurementUnitId));
		attribute.setName(name);
		attribute.setUser(userDao.getUserByUsername(username));
		return measurementDao.createAttribute(attribute);
	}
	
	@Override
	public Long createMeasurement(long entryId, long attributeId, Double value) {
		Entry entry = entryDao.getEntry(entryId);
		
		MeasurementImpl measurement = new MeasurementImpl();
		measurement.setAttribute(measurementDao.getAttribute(attributeId));
		measurement.setEntry(entry);
		measurement.setDate(entry.getDate());
		measurement.setValue(value);
		return measurementDao.createMeasurement(measurement);
	}
	
	@Override
	public void removeMeasurement(long measurementId) {
		commonDao.removeEntity(MeasurementImpl.class, measurementId);
	}
	
}
