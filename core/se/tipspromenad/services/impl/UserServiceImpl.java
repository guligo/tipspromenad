package se.tipspromenad.services.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import se.tipspromenad.entities.User;
import se.tipspromenad.entities.UserProfile;
import se.tipspromenad.entities.enums.Gender;
import se.tipspromenad.entities.enums.UserRole;
import se.tipspromenad.entities.impl.UserImpl;
import se.tipspromenad.entities.impl.UserProfileImpl;
import se.tipspromenad.exception.ValidationException;
import se.tipspromenad.services.UserService;
import se.tipspromenad.services.dao.UserDao;
import se.tipspromenad.utils.ValidationUtils;


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
	public Long createUser(String email, String username, String password) throws ValidationException {
		ValidationException errors = new ValidationException();
		ValidationUtils.validate("email", email, true, User.MIN_EMAIL_LENGTH, User.MAX_EMAIL_LENGTH, errors);
		ValidationUtils.validate("username", username, true, User.MIN_USERNAME_LENGTH, User.MAX_USERNAME_LENGTH, errors);
		ValidationUtils.validate("password", password, true, User.MIN_PASSWORD_LENGTH, User.MAX_PASSWORD_LENGTH, errors);
		if (errors.persent()) {
			throw errors;
		}
		
		UserImpl user = new UserImpl();
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(password);
		user.setRole(UserRole.ROLE_SIMPLE_USER);
		user.setEnabled(true);
		return userDao.createUser(user);
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
