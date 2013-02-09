package co.vrings.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import co.vrings.entities.User;
import co.vrings.entities.UserProfile;
import co.vrings.entities.enums.Gender;

/**
 * Database entity of {@link UserProfile}.
 * 
 * @author guligo
 */
@Entity
@Table(name = "user_profiles")
public class UserProfileImpl extends EntityImpl implements UserProfile {
	
	@OneToOne(optional = false)
	private UserImpl user;	
	@Column(length = MAX_FIRST_NAME_LENGTH)
	private String firstName;
	@Column(length = MAX_LAST_NAME_LENGTH)
	private String lastName;
	@Column(nullable = true)
	private String image;
	@Column(nullable = false)
	private Gender gender;
	
	@Override
	public User getUser() {
		return user;
	}
	
	@Override
	public void setUser(User user) {
		this.user = (UserImpl) user;
	}
	
	@Override
	public String getFirstName() {
		return firstName;
	}
	
	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Override
	public String getLastName() {
		return lastName;
	}
	
	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String getImage() {
		return image;
	}
	
	@Override
	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public Gender getGender() {
		return gender;
	}
	
	@Override
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
}
