package co.vrings.services.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.vrings.entities.User;
import co.vrings.entities.UserProfile;
import co.vrings.entities.enums.Gender;
import co.vrings.entities.enums.UserRole;
import co.vrings.entities.impl.UserImpl;
import co.vrings.entities.impl.UserProfileImpl;
import co.vrings.services.UserService;
import co.vrings.services.dao.UserDao;

/**
 * See {@link UserService}.
 * 
 * @author guligo
 */
@Component
@Transactional
public class UserServiceImpl implements UserService {

	private final static Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Override
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

	@Override
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	public UserProfile getUserProfileByUsername(String username) {
		return userDao.getUserProfileByUsername(username);
	}

	@Override
	public void createUser(String email, String username, String password) {
		UserImpl user = new UserImpl();
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(password);
		user.setRole(UserRole.ROLE_SIMPLE_USER);
		user.setEnabled(true);
		userDao.createUser(user);

		logger.debug("New user was successfully created (email = '" + email + "', username = '" + username + "', password = '" + password + "')");
	}

	@Override
	public void updateUserProfile(String username, String firstName, String lastName, Gender gender) {
		UserProfile userProfile = userDao.getUserProfileByUsername(username);
		if (userProfile == null) {
			userProfile = new UserProfileImpl();
			userProfile.setUser(userDao.getUserByUsername(username));
			userProfile.setFirstName(firstName);
			userProfile.setLastName(lastName);
			userProfile.setGender(gender);
			userDao.createUserProfile(userProfile);
		} else {
			userProfile.setUser(userDao.getUserByUsername(username));
			userProfile.setFirstName(firstName);
			userProfile.setLastName(lastName);
			userProfile.setGender(gender);
			userDao.updateUserProfile(userProfile);
		}
	}

	@Override
	public void removeUser(Long id) {
		userDao.removeUser(id);
	}

}
