package se.tipspromenad.controllers.user;

import java.io.IOException;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.tipspromenad.beans.DataTransferBean;
import se.tipspromenad.beans.UserProfileBean;
import se.tipspromenad.entities.User;
import se.tipspromenad.entities.UserProfile;
import se.tipspromenad.entities.enums.Gender;
import se.tipspromenad.globals.Constants;
import se.tipspromenad.security.UserWrapper;
import se.tipspromenad.services.UserService;
import se.tipspromenad.utils.SecurityUtils;
import se.tipspromenad.utils.ValidationUtils;
import se.tipspromenad.validation.BasicStringValidator;

/**
 * Controller of MVC paradigm that is responsible for {@link User} related actions.
 * 
 * @author guligo
 */
@Controller
public class UserController {

	private final static Logger logger = Logger.getLogger(UserController.class);
	
	// services
	@Autowired
	private UserService userService;

	// validators
	private BasicStringValidator<UserError> userNameValidator;
	private BasicStringValidator<UserError> userEmailValidator;
	private BasicStringValidator<UserError> userPasswordValidator;

	public UserController() {
		userNameValidator     = new BasicStringValidator<UserError>(User.MIN_NAME_LENGTH, User.MAX_NAME_LENGTH, UserError.NAME_EMPTY, UserError.NAME_TOO_SHORT, UserError.NAME_TOO_LONG);
		userEmailValidator    = new BasicStringValidator<UserError>(User.MIN_EMAIL_LENGTH, User.MAX_EMAIL_LENGTH, UserError.EMAIL_EMPTY, UserError.EMAIL_TOO_SHORT, UserError.EMAIL_TOO_LONG);
		userPasswordValidator = new BasicStringValidator<UserError>(User.MIN_PASSWORD_LENGTH, User.MAX_PASSWORD_LENGTH, UserError.PASSWORD_EMPTY, UserError.PASSWORD_TOO_SHORT, UserError.PASSWORD_TOO_LONG);
	}

	/**
	 * Retrieves user profile for current user.
	 */
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.USER_PROFILE_GET_ACTION)
	public @ResponseBody DataTransferBean getUserProfile() {
		String username = ((UserWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();		
		
		UserProfile userProfile = userService.getUserProfileByUsername(username);
		if (userProfile != null) {
			DataTransferBean userProfileBean = new UserProfileBean(
				userProfile.getFirstName(),
				userProfile.getLastName(),
				userProfile.getGender().toString()
			);
			return userProfileBean;
		}
		return null;
	}
	
	/**
	 * Action responsible for updating user profile.
	 */
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.USER_PROFILE_UPDATE_ACTION)
	public @ResponseBody DataTransferBean doUpdateUserProfile(String firstName, String lastName, String gender) {
		UserProfileBean userProfileBean = new UserProfileBean(firstName, lastName, gender);
		
		// validation
		ValidationUtils.validate(userProfileBean, "firstName", "First name", userProfileBean.getFirstName(), UserProfile.MIN_FIRST_NAME_LENGTH, UserProfile.MAX_FIRST_NAME_LENGTH);
		ValidationUtils.validate(userProfileBean, "lastName", "Last name", userProfileBean.getLastName(), UserProfile.MIN_LAST_NAME_LENGTH, UserProfile.MAX_LAST_NAME_LENGTH);		
		try {
			Gender.valueOf(gender.toUpperCase());
		} catch (Exception e) {
			userProfileBean.reject("gender", "Wrong value for gender field");
		}
		
		// actions
		if (userProfileBean.getStatus() == DataTransferBean.STATUS_OK) {
			// String username = ((UserWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
			// userService.updateUserProfile(username, firstName, lastName, Gender.valueOf(gender.toUpperCase()));
		}
		return userProfileBean;
	}

	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.USER_LOGIN_ACTION)
	public @ResponseBody UserLoginResponse login(@RequestBody UserLoginRequest request) throws IOException {
		UserLoginResponse response = new UserLoginResponse();
		try {
			// processing
			User user = userService.getUserByEmail(request.getEmail());
			if (user != null) {
				if (user.getPassword().equals(SecurityUtils.toBase64(SecurityUtils.toMD5(request.getPassword())))) {
					response.setSessionId(new Random().nextLong());
				} else {
					response.addError(UserError.PASSWORD_WRONG);
				}
			} else {
				response.addError(UserError.EMAIL_DOES_NOT_EXIST);
			}
		} catch (Exception e) {
			response.addError(UserError.UNEXPECTED_ERROR);
			logger.error("Unexpected error in WS on user login", e);
		}
		response.normalize();
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.USER_REGISTRATION_ACTION)
	public @ResponseBody UserRegistrationResponse register(@RequestBody UserRegistrationRequest request) throws IOException {
		UserRegistrationResponse response = new UserRegistrationResponse();
		try {
			// validate
			userNameValidator.validate(request.getName(), response.getErrors());
			userEmailValidator.validate(request.getEmail(), response.getErrors());
			userPasswordValidator.validate(request.getPassword(), response.getErrors());
			if (!response.hasErrors()) {
				if (userService.getUserByEmail(request.getEmail()) != null) {
					response.addError(UserError.DUBLICATED_USER);
				}
				if (!request.getPassword().equals(request.getConfirm())) {
					response.addError(UserError.PASSWORD_NOT_MATCH);
				}
			}
			
			// if there were no validation errors
			if (!response.hasErrors()) {
				response.setUserId(userService.createUser(request.getName(), request.getEmail(), request.getPassword()));
			}
		} catch (Exception e) {
			response.addError(UserError.UNEXPECTED_ERROR);
			logger.error("User registration error", e);
		}
		response.normalize();
		return response;
	}
	
}
