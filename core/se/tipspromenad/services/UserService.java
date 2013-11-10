package se.tipspromenad.services;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import se.tipspromenad.entities.User;
import se.tipspromenad.entities.UserProfile;
import se.tipspromenad.entities.enums.Gender;
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

	public UserProfile getUserProfileByEmail(String email) {
		return userDao.getUserProfileByEmail(email);
	}

	public Long createUser(String name, String email, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(SecurityUtils.toBase64(SecurityUtils.toMD5(password)));
		user.setRole(UserRole.ROLE_SIMPLE_USER);
		user.setEnabled(true);
		user.setFbUserId(null);
		user.setFbUserPassword(null);
		
		userDao.createUser(user);
		createUserProfile(user.getId(), user.getName());
		return user.getId();
	}

	public Long createUser(String name, String email, String fbUserId, String fbUserPassword) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(null);
		user.setRole(UserRole.ROLE_SIMPLE_USER);
		user.setEnabled(true);
		user.setFbUserId(fbUserId);
		user.setFbUserPassword(fbUserPassword);
		
		userDao.createUser(user);
		createUserProfile(user.getId(), user.getName());
		return user.getId();
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

	private Long createUserProfile(Long userId, String name) {
		User user = userDao.getUser(userId);
		UserProfile userProfile = new UserProfile();
		userProfile.setName(name);
		userProfile.setUser(user);
		userDao.createUserProfile(userProfile);
		return userProfile.getId();
	}
	
	public void updateUserProfile(String email, String name, Gender gender) {
		User user = userDao.getUserByEmail(email);
		user.setName(name);
		userDao.updateUser(user);
		
		UserProfile userProfile = userDao.getUserProfileByEmail(email);
		userProfile.setUser(userDao.getUserByEmail(email));
		userProfile.setName(name);
		userProfile.setGender(gender);
		userDao.updateUserProfile(userProfile);
	}
	
	public void updateUserProfile(UserProfile userProfile) {
		userDao.updateUserProfile(userProfile);
	}
	
}
