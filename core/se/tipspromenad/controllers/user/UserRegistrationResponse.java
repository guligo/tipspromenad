package se.tipspromenad.controllers.user;

import org.codehaus.jackson.annotate.JsonWriteNullProperties;

import se.tipspromenad.controllers.ResponseBean;

/**
 * See {@link UserWebService#register(UserRegistrationRequestBean, javax.servlet.http.HttpServletRequest)}.
 * 
 * @author eigogul
 */
@SuppressWarnings("deprecation")
@JsonWriteNullProperties(value = false)
public class UserRegistrationResponse extends ResponseBean {

	private static final long serialVersionUID = 1L;
	
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
