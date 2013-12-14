package se.tipspromenad.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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

	public final static int MIN_NAME_LENGTH     = 3;
	public final static int MAX_NAME_LENGTH     = 30;
	public final static int MIN_EMAIL_LENGTH    = 10;
	public final static int MAX_EMAIL_LENGTH    = 50;
	public final static int MIN_PASSWORD_LENGTH = 5;
	public final static int MAX_PASSWORD_LENGTH = 30;

	@OneToOne(fetch = FetchType.EAGER)
	private UserProfile userProfile;
	@Column(nullable = false, length = MAX_NAME_LENGTH, unique = false)
	private String name;
	@Column(nullable = false, length = MAX_EMAIL_LENGTH, unique = true)
	private String email;
	@Column(nullable = true, length = MAX_PASSWORD_LENGTH)
	private String password;
	@Column(nullable = false)
	private UserRole role;
	@Column(nullable = false)
	private Boolean enabled;
	@Column(nullable = true /* unique = true */)
	private String fbUserId;
	@Column(nullable = true, unique = false)
	private String fbUserPassword;
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Play> plays;

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = (UserProfile) userProfile;
	}

    public String getName() {
    	return name;
    }

    public void setName(String name) {
    	this.name = name;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getFbUserId() {
		return fbUserId;
	}

	public void setFbUserId(String fbUserId) {
		this.fbUserId = fbUserId;
	}

	public String getFbUserPassword() {
		return fbUserPassword;
	}

	public void setFbUserPassword(String fbUserPassword) {
		this.fbUserPassword = fbUserPassword;
	}

	// this method is sometimes used before serialization into JSON format
	// because of security reasons
	public void secure() {
		setId(null);
		setPassword(null);
		setRole(null);
		setEnabled(null);
		setFbUserId(null);
		setFbUserPassword(null);
	}

}
