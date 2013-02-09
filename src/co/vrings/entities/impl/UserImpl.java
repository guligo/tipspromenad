package co.vrings.entities.impl;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import co.vrings.entities.Entry;
import co.vrings.entities.User;
import co.vrings.entities.UserProfile;
import co.vrings.entities.enums.UserRole;

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
	@OneToMany(fetch = FetchType.EAGER)
	private Set<EntryImpl> entries;
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
	public Set<EntryImpl> getEntries() {
		return this.entries;
	}
		
	@Override
	@SuppressWarnings("unchecked")
	public void setEntries(Set<? extends Entry> entries) {
		this.entries = (Set<EntryImpl>) entries;
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
