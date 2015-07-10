package se.tipspromenad.controllers.user;

import org.codehaus.jackson.annotate.JsonWriteNullProperties;

import se.tipspromenad.controllers.ResponseBean;

/**
 * See {@link UserWebService#register(UserRegistrationRequestBean, javax.servlet.http.HttpServletRequest)}.
 * 
 * @author guligo
 * @author pavelefimov
 */
@SuppressWarnings("deprecation")
@JsonWriteNullProperties(value = false)
public class UserRegistrationResponse extends ResponseBean<UserError> {

	private static final long serialVersionUID = 1L;
	
	private Long userId;
	
	public UserRegistrationResponse() {
		super();
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
