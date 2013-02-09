package co.vrings.services.impl.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.vrings.entities.Attribute;
import co.vrings.entities.AttributeBody;
import co.vrings.entities.AttributePerformance;
import co.vrings.entities.Measurement;
import co.vrings.entities.MeasurementUnit;
import co.vrings.entities.Sport;
import co.vrings.entities.impl.AttributeImpl;
import co.vrings.entities.impl.MeasurementUnitImpl;
import co.vrings.entities.impl.SportImpl;
import co.vrings.services.dao.MeasurementDao;

/**
 * See {@link MeasurementDaoImpl}.
 * 
 * @author guligo
 */
@Component
public class MeasurementDaoImpl implements MeasurementDao {
	
	@Autowired
	private CommonDao commonDao;

	@Override
	@SuppressWarnings("unchecked")
	public List<Measurement> getMeasurements(Long entryId) {
		return commonDao.getEntityManager().createQuery("from MeasurementImpl m where m.entry.id = :entryId")
			.setParameter("entryId", entryId)
			.getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Measurement> getMeasurementsByAttribute(Long attributeId) {
		return commonDao.getEntityManager().createQuery("from MeasurementImpl m where m.attribute.id = :attributeId order by m.date asc, m.id asc")
			.setParameter("attributeId", attributeId)
			.getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Measurement> getMeasurementsByAttribute(Long attributeId, Date start, Date end) {
		return commonDao.getEntityManager().createQuery("from MeasurementImpl m where m.attribute.id = :attributeId and m.date >= :start and m.date <= :end order by m.date asc, m.id asc")
				.setParameter("attributeId", attributeId)
				.setParameter("start", start)
				.setParameter("end", end)
				.getResultList();
	}
	
	@Override
	public MeasurementUnit getMeasurementUnit(Long id) {
		return (MeasurementUnit) commonDao.getEntity(MeasurementUnitImpl.class, id);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<MeasurementUnit> getMeasurementUnits() {
		return (List<MeasurementUnit>) commonDao.getEntityManager().createQuery("from MeasurementUnitImpl")
			.getResultList();		
	}

	@Override
	public Long createMeasurement(Measurement measurement) {
		return commonDao.createEntity(measurement);
	}
	
	@Override
	public Attribute getAttribute(Long id) {
		return (Attribute) commonDao.getEntity(AttributeImpl.class, id);
	}
	
	/**
	 * XXX: Performing two HQL queries here might seem strange, but performing one (combination of these two)
	 * gives unexpected result.  
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<AttributePerformance> getPerformanceAttributes(String username) {
		List<AttributePerformance> result = (List<AttributePerformance>) commonDao.getEntityManager().createQuery("from AttributePerformanceImpl a where a.user is null order by a.id asc")
				.getResultList();
			result.addAll(
				(List<AttributePerformance>) commonDao.getEntityManager().createQuery("from AttributePerformanceImpl a where a.user.username = :username order by a.id asc")
					.setParameter("username", username)
					.getResultList()
			);
			return result;
	}
	
	/**
	 * XXX: Performing two HQL queries here might seem strange, but performing one (combination of these two)
	 * gives unexpected result.  
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<AttributePerformance> getPerformanceAttributes(String username, long sportId) {
		List<AttributePerformance> result = (List<AttributePerformance>) commonDao.getEntityManager().createQuery("from AttributePerformanceImpl a where a.sport.id = :sportId and a.user is null")
			.setParameter("sportId", sportId)			
			.getResultList();
		result.addAll(
			(List<AttributePerformance>) commonDao.getEntityManager().createQuery("from AttributePerformanceImpl a where a.sport.id = :sportId and a.user.username = :username")
				.setParameter("username", username)
				.setParameter("sportId", sportId)			
				.getResultList()
		);
		return result;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<AttributeBody> getBodyAttributes(String username) {
		return (List<AttributeBody>) commonDao.getEntityManager().createQuery("from AttributeBodyImpl a where a.user.username = :username")
			.setParameter("username", username)
			.getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public AttributePerformance getPerformanceAttributeByName(String username, String name) {
		List<AttributePerformance> attributes = commonDao.getEntityManager().createQuery("from AttributePerformanceImpl a where a.user.username = :username and a.name = :name")			
			.setParameter("username", username)
			.setParameter("name", name)
			.getResultList();
		if (attributes != null && attributes.size() > 0) {
			assert(attributes.size() == 1);
			return attributes.get(0);
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public AttributeBody getBodyAttributeByName(String username, String name) {
		List<AttributeBody> attributes = commonDao.getEntityManager().createQuery("from AttributeBodyImpl a where a.user.username = :username and a.name = :name")			
			.setParameter("username", username)
			.setParameter("name", name)
			.getResultList();
		if (attributes != null && attributes.size() > 0) {
			assert(attributes.size() == 1);
			return attributes.get(0);
		}
		return null;
	}
	
	@Override
	public Long createAttribute(Attribute attribute) {
		return commonDao.createEntity(attribute);
	}
	
	@Override
	public Sport getSport(Long id) {
		return (Sport) commonDao.getEntity(SportImpl.class, id);
	}
	
}
