package se.tipspromenad.controllers.user;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
import se.tipspromenad.beans.UserRegistrationBean;
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
	private BasicStringValidator userNameValidator;
	private BasicStringValidator userEmailValidator;
	private BasicStringValidator userPasswordValidator;
	
	public UserController() {
		userNameValidator     = new BasicStringValidator(User.MIN_USERNAME_LENGTH, User.MAX_USERNAME_LENGTH, UserError.USERNAME_EMPTY, UserError.USERNAME_TOO_SHORT, UserError.USERNAME_TOO_LONG);
		userEmailValidator    = new BasicStringValidator(User.MIN_EMAIL_LENGTH, User.MAX_EMAIL_LENGTH, UserError.EMAIL_EMPTY, UserError.EMAIL_TOO_SHORT, UserError.EMAIL_TOO_LONG);
		userPasswordValidator = new BasicStringValidator(User.MIN_PASSWORD_LENGTH, User.MAX_PASSWORD_LENGTH, UserError.PASSWORD_EMPTY, UserError.PASSWORD_TOO_SHORT, UserError.PASSWORD_TOO_LONG);
	}
	
	/**
	 * Action responsible for performing registration of a new user.
	 */
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.USER_REGISTRATION_ACTION)
	public @ResponseBody DataTransferBean doRegistration(String email, String username, String password, String confirm) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		UserRegistrationBean registrationBean = new UserRegistrationBean(email, username, password, confirm);
		
		// validation
		ValidationUtils.validate(registrationBean, "email", "E-mail", email, User.MIN_EMAIL_LENGTH, User.MAX_EMAIL_LENGTH);
		if (!registrationBean.isRejected("email")) {
			if (userService.getUserByEmail(email) != null) {
				registrationBean.reject("email", "User with such email is already registred");
			}
		}
		ValidationUtils.validate(registrationBean, "username", "Username", username, User.MIN_USERNAME_LENGTH, User.MAX_USERNAME_LENGTH);
		if (!registrationBean.isRejected("username")) {
			if (userService.getUserByUsername(username) != null) {
				registrationBean.reject("username", "User with such username is already registred");
			}
		}
		ValidationUtils.validate(registrationBean, "password", "Password", password, User.MIN_PASSWORD_LENGTH, User.MAX_PASSWORD_LENGTH);
		if (registrationBean.getStatus() == DataTransferBean.STATUS_OK && !password.equals(confirm)) {
			registrationBean.reject("confirm", "This field must match with the password field");
		}
		
		// actions
		if (registrationBean.getStatus() == DataTransferBean.STATUS_OK) {
			userService.createUser(registrationBean.getEmail(), registrationBean.getUsername(), registrationBean.getPassword());
		}
		return registrationBean;
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
			String username = ((UserWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
			userService.updateUserProfile(username, firstName, lastName, Gender.valueOf(gender.toUpperCase()));
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
				response.addError(UserError.USERNAME_DOES_NOT_EXIST);
			}
		} catch (Exception e) {
			response.addError(UserError.UNEXPECTED_ERROR);
			logger.error("Unexpected error in WS on user login", e);
		}
		response.normalize();
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = Constants.WS.USER_REGISTER)
	public @ResponseBody UserRegistrationResponse register(@RequestBody UserRegistrationRequest request) throws IOException {
		UserRegistrationResponse response = new UserRegistrationResponse();
		try {
			// processing
			userNameValidator.validate(request.getUsername(), response.getErrors());
			userEmailValidator.validate(request.getEmail(), response.getErrors());
			userPasswordValidator.validate(request.getPassword(), response.getErrors());
			if (!response.hasErrors()) {
				if (userService.getUserByUsername(request.getUsername()) != null || userService.getUserByEmail(request.getEmail()) != null) {
					response.addError(UserError.DUBLICATED_USER);
				} else {
					response.setUserId(userService.createUser(request.getEmail(), request.getUsername(), request.getPassword()));
				}
			}
		} catch (Exception e) {
			response.addError(UserError.UNEXPECTED_ERROR);
			logger.error("Unexpected error in WS on user registration", e);
		}
		response.normalize();
		logger.info("### " + response.getUserId() + " ###");
		return response;
	}
	
}
