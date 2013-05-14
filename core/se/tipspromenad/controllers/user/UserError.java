package se.tipspromenad.controllers.user;

import se.tipspromenad.validation.ValidationError;

/**
 * Validation errors related to user.
 * 
 * @author guligo
 */
public enum UserError implements ValidationError {
	
	USERNAME_EMPTY,
	USERNAME_TOO_SHORT,
	USERNAME_TOO_LONG,
	USERNAME_DOES_NOT_EXIST,
	EMAIL_EMPTY,
	EMAIL_TOO_SHORT,
	EMAIL_TOO_LONG,
	PASSWORD_EMPTY,
	PASSWORD_TOO_SHORT,
	PASSWORD_TOO_LONG,
	PASSWORD_WRONG,
	DUBLICATED_USER,
	UNEXPECTED_ERROR;
	
}
