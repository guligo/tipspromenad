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
public class ResponseBean<E extends ValidationError> implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<E> errors;

	public ResponseBean() {
		errors = new ArrayList<E>();
	}

	public List<E> getErrors() {
		return errors;
	}
	
	public void addError(E error) {
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
