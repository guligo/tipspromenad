package se.tipspromenad.beans;

import se.tipspromenad.controllers.UserController;

/**
 * Data transfer bean between {@link UserController#doUpdateUserProfile(String, String, String)} and view.
 * 
 * @author guligo
 */
public class UserProfileBean extends DataTransferBean {
	
	private String firstName;
	private String lastName;
	private String gender;
	
	public UserProfileBean() {
		// used for serialization purposes only
	}
	
	public UserProfileBean(String firstName, String lastName, String gender) {		
		this.firstName = firstName;
		this.lastName  = lastName;
		this.gender    = gender;
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
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
