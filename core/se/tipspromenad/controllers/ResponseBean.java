package se.tipspromenad.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import se.tipspromenad.validation.ValidationError;

/**
 * Represents response object of MVC controller.
 * 
 * @author eigogul
 */
public class ResponseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ValidationError> errors;

	public ResponseBean() {
		errors = new ArrayList<ValidationError>();
	}

	public List<ValidationError> getErrors() {
		return errors;
	}
	
	public void addError(ValidationError error) {
		errors.add(error);
	}
	
	public boolean hasErrors() {
		return errors != null && !errors.isEmpty();
	}

	public void normalize() {
		if (errors != null && errors.size() == 0) {
			errors = null;
		}
	}

}
