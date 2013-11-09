package se.tipspromenad.controllers.login;

import org.codehaus.jackson.annotate.JsonWriteNullProperties;

import se.tipspromenad.controllers.ResponseBean;

@SuppressWarnings("deprecation")
@JsonWriteNullProperties(value = false)
public class LoginVerifyAccessTokenResponse extends ResponseBean<LoginError> {

	private static final long serialVersionUID = 1L;

	private String email;
	private String password;

	public LoginVerifyAccessTokenResponse() {
		// for serializer
	}

	public LoginVerifyAccessTokenResponse(String email, String password) {
		this.email    = email;
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
