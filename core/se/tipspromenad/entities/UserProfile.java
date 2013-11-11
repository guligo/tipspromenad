package se.tipspromenad.entities;

import java.util.Date;

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
	
	public final static int MIN_NAME_LENGTH = User.MIN_NAME_LENGTH;
	public final static int MAX_NAME_LENGTH = User.MAX_NAME_LENGTH;
	
	@OneToOne(optional = false)
	private User user;
	@Column(nullable = false, length = MAX_NAME_LENGTH)
	private String name;
	@Column(nullable = true)
	private Gender gender;
	@Column(nullable = true)
	private Date birthDate;
	@Column(nullable = true)
	private String country;
	@Column(nullable = true)
	private String city;
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = (User) user;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
}
