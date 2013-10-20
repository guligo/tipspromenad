package se.tipspromenad.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
		if (user == null || user.getPassword() == null) {
			throw new UsernameNotFoundException("User with email " + email + " not found");
		} else {
			UserWrapper wrapper = new UserWrapper(user);
			if (user.getResetPassword()) {
				user.setPassword(null);
				user.setResetPassword(false);
				userService.updateUser(user);
			}
			return wrapper;
		}
	}

}
