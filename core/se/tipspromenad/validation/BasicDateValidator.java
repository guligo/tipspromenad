package se.tipspromenad.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;

/**
 * Represents simple date object validator.
 * 
 * @author guligo
 */
public class BasicDateValidator implements Validator {
	
	private DateFormat      format;
	private ValidationError emptyErr;
	private ValidationError formatErr;

	public BasicDateValidator(DateFormat format, ValidationError emptyErr, ValidationError formatErr) {
		this.format    = format;
		this.emptyErr  = emptyErr;
		this.formatErr = formatErr;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return String.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, List<ValidationError> errors) {
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
