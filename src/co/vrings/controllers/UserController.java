package co.vrings.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.vrings.beans.DataTransferBean;
import co.vrings.beans.UserRegistrationBean;
import co.vrings.beans.UserProfileBean;
import co.vrings.entities.User;
import co.vrings.entities.UserProfile;
import co.vrings.entities.enums.Gender;
import co.vrings.globals.Constants;
import co.vrings.security.UserWrapper;
import co.vrings.services.UserService;
import co.vrings.utils.ValidationUtils;

/**
 * Controller of MVC paradigm that is responsible for {@link User} related actions.
 * 
 * @author guligo
 */
@Controller
public class UserController {
	
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * Action responsible for performing registration of a new user.
	 */
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.USER_REGISTRATION_ACTION)
	public @ResponseBody DataTransferBean doRegistration(String email, String username, String password, String confirm) {
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
	
}
