package se.tipspromenad.controllers.user;

import se.tipspromenad.validation.ValidationError;

/**
 * Validation errors related to user.
 * 
 * @author guligo
 */
public enum UserError implements ValidationError {

	NAME_EMPTY,
	NAME_TOO_SHORT,
	NAME_TOO_LONG,
	EMAIL_EMPTY,
	EMAIL_TOO_SHORT,
	EMAIL_TOO_LONG,
	EMAIL_DOES_NOT_EXIST,
	PASSWORD_EMPTY,
	PASSWORD_TOO_SHORT,
	PASSWORD_TOO_LONG,
	PASSWORD_WRONG,
	PASSWORD_NOT_MATCH,
	DUBLICATED_USER,
	BIRTH_DATE_WRONG_FORMAT,
	COUNTRY_TOO_SHORT,
	COUNTRY_TOO_LONG,
	CITY_TOO_SHORT,
	CITY_TOO_LONG,
	UNEXPECTED_ERROR;

}
