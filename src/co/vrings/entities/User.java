package co.vrings.entities;

import java.util.Set;

import co.vrings.entities.enums.UserRole;

/**
 * Represents user.
 * 
 * @author guligo
 */
public interface User extends Entity {
	
	public final static int MIN_EMAIL_LENGTH    = 10;
	public final static int MAX_EMAIL_LENGTH    = 50;
	public final static int MIN_USERNAME_LENGTH = 5;
	public final static int MAX_USERNAME_LENGTH = 30;
	public final static int MIN_PASSWORD_LENGTH = 5;
	public final static int MAX_PASSWORD_LENGTH = 30;
	
	public UserProfile getUserProfile();
	
	public void setUserProfile(UserProfile userProfile);
	
	public Set<? extends Entry> getEntries();
	
	public void setEntries(Set<? extends Entry> entries);
	
	public String getEmail();
	
	public void setEmail(String email);
	
	public String getUsername();
	
	public void setUsername(String username);
	
	public String getPassword();
	
	public void setPassword(String password);

	public UserRole getRole();
	
	public void setRole(UserRole userRole);
	
	public boolean getEnabled();
	
	public void setEnabled(boolean enables);
	
}
