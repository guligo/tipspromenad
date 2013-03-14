package se.tipspromenad.exception;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;

	private Map<String, String> errors;

	public ValidationException() {
		errors = new HashMap<String, String>();
	}

	public void rejectField(String field, String message) {
		errors.put(field, message);
	}
	
	public boolean persent() {
		return !errors.isEmpty();
	}
	
	@Override
	public String getMessage() {
		StringBuilder sb = new StringBuilder();
		for (String message : errors.values()) {
			sb.append(message + "\n");
		}
		return sb.toString();
	}

}
