package se.tipspromenad.validation;

import java.util.List;

/**
 * Interface for object validator.
 * 
 * @author guligo
 */
public interface Validator {
	
	public boolean supports(Class<?> clazz);
	
	public void validate(Object target, List<ValidationError> errors);
	
}
