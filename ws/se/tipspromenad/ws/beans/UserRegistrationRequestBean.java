package se.tipspromenad.ws.beans;

import java.io.Serializable;

public class UserRegistrationRequestBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String email;
	private String password;

	public UserRegistrationRequestBean() {
		// empty
	}

	public UserRegistrationRequestBean(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

}