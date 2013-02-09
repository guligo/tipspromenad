package co.vrings.services;

import co.vrings.entities.User;
import co.vrings.entities.UserProfile;
import co.vrings.entities.enums.Gender;

/**
 * Interface of service layer component that provides business logic for
 * {@link User} and {@link UserProfile}.
 * 
 * @author guligo
 */
public interface UserService {

	public User getUserByUsername(String username);

	public User getUserByEmail(String email);

	public UserProfile getUserProfileByUsername(String username);

	public void createUser(String email, String username, String password);

	public void updateUserProfile(String username, String firstName, String lastName, Gender gender);

	public void removeUser(Long id);

}
