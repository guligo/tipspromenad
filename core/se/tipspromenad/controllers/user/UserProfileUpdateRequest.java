package se.tipspromenad.controllers.user;

import se.tipspromenad.controllers.RequestBean;

public class UserProfileUpdateRequest extends RequestBean {

	private static final long serialVersionUID = 1L;

	private String name;
	private String gender;

	public UserProfileUpdateRequest() {
		// empty
	}

	public UserProfileUpdateRequest(String name, String gender) {
		this.name   = name;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
