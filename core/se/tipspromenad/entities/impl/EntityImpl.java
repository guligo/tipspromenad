package se.tipspromenad.entities.impl;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import se.tipspromenad.entities.Entity;


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
