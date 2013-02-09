package co.vrings.entities.impl;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import co.vrings.entities.Entity;

/**
 * Represents database entity.
 * 
 * @author guligo
 */
@MappedSuperclass
public abstract class EntityImpl implements Entity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
}
