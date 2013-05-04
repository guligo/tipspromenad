package se.tipspromenad.services.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import se.tipspromenad.entities.Placemark;

/**
 * Link between business logic and database.
 * 
 * @author guligo
 */
@Component
@Transactional
public class PlacemarkDao {
	
	@Autowired
	private CommonDao commonDao;
	
	public void createPlacemark(Placemark placemark) {
		commonDao.createEntity(placemark);
	}
	
	public void updatePlacemark(Placemark placemark) {
		commonDao.updateEntity(placemark);
	}
	
	public void removePlacemark(Long id) {
		commonDao.removeEntity(Placemark.class, id);
	}
	
}
