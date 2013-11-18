package se.tipspromenad.validation;

import java.util.List;

/**
 * Represents simple string object validator.
 * 
 * @author guligo
 * @param <E>
 */
public class BasicStringValidator<E extends ValidationError> implements Validator<E> {

	private int minLength;
	private int maxLength;
	private E   emptyErr;
	private E   tooShortErr;
	private E   tooLongErr;

	public BasicStringValidator(int minLength, int maxLength, E tooShortErr, E tooLongErr) {
		this(minLength, maxLength, null, tooShortErr, tooLongErr);
	}

	public BasicStringValidator(int minLength, int maxLength, E emptyErr, E tooShortErr, E tooLongErr) {
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
	public void validate(Object target, List<E> errors) {
		String value = (String) target;
		if (emptyErr != null && (value == null || value.length() == 0)) {
			errors.add(emptyErr);
		} else if (value.length() < minLength) {
			errors.add(tooShortErr);
		} else if (value.length() > maxLength) {
			errors.add(tooLongErr);
		}
	}

}
