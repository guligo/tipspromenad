package se.tipspromenad.validation;

import java.util.List;

/**
 * Interface for object validator.
 * 
 * @author guligo
 */
public interface Validator<E extends ValidationError> {
	
	public boolean supports(Class<?> clazz);
	
	public void validate(Object target, List<E> errors);
	
}
