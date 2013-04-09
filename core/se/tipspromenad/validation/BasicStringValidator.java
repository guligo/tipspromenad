package se.tipspromenad.validation;

import java.util.List;

/**
 * Represents simple string object validator.
 * 
 * @author guligo
 */
public class BasicStringValidator implements Validator {

	private int             minLength;
	private int             maxLength;
	private ValidationError emptyErr;
	private ValidationError tooShortErr;
	private ValidationError tooLongErr;

	public BasicStringValidator(int minLength, int maxLength, ValidationError emptyErr, ValidationError tooShortErr, ValidationError tooLongErr) {
		this.minLength   = minLength;
		this.maxLength   = maxLength;
		this.emptyErr    = emptyErr;
		this.tooShortErr = tooShortErr;
		this.tooLongErr  = tooLongErr;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return String.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, List<ValidationError> errors) {
		String value = (String) target;
		if (value == null || value.length() == 0) {
			errors.add(emptyErr);
		} else if (value.length() < minLength) {
			errors.add(tooShortErr);
		} else if (value.length() > maxLength) {
			errors.add(tooLongErr);
		}
	}

}
