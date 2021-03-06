package se.tipspromenad.controllers.user;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.tipspromenad.entities.User;
import se.tipspromenad.entities.UserProfile;
import se.tipspromenad.entities.enums.Gender;
import se.tipspromenad.globals.Constants;
import se.tipspromenad.security.UserWrapper;
import se.tipspromenad.services.UserService;
import se.tipspromenad.utils.FacebookUtils;
import se.tipspromenad.utils.SecurityUtils;
import se.tipspromenad.validation.BasicDateValidator;
import se.tipspromenad.validation.BasicStringValidator;

/**
 * Controller of MVC paradigm that is responsible for {@link User} related actions.
 * 
 * @author guligo
 * @author pavelefimov
 */
@Controller
public class UserController {
	
	private final static Logger logger = Logger.getLogger(UserController.class);	
	
	private final DateFormat DATE_FORMATTER = new SimpleDateFormat("MM/dd/yyyy");
	
	// services
	@Autowired
	private UserService userService;
	
	// user
	private BasicStringValidator<UserError> userNameValidator;
	private BasicStringValidator<UserError> userEmailValidator;
	private BasicStringValidator<UserError> userPasswordValidator;
	
	// user profile
	private BasicDateValidator  <UserError> userProfileBirthDateValidator;
	private BasicStringValidator<UserError> userProfileCountryValidator;
	private BasicStringValidator<UserError> userProfileCityValidator;
	
	public UserController() {
		// user
		userNameValidator     = new BasicStringValidator<UserError>(User.MIN_NAME_LENGTH, User.MAX_NAME_LENGTH, UserError.NAME_EMPTY, UserError.NAME_TOO_SHORT, UserError.NAME_TOO_LONG);
		userEmailValidator    = new BasicStringValidator<UserError>(User.MIN_EMAIL_LENGTH, User.MAX_EMAIL_LENGTH, UserError.EMAIL_EMPTY, UserError.EMAIL_TOO_SHORT, UserError.EMAIL_TOO_LONG);
		userPasswordValidator = new BasicStringValidator<UserError>(User.MIN_PASSWORD_LENGTH, User.MAX_PASSWORD_LENGTH, UserError.PASSWORD_EMPTY, UserError.PASSWORD_TOO_SHORT, UserError.PASSWORD_TOO_LONG);
		
		// user profile
		userProfileBirthDateValidator = new BasicDateValidator  <UserError>(DATE_FORMATTER, UserError.BIRTH_DATE_WRONG_FORMAT);
		userProfileCountryValidator   = new BasicStringValidator<UserError>(UserProfile.MIN_COUNTRY_LENGTH, UserProfile.MAX_COUNTRY_LENGTH, UserError.COUNTRY_TOO_SHORT, UserError.COUNTRY_TOO_LONG);
		userProfileCityValidator      = new BasicStringValidator<UserError>(UserProfile.MIN_CITY_LENGTH, UserProfile.MAX_CITY_LENGTH, UserError.CITY_TOO_SHORT, UserError.COUNTRY_TOO_LONG);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.USER_LOGIN_ACTION)
	public @ResponseBody UserLoginResponse loginUser(@RequestBody UserLoginRequest request) throws IOException {
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
	public @ResponseBody UserRegistrationResponse registerUser(@RequestBody UserRegistrationRequest request) throws IOException {
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
	
	/**
	 * Retrieves user profile for current user.
	 */
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.USER_PROFILE_GET_ACTION)
	public @ResponseBody UserProfile getUserProfile() {
		String email = ((UserWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();		
		UserProfile userProfile = userService.getUserProfileByEmail(email);
		return userProfile;
	}
	
	/**
	 * Action responsible for updating user profile.
	 */
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.USER_PROFILE_UPDATE_ACTION)
	public @ResponseBody UserProfileUpdateResponse updateUserProfile(@RequestBody UserProfileUpdateRequest request) throws ParseException {	
		UserProfileUpdateResponse response = new UserProfileUpdateResponse();		
		
		// validation
		userNameValidator.validate(request.getName(), response.getErrors());
		userProfileBirthDateValidator.validate(request.getBirthDate(), response.getErrors());
		userProfileCountryValidator.validate(request.getCountry(), response.getErrors());
		userProfileCityValidator.validate(request.getCity(), response.getErrors());
		
		// processing
		if (!response.hasErrors()) {
			String email = ((UserWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
			userService.updateUserProfile(
				email,
				request.getName(),
				request.getGender() != null ? Gender.valueOf(request.getGender()) : null,
				request.getBirthDate() != null ? DATE_FORMATTER.parse(request.getBirthDate()) : null,
				request.getCountry(),
				request.getCity()
			);
		}		
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.USER_PROFILE_FACEBOOK_ACTION)
	public @ResponseBody Boolean connectUserProfile(HttpServletRequest request, String accessToken) throws Exception {
		String email = ((UserWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		try {
			Map<String, Object> info = FacebookUtils.getAccessToken(accessToken);
			logger.debug("Information retrieved from FB together with access token = " + info);
			
			// update user with information from FB
			User user = userService.getUserByEmail(email);
			FacebookUtils.updateUser(info, user);
			userService.updateUser(user);
			
			// update user profile with information from FB
			UserProfile userProfile = userService.getUserProfileByEmail(email);			
			FacebookUtils.updateUserProfile(info, userProfile);
			userService.updateUserProfile(userProfile);									
			return true;
		} catch (Exception e) {
			logger.error("Error on connecting user profile with FB", e);
		}
		return false;
	}
	
}
