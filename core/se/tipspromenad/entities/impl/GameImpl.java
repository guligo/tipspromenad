package se.tipspromenad.entities.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import se.tipspromenad.entities.Game;
import se.tipspromenad.entities.User;

/**
 * Database entity for {@link Game}.
 * 
 * @author guligo
 */
@Entity
@Table(name = "games")
public class GameImpl extends EntityImpl implements Game {

	@ManyToOne
	private UserImpl creator;
	@Column(nullable = false, length = MAX_NAME_LENGTH)
	private String name;
	@Column(nullable = false)
	private Date creationDate;

	@Override
	public User getCreator() {
		return creator;
	}

	@Override
	public void setCreator(User creator) {
		this.creator = (UserImpl) creator;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Date getCreationDate() {
		return creationDate;
	}

	@Override
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
