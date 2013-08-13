package se.tipspromenad.controllers.user;

import se.tipspromenad.controllers.RequestBean;

/**
 * @author guligo
 */
public class UserRegistrationRequest extends RequestBean {

	private static final long serialVersionUID = 1L;

	private String name;
	private String email;
	private String password;

	public UserRegistrationRequest() {
		// empty
	}

	public UserRegistrationRequest(String name, String email, String password) {
		this.name     = name;
		this.email    = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setUsername(String name) {
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

}
