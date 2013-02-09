package co.vrings.entities;

import java.util.Date;

/**
 * Represents user entry.
 * 
 * @author guligo
 */
public interface Entry extends Entity {
	
	public User getUser();
	
	public void setUser(User user);
	
	public Date getDate();
	
	public void setDate(Date date);
	
	public int getSequence();
	
	public void setSequence(int sequence);
	
	public boolean getIsPrivate();
	
	public void setIsPrivate(boolean isPrivate);
	
}
