package se.tipspromenad.entities;

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
public class User extends se.tipspromenad.entities.Entity {

	public final static int MIN_EMAIL_LENGTH    = 10;
	public final static int MAX_EMAIL_LENGTH    = 50;
	public final static int MIN_USERNAME_LENGTH = 5;
	public final static int MAX_USERNAME_LENGTH = 30;
	public final static int MIN_PASSWORD_LENGTH = 5;
	public final static int MAX_PASSWORD_LENGTH = 30;

	@OneToOne(fetch = FetchType.EAGER)
	private UserProfile userProfile;
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

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = (UserProfile) userProfile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
