package se.tipspromenad.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import se.tipspromenad.entities.Entity;

/**
 * Represents database entity.
 * 
 * @author guligo
 * @author pavelefimov
 */
@MappedSuperclass
public abstract class Entity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
}
