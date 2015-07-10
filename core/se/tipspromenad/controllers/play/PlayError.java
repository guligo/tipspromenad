package se.tipspromenad.controllers.play;

import se.tipspromenad.entities.Play;
import se.tipspromenad.validation.ValidationError;

/**
 * Represents various {@link Play} entity related errors.
 * 
 * @author guligo
 * @author pavelefimov
 */
public enum PlayError implements ValidationError {
	
	START_DATE_WRONG_FORMAT,
	END_DATE_WRONG_FORMAT,
	UNKNOWN;
	
}
