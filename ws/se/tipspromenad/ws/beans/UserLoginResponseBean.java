package se.tipspromenad.ws.beans;

import org.codehaus.jackson.annotate.JsonWriteNullProperties;

import se.tipspromenad.ws.UserWebService;

/**
 * See {@link UserWebService#login(UserLoginRequestBean, javax.servlet.http.HttpServletRequest)}.
 * 
 * @author eigogul
 */
@SuppressWarnings("deprecation")
@JsonWriteNullProperties(value = false)
public class UserLoginResponseBean extends ResponseBean {

	private static final long serialVersionUID = 1L;

	public static final int ERROR_CODE_WRONG_EMAIL = 1;
	public static final int ERROR_CODE_WRONG_PASSWORD = 2;

	private Long sessionId;

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long userId) {
		this.sessionId = userId;
	}

}
