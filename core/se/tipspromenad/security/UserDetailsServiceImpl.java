package se.tipspromenad.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import se.tipspromenad.entities.User;
import se.tipspromenad.services.UserService;

/**
 * This class is used by spring security authentication mechanism.
 * 
 * @author guligo
 */
@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, DataAccessException {
		User user = userService.getUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User with email " + email + " not found");
		} else {
			String tmp = user.getPassword();
			
			// if user has accessed the system through FB only
			boolean fbOnly = (user.getFbUserId() != null && user.getFbUserPassword() != null);			
			if (fbOnly) {				
				user.setPassword(user.getFbUserPassword());
			}
			
			UserWrapper wrapper = new UserWrapper(user);
			if (fbOnly) {
				user.setPassword(tmp);
				user.setFbUserPassword(null);
				userService.updateUser(user);
			}
			return wrapper;
		}
	}

}
