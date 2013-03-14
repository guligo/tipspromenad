package se.tipspromenad.ws;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.tipspromenad.globals.Constants;
import se.tipspromenad.services.UserService;
import se.tipspromenad.utils.SerializationUtils;
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
	
	private final static Logger logger = Logger.getLogger(UserWebService.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, value = Constants.WS.USER_LOGIN)
	public @ResponseBody UserLoginResponseBean login(UserLoginRequestBean request, HttpServletRequest http) throws IOException {
		request = SerializationUtils.deserialize(http.getContentType(), http.getReader(), UserLoginRequestBean.class);
		
		UserLoginResponseBean response = new UserLoginResponseBean();
		response.setUserId(userService.getUserByEmail(request.getEmail()).getId());
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = Constants.WS.USER_REGISTER)
	public @ResponseBody UserRegistrationResponseBean register(UserRegistrationRequestBean request, HttpServletRequest http) throws IOException {
		request = SerializationUtils.deserialize(http.getContentType(), http.getReader(), UserRegistrationRequestBean.class);
		
		UserRegistrationResponseBean response = new UserRegistrationResponseBean();
		try {
			response.setUserId(userService.createUser(request.getEmail(), request.getUsername(), request.getPassword()));
		} catch (Exception e) {
			e.printStackTrace();
			response.setErrorCode("?");
			response.setErrorMessage(e.getMessage());
		}
		return response;
	}
	
}
