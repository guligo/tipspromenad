package se.tipspromenad.ws.beans;

import se.tipspromenad.ws.UserWebService;

/**
 * See {@link UserWebService#register(UserRegistrationRequestBean, javax.servlet.http.HttpServletRequest)}.
 * 
 * @author eigogul
 */
public class UserRegistrationResponseBean extends ResponseBean {

	private static final long serialVersionUID = 1L;

	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
