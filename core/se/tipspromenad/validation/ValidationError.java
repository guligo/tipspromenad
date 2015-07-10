package se.tipspromenad.validation;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;

/**
 * Interface for validation error.
 * 
 * @author guligo
 * @author pavelefimov
 */
@JsonTypeInfo(use = Id.NONE)
public interface ValidationError {
	
}
