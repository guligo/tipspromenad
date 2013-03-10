package se.tipspromenad.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import se.tipspromenad.entities.User;
import se.tipspromenad.entities.UserProfile;
import se.tipspromenad.entities.enums.UserRole;


/**
 * Database entity for {@link User}.
 * 
 * @author guligo
 */
@Entity
@Table(name = "users")
public class UserImpl extends EntityImpl implements User {

	@OneToOne(fetch = FetchType.EAGER)
	private UserProfileImpl userProfile;
	@Column(nullable = false, length = MAX_EMAIL_LENGTH, unique = true)
	private String email;
	@Column(nullable = false, length = MAX_USERNAME_LENGTH, unique = true)
	private String username;
	@Column(nullable = false, length = MAX_PASSWORD_LENGTH)
	private String password;
	@Column(nullable = false)
	private UserRole role;
	@Column(nullable = false)
	private boolean enabled;

	@Override
	public UserProfile getUserProfile() {
		return userProfile;
	}

	@Override
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = (UserProfileImpl) userProfile;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public UserRole getRole() {
		return role;
	}

	@Override
	public void setRole(UserRole role) {
		this.role = role;
	}

	@Override
	public boolean getEnabled() {
		return enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
