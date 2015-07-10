package se.tipspromenad.controllers.game;

import se.tipspromenad.validation.ValidationError;

/**
 * Validation errors related to game page.
 * 
 * @author guligo
 * @author pavelefimov
 */
public enum GameError implements ValidationError {
	
	NAME_EMPTY,
	NAME_TOO_LONG,
	NAME_TOO_SHORT,
	DATE_EMPTY,
	DATE_WRONG_FORMAT,
	UNKNOWN;
	
}
