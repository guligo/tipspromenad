package se.tipspromenad.services;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import se.tipspromenad.entities.User;
import se.tipspromenad.entities.UserProfile;
import se.tipspromenad.entities.enums.UserRole;
import se.tipspromenad.services.UserService;
import se.tipspromenad.services.dao.UserDao;
import se.tipspromenad.utils.SecurityUtils;

/**
 * See {@link UserService}.
 * 
 * @author guligo
 */
@Component
@Transactional
public class UserService {

	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(UserService.class);

	@Autowired
	private UserDao userDao;

	public User getUser(Long id) {
		return userDao.getUser(id);
	}

	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	public UserProfile getUserProfileByUsername(String username) {
		return userDao.getUserProfileByUsername(username);
	}

	public Long createUser(String name, String email, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(SecurityUtils.toBase64(SecurityUtils.toMD5(password)));
		user.setRole(UserRole.ROLE_SIMPLE_USER);
		user.setEnabled(true);
		user.setFbUserId(null);
		user.setResetPassword(false);
		return userDao.createUser(user);
	}

	public Long createUser(String fbUserId, String name, String email, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(SecurityUtils.toBase64(SecurityUtils.toMD5(password)));
		user.setRole(UserRole.ROLE_SIMPLE_USER);
		user.setEnabled(true);
		user.setFbUserId(fbUserId);
		user.setResetPassword(true);
		return userDao.createUser(user);
	}

	public void removeUser(Long id) {
		userDao.removeUser(id);
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	public User getUserByFbId(String fbId) {
		return userDao.getUserByFbId(fbId);
	}

//	public void updateUserProfile(String email, String firstName, String lastName, Gender gender) {
//		UserProfile userProfile = userDao.getUserProfileByUsername(username);
//		if (userProfile == null) {
//			userProfile = new UserProfile();
//			userProfile.setUser(userDao.getUserByUsername(username));
//			userProfile.setFirstName(firstName);
//			userProfile.setLastName(lastName);
//			userProfile.setGender(gender);
//			userDao.createUserProfile(userProfile);
//		} else {
//			userProfile.setUser(userDao.getUserByUsername(username));
//			userProfile.setFirstName(firstName);
//			userProfile.setLastName(lastName);
//			userProfile.setGender(gender);
//			userDao.updateUserProfile(userProfile);
//		}
//	}

}

