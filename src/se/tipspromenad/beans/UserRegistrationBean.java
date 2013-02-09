package se.tipspromenad.beans;

import se.tipspromenad.controllers.UserController;

/**
 * Data transfer bean between {@link UserController#doRegistration(String, String, String, String)} and view.
 * 
 * @author guligo
 */
public class UserRegistrationBean extends DataTransferBean {
	
	private String email;
	private String username;
	private String password;
	private String confirm;
	
	public UserRegistrationBean() {
		// used for serialization purposes only
	}
	
	public UserRegistrationBean(String email, String username, String password, String confirm) {
		this.email    = email;
		this.username = username;
		this.password = password;
		this.confirm  = confirm;
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
	
	public String getConfirm() {
		return confirm;
	}
	
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	
}
