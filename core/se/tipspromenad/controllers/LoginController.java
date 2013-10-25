package se.tipspromenad.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.tipspromenad.beans.DataTransferBean;
import se.tipspromenad.beans.VerifyAccessTokenBean;
import se.tipspromenad.entities.User;
import se.tipspromenad.globals.Constants;
import se.tipspromenad.services.UserService;
import se.tipspromenad.utils.FacebookUtils;
import se.tipspromenad.utils.SecurityUtils;

/**
 * MVC controller responsible for actions around authentication and authorization mechanisms.
 * 
 * @author guligo
 */
@Controller
public class LoginController {
	
	private final static Logger logger = Logger.getLogger(LoginController.class);
	
	private final static String FIELD_NAME             = "name";
	private final static String FIELD_EMAIL            = "email";
	private final static String FIELD_ID               = "id";	
	private final static int    RANDOM_PASSWORD_LENGTH = 10;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.LOGIN_PAGE)
	public String showLoginPage() {
		return Constants.Views.LOGIN;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.LOGIN_GET_SUCCESS_RESULT_ACTION)
	public @ResponseBody int getLoginSuccessResult() {
		return DataTransferBean.STATUS_OK;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.LOGIN_GET_FAIL_RESULT_ACTION)
	public @ResponseBody int getLoginFailResult() {
		return DataTransferBean.STATUS_NOK;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.LOGIN_VERIFY_FACEBOOK_ACCESS_TOKEN_ACTION)
	public @ResponseBody DataTransferBean verifyAccessToken(HttpServletRequest request, HttpServletResponse response, String accessToken) throws Exception {
		Map<String, Object> info = FacebookUtils.getAccessToken(accessToken);
		logger.debug("Information retrieved from FB together with access token = " + info);
		
		String email          = (String) info.get(FIELD_EMAIL);
		String name           = (String) info.get(FIELD_NAME);
		String fbUserId       = (String) info.get(FIELD_ID);		
		String fbUserPassword = SecurityUtils.generate(RANDOM_PASSWORD_LENGTH);
		
		User user = userService.getUserByFbId(fbUserId);
		if (user == null) {
			logger.debug("User does not exist in database, so creating a new one...");
			user = userService.getUser(userService.createUser(name, email, fbUserId, SecurityUtils.toBase64(SecurityUtils.toMD5(fbUserPassword))));
		} else {
			user.setFbUserPassword(SecurityUtils.toBase64(SecurityUtils.toMD5(fbUserPassword)));
			userService.updateUser(user);
		}
		return new VerifyAccessTokenBean(user.getEmail(), fbUserPassword);
	}
	
}
