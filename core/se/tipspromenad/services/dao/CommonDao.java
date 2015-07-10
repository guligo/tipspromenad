package se.tipspromenad.services.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import se.tipspromenad.entities.Entity;


/**
 * Generic data-access-object layer class that provides common functions.
 * 
 * @author guligo
 * @author pavelefimov
 */
@Component
public class CommonDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Long createEntity(Entity entity) {
		entityManager.persist(entity);
		return entity.getId();
	}
	
	public void updateEntity(Entity entity) {
		entityManager.merge(entity);
		entityManager.flush();
	}
	
	public Entity getEntity(Class<? extends Entity> clazz, Long entityId) {
		return entityManager.find(clazz, entityId);
	}
	
	public void removeEntity(Class<? extends Entity> clazz, Long entityId) {
		entityManager.remove(getEntity(clazz, entityId));
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
}
