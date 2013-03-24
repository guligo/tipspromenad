package se.tipspromenad.entities;

import java.util.Date;

/**
 * Represents game.
 * 
 * @author guligo
 */
public interface Game extends Entity {

	public final static int MIN_NAME_LENGTH = 5;
	public final static int MAX_NAME_LENGTH = 50;

	public User getCreator();

	public void setCreator(User user);

	public String getName();

	public void setName(String name);

	public Date getCreationDate();

	public void setCreationDate(Date date);

	// public Set<? extends Point> getPoints();

	// public void setPoints(Set<? extends Point> points);

}
