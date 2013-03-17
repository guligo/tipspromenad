package se.tipspromenad.utils;

import java.util.List;

import se.tipspromenad.beans.DataTransferBean;

/**
 * Utils for data validation.
 * 
 * @author guligo
 */
public class ValidationUtils {
	
	public static void validate(String value, int min, int max,
			List<String> errorCodes, String errorCodeEmpty, String errorCodeTooShort, String errorCodeTooLong) {
		if (value == null || value.isEmpty()) {
			errorCodes.add(errorCodeEmpty);
		} else if (value.length() < min) {
			errorCodes.add(errorCodeTooShort);
		} else if (value.length() > max) {
			errorCodes.add(errorCodeTooLong);
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
