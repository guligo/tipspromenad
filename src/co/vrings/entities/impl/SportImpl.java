package co.vrings.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import co.vrings.entities.Sport;

/**
 * Database entity for {@link Sport}.
 * 
 * @author guligo
 */
@Entity
@Table(name = "sports")
public class SportImpl extends EntityImpl implements Sport {
	
	@Column(nullable = false, unique = true)
	private String name;
	@Column(nullable = false, unique = true)
	private String image;
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getImage() {
		return image;
	}

	@Override
	public void setImage(String image) {
		this.image = image;
	}
	
}
