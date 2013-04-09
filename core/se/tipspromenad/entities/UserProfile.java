package se.tipspromenad.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import se.tipspromenad.entities.User;
import se.tipspromenad.entities.UserProfile;
import se.tipspromenad.entities.enums.Gender;

/**
 * Database entity of {@link UserProfile}.
 * 
 * @author guligo
 */
@Entity
@Table(name = "user_profiles")
public class UserProfile extends se.tipspromenad.entities.Entity {
	
	public final static int MIN_FIRST_NAME_LENGTH = 1;
	public final static int MAX_FIRST_NAME_LENGTH = 30;
	public final static int MIN_LAST_NAME_LENGTH  = 1;
	public final static int MAX_LAST_NAME_LENGTH  = 30;
	
	@OneToOne(optional = false)
	private User user;	
	@Column(length = MAX_FIRST_NAME_LENGTH)
	private String firstName;
	@Column(length = MAX_LAST_NAME_LENGTH)
	private String lastName;
	@Column(nullable = false)
	private Gender gender;
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = (User) user;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
}
