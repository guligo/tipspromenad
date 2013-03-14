package se.tipspromenad.ws.beans;

import java.io.Serializable;

/**
 * Represents response entity for WS call.
 * 
 * @author eigogul
 */
public class ResponseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String errorCode;
	private String errorMessage;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
