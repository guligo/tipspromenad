package se.tipspromenad.utils;

import se.tipspromenad.beans.DataTransferBean;
import se.tipspromenad.exception.ValidationException;

/**
 * Utils for data validation.
 * 
 * @author guligo
 */
public class ValidationUtils {
	
	public static void validate(String field, String value, boolean mandatory, int min, int max, ValidationException exception) {
		if (value == null || value.isEmpty()) {
			exception.rejectField(field, String.format("%s may not be empty", field));
		} else if (value.length() < min) {
			exception.rejectField(field, String.format("%s must be at least %s symbols long", field, min));
		} else if (value.length() > max) {
			exception.rejectField(field, String.format("%s must be not more than %s symbols long", field, max));
		}
	}
	
	/**
	 * Performs some basic string validation on field of
	 * {@link DataTransferBean}.
	 */
	public static void validate(DataTransferBean bean, String field, String label, String value, int min, int max) {
		if (value == null || value.isEmpty()) {
			bean.reject(field, String.format("%s may not be empty", label));
		} else if (value.length() < min) {
			bean.reject(field, String.format("%s must be at least %s symbols long", label, min));
		} else if (value.length() > max) {
			bean.reject(field, String.format("%s must be not more than %s symbols long", label, max));
		}
	}

}
