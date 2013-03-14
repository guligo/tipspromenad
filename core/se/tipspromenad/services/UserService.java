package se.tipspromenad.services;

import se.tipspromenad.entities.User;
import se.tipspromenad.entities.UserProfile;
import se.tipspromenad.entities.enums.Gender;
import se.tipspromenad.exception.ValidationException;

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

	public Long createUser(String email, String username, String password) throws ValidationException;

	public void updateUserProfile(String username, String firstName, String lastName, Gender gender);

	public void removeUser(Long id);

}
