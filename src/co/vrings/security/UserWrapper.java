package co.vrings.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import co.vrings.entities.User;

/**
 * Helper class that acts as proxy between {@link co.vrings.entities.User} entity and
 * {@link org.springframework.security.core.userdetails.UserDetails} class used by spring security.
 * 
 * @author eigogul
 */
public class UserWrapper implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private boolean enabled;
	private GrantedAuthority[] authorities;
	
	public UserWrapper(User user) {
		username = user.getUsername();
		password = user.getPassword();
		enabled = user.getEnabled();
		authorities = new GrantedAuthority[] {new GrantedAuthorityImpl(user.getRole().name())};
	}
	
	@Override
	public String getUsername() {
		return this.username;
	}
	
	@Override
	public String getPassword() {
		return this.password;
	}
	
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return Arrays.asList(authorities);
	}
	
	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

}