package se.tipspromenad.controllers.user;

import se.tipspromenad.controllers.RequestBean;

public class UserLoginRequest extends RequestBean {

	private static final long serialVersionUID = 1L;

	private String email;
	private String password;

	public UserLoginRequest() {
		// empty
	}

	public UserLoginRequest(String email, String password) {
		this.email = email;
		this.password = password;
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
