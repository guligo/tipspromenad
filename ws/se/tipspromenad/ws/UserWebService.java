package se.tipspromenad.ws;

import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.tipspromenad.entities.User;
import se.tipspromenad.globals.Constants;
import se.tipspromenad.services.UserService;
import se.tipspromenad.utils.SecurityUtils;
import se.tipspromenad.utils.SerializationUtils;
import se.tipspromenad.utils.ValidationUtils;
import se.tipspromenad.ws.beans.UserLoginRequestBean;
import se.tipspromenad.ws.beans.UserLoginResponseBean;
import se.tipspromenad.ws.beans.UserRegistrationRequestBean;
import se.tipspromenad.ws.beans.UserRegistrationResponseBean;

/**
 * Web-service methods for manipulation with users.
 * 
 * @author eigogul
 */
@Controller
public class UserWebService {
	
	public enum ErrorCode {
		
		USERNAME_EMPTY,
		USERNAME_TOO_SHORT,
		USERNAME_TOO_LONG,
		USERNAME_DOES_NOT_EXIST,
		EMAIL_EMPTY,
		EMAIL_TOO_SHORT,
		EMAIL_TOO_LONG,
		PASSWORD_EMPTY,
		PASSWORD_TOO_SHORT,
		PASSWORD_TOO_LONG,
		PASSWORD_WRONG,
		DUBLICATED_USER,
		UNEXPECTED_ERROR
		
	}
	
	private final static Logger logger = Logger.getLogger(UserWebService.class);
	
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST, value = Constants.WS.USER_REGISTER)
	public @ResponseBody UserRegistrationResponseBean register(UserRegistrationRequestBean request, HttpServletRequest http) throws IOException {
		UserRegistrationResponseBean response = new UserRegistrationResponseBean();
		try {
			request = SerializationUtils.deserialize(http.getContentType(), http.getReader().readLine(), UserRegistrationRequestBean.class);
			
			// processing
			ValidationUtils.validate(request.getUsername(), User.MIN_USERNAME_LENGTH, User.MAX_USERNAME_LENGTH, response.getErrorCodes(), ErrorCode.USERNAME_EMPTY.name(), ErrorCode.USERNAME_TOO_SHORT.name(), ErrorCode.USERNAME_TOO_LONG.name());
			ValidationUtils.validate(request.getEmail(), User.MIN_EMAIL_LENGTH, User.MAX_EMAIL_LENGTH, response.getErrorCodes(), ErrorCode.EMAIL_EMPTY.name(), ErrorCode.EMAIL_TOO_SHORT.name(), ErrorCode.EMAIL_TOO_LONG.name());
			ValidationUtils.validate(request.getPassword(), User.MIN_PASSWORD_LENGTH, User.MAX_PASSWORD_LENGTH, response.getErrorCodes(), ErrorCode.PASSWORD_EMPTY.name(), ErrorCode.PASSWORD_TOO_SHORT.name(), ErrorCode.PASSWORD_TOO_LONG.name());
			if (!response.hasErrors()) {
				if (userService.getUserByUsername(request.getUsername()) != null || userService.getUserByEmail(request.getEmail()) != null) {
					response.addErrorCode(ErrorCode.DUBLICATED_USER.name());
				} else {
					response.setUserId(userService.createUser(request.getEmail(), request.getUsername(), request.getPassword()));
				}
			}
		} catch (Exception e) {
			response.addErrorCode(ErrorCode.UNEXPECTED_ERROR.name());
			logger.error("Unexpected error in WS on user registration", e);
		}
		response.normalize();
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = Constants.WS.USER_LOGIN)
	public @ResponseBody UserLoginResponseBean login(UserLoginRequestBean request, HttpServletRequest http) throws IOException {
		UserLoginResponseBean response = new UserLoginResponseBean();
		try {
			request = SerializationUtils.deserialize(http.getContentType(), http.getReader().readLine(), UserLoginRequestBean.class);
			
			// processing
			User user = userService.getUserByEmail(request.getEmail());
			if (user != null) {
				if (user.getPassword().equals(SecurityUtils.toBase64(SecurityUtils.toMD5(request.getPassword())))) {
					response.setSessionId(new Random().nextLong());
				} else {
					response.addErrorCode(ErrorCode.PASSWORD_WRONG.name());
				}
			} else {
				response.addErrorCode(ErrorCode.USERNAME_DOES_NOT_EXIST.name());
			}
		} catch (Exception e) {
			response.addErrorCode(ErrorCode.UNEXPECTED_ERROR.name());
			logger.error("Unexpected error in WS on user login", e);
		}
		response.normalize();
		return response;
	}
	
}
