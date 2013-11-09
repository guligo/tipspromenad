package se.tipspromenad.controllers.user;

import org.codehaus.jackson.annotate.JsonWriteNullProperties;

import se.tipspromenad.controllers.ResponseBean;

@SuppressWarnings("deprecation")
@JsonWriteNullProperties(value = false)
public class UserProfileUpdateResponse extends ResponseBean<UserError> {

	private static final long serialVersionUID = 1L;

}
