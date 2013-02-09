package co.vrings.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents class of objects that are responsible for data transfer between
 * MVC controller and view.
 * 
 * @author guligo
 */
public abstract class DataTransferBean {

	public final static int STATUS_OK = 0;
	public final static int STATUS_NOK = 1;

	private int status;
	private Map<String, String> errors;

	public DataTransferBean() {
		status = STATUS_OK;
		errors = new HashMap<String, String>();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	
	public void reject(String field, String message) {
		setStatus(STATUS_NOK);
		errors.put(field, message);
	}
	
	public boolean isRejected(String field) {
		return errors.get(field) != null;
	}
	
}
