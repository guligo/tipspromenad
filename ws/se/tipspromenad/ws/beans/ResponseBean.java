package se.tipspromenad.ws.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents response entity for WS call.
 * 
 * @author eigogul
 */
public class ResponseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> errorCodes;

	public ResponseBean() {
		errorCodes = new ArrayList<String>();
	}

	public List<String> getErrorCodes() {
		return errorCodes;
	}

	public void addErrorCode(String errorCode) {
		errorCodes.add(errorCode);
	}

	public void setErrorCodes(List<String> errorCodes) {
		this.errorCodes = errorCodes;
	}

	public boolean hasErrors() {
		return errorCodes != null && errorCodes.size() > 0;
	}
	
	public void normalize() {
		if (errorCodes != null && errorCodes.size() == 0) {
			errorCodes = null;
		}
	}
	
}
