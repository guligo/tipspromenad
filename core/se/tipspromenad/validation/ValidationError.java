package se.tipspromenad.validation;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;

/**
 * Interface for validation error.
 * 
 * @author guligo
 */
@JsonTypeInfo(use = Id.CLASS)
public interface ValidationError {
	
}