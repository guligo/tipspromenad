package se.tipspromenad.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;

/**
 * Represents simple date object validator.
 * 
 * @author guligo
 */
public class BasicDateValidator<E extends ValidationError> implements Validator<E> {
	
	private DateFormat      format;
	private E emptyErr;
	private E formatErr;

	public BasicDateValidator(DateFormat format, E emptyErr, E formatErr) {
		this.format    = format;
		this.emptyErr  = emptyErr;
		this.formatErr = formatErr;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return String.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, List<E> errors) {
		String value = (String) target;
		if (value == null) {
			errors.add(emptyErr);
		} else {
			try {
				format.parse(value);
			} catch (ParseException pe) {
				errors.add(formatErr);
			}
		}
	}

}
