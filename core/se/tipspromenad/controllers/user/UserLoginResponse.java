package se.tipspromenad.controllers.user;

import org.codehaus.jackson.annotate.JsonWriteNullProperties;

import se.tipspromenad.controllers.ResponseBean;

/**
 * See {@link UserWebService#login(UserLoginRequestBean, javax.servlet.http.HttpServletRequest)}.
 * 
 * @author eigogul
 */
@SuppressWarnings("deprecation")
@JsonWriteNullProperties(value = false)
public class UserLoginResponse extends ResponseBean {

	private static final long serialVersionUID = 1L;

	private Long sessionId;

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long userId) {
		this.sessionId = userId;
	}

}
