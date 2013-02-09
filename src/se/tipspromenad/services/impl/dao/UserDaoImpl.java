package se.tipspromenad.services.impl.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.tipspromenad.entities.User;
import se.tipspromenad.entities.UserProfile;
import se.tipspromenad.entities.impl.UserImpl;
import se.tipspromenad.services.dao.UserDao;


/**
 * See {@link UserDao}.
 * 
 * @author guligo
 */
@Component
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private CommonDao commonDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public User getUserByUsername(String username) {
		List<User> users = commonDao.getEntityManager().createQuery("from UserImpl where username = :username")
			.setParameter("username", username)
			.getResultList();
		if (users != null && users.size() > 0) {
			assert(users.size() == 1);
			return users.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public User getUserByEmail(String email) {		
		List<User> users = commonDao.getEntityManager().createQuery("from UserImpl where email = :email")
			.setParameter("email", email)
			.getResultList();
		if (users != null && users.size() > 0) {
			assert(users.size() == 1);
			return users.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public UserProfile getUserProfileByUsername(String username) {		
		List<UserProfile> userProfiles = commonDao.getEntityManager().createQuery("from UserProfileImpl up where up.user.username = :username")
			.setParameter("username", username)
			.getResultList();
		if (userProfiles != null && userProfiles.size() > 0) {
			assert(userProfiles.size() == 1);
			return userProfiles.get(0);
		}
		return null;
	}
	
	@Override
	public void createUser(User user) {
		commonDao.createEntity(user);
	}
	
	@Override
	public void createUserProfile(UserProfile userProfile) {
		commonDao.createEntity(userProfile);
	}
	
	@Override
	public void updateUserProfile(UserProfile userProfile) {
		commonDao.updateEntity(userProfile);
	}
	
	@Override
	public void removeUser(Long id) {
		commonDao.removeEntity(UserImpl.class, id);
	}
	
}
