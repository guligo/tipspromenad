package se.tipspromenad.entities;

import se.tipspromenad.entities.enums.Gender;

/**
 * Represents some additional nice-to-have user information.
 * 
 * @author guligo
 */
public interface UserProfile extends Entity {
	
	public final static int MIN_FIRST_NAME_LENGTH = 1;
	public final static int MAX_FIRST_NAME_LENGTH = 30;
	public final static int MIN_LAST_NAME_LENGTH  = 1;
	public final static int MAX_LAST_NAME_LENGTH  = 30;
	
	public User getUser();
	
	public void setUser(User user);
	
	public String getFirstName();
	
	public void setFirstName(String firstName);
	
	public String getLastName();
	
	public void setLastName(String lastName);
	
	public Gender getGender();
	
	public void setGender(Gender gender);
	
}
