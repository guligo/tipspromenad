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
public class UserRegistrationResponseBean extends ResponseBean {

	private static final long serialVersionUID = 1L;
	
	public final static int ERROR_CODE_USERNAME_TOO_SHORT = 200;
	public final static int ERROR_CODE_USERNAME_TOO_LONG  = 201;
	public final static int ERROR_CODE_EMAIL_TOO_SHORT    = 202;
	public final static int ERROR_CODE_EMAIL_TOO_LONG     = 203;
	public final static int ERROR_CODE_PASSWORD_TOO_SHORT = 204;
	public final static int ERROR_CODE_PASSWORD_TOO_LONG  = 205;
	
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
