package co.vrings.services.dao;

import co.vrings.entities.User;
import co.vrings.entities.UserProfile;

/**
 * Interface of data-access-object layer component for {@link User} and {@link UserProfile}.
 * 
 * @author guligo
 */
public interface UserDao {
	
	public User getUserByUsername(String username);
	
	public User getUserByEmail(String email);
	
	public UserProfile getUserProfileByUsername(String username);
	
	public void createUser(User user);
	
	public void createUserProfile(UserProfile userProfile);
	
	public void updateUserProfile(UserProfile userProfile);
	
	public void removeUser(Long id);
	
}
