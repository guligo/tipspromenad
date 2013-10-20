package se.tipspromenad.beans;

/**
 * @author guligo
 */
public class VerifyAccessTokenBean extends DataTransferBean {

	private String email;
	private String password;

	public VerifyAccessTokenBean() {
		// for serializer
	}

	public VerifyAccessTokenBean(String email, String password) {
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
