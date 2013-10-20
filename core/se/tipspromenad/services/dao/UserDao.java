package se.tipspromenad.services.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import se.tipspromenad.entities.User;
import se.tipspromenad.entities.UserProfile;
import se.tipspromenad.services.dao.UserDao;

/**
 * See {@link UserDao}.
 * 
 * @author guligo
 */
@Component
@Transactional
public class UserDao {
	
	@Autowired
	private CommonDao commonDao;
	
	public User getUser(Long id) {
		return (User) commonDao.getEntity(User.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public User getUserByEmail(String email) {		
		List<User> users = commonDao.getEntityManager().createQuery("from User where email = :email")
			.setParameter("email", email)
			.getResultList();
		if (users != null && users.size() > 0) {
			assert(users.size() == 1);
			return users.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public UserProfile getUserProfileByUsername(String username) {		
		List<UserProfile> userProfiles = commonDao.getEntityManager().createQuery("from UserProfile up where up.user.username = :username")
			.setParameter("username", username)
			.getResultList();
		if (userProfiles != null && userProfiles.size() > 0) {
			assert(userProfiles.size() == 1);
			return userProfiles.get(0);
		}
		return null;
	}
	
	public Long createUser(User user) {
		return commonDao.createEntity(user);
	}
	
	public void createUserProfile(UserProfile userProfile) {
		commonDao.createEntity(userProfile);
	}
	
	public void updateUserProfile(UserProfile userProfile) {
		commonDao.updateEntity(userProfile);
	}
	
	public void removeUser(Long id) {
		commonDao.removeEntity(User.class, id);
	}
	
	public void updateUser(User user) {
		commonDao.updateEntity(user);
	}
	
	@SuppressWarnings("unchecked")
	public User getUserByFbId(String fbUserId) {		
		List<User> users = commonDao.getEntityManager().createQuery("from User u where u.fbUserId = :fbUserId")
			.setParameter("fbUserId", fbUserId)
			.getResultList();
		if (users != null && users.size() > 0) {
			return users.get(0);
		}
		return null;
	}
	
}
