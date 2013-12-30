package se.tipspromenad.controllers.login;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import se.tipspromenad.beans.DataTransferBean;
import se.tipspromenad.entities.User;
import se.tipspromenad.entities.UserProfile;
import se.tipspromenad.globals.Constants;
import se.tipspromenad.services.UserService;
import se.tipspromenad.utils.CommonUtils;
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
	
	private final static int    RANDOM_PASSWORD_LENGTH = 10;
	
	private final static String CAPTCHA_PRIVATE_KEY = "6LfXxesSAAAAABCx-ahT0cJuVMUnTjr8qRWakl5_";
	// private final static String CAPTCHA_PRIVATE_KEY = "6Ledy-sSAAAAAA09ppUgGNR495ZS_ZLrgeD8OHGy";
	
	private final static String IPV6_LOCALHOST = "0:0:0:0:0:0:0:1";
	private final static String IPV4_LOCALHOST = "127.0.0.1";
	
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
	public @ResponseBody LoginVerifyAccessTokenResponse verifyAccessToken(HttpServletRequest request, String accessToken) throws Exception {
		Map<String, Object> info = FacebookUtils.getAccessToken(accessToken);
		logger.debug("Information retrieved from FB together with access token = " + info);
		
		String fbUserId       = (String) info.get(FacebookUtils.FIELD_ID);
		String fbUserPassword = SecurityUtils.generate(RANDOM_PASSWORD_LENGTH);
		
		User user = userService.getUserByFbId(fbUserId);
		if (user == null) {
			logger.debug("User does not exist in database, so creating a new one...");
			
			// create user with information from FB
			user = new User();
			FacebookUtils.createUser(info, user);
			user = userService.getUser(userService.createUser(user.getName(), user.getEmail(), user.getFbUserId(), SecurityUtils.toBase64(SecurityUtils.toMD5(fbUserPassword))));
			
			// create user profile with information from FB
			UserProfile userProfile = userService.getUserProfileByEmail(user.getEmail());
			FacebookUtils.updateUserProfile(info, userProfile);
			userService.updateUserProfile(userProfile);
		} else {
			user.setFbUserPassword(SecurityUtils.toBase64(SecurityUtils.toMD5(fbUserPassword)));
			userService.updateUser(user);
		}
		return new LoginVerifyAccessTokenResponse(user.getEmail(), fbUserPassword);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.LOGIN_VERIFY_CAPTCHA)
	public @ResponseBody boolean verifyCaptcha(HttpServletRequest request, @RequestParam String challenge, @RequestParam String response) throws MalformedURLException, IOException {
		String content = "privatekey=%s&remoteip=%s&challenge=%s&response=%s";
		content = String.format(content, CAPTCHA_PRIVATE_KEY, request.getRemoteAddr().replace(IPV6_LOCALHOST, IPV4_LOCALHOST), challenge, response);
		logger.debug("Captcha request = " + content);
		
		content = CommonUtils.doHttpPost("http://www.google.com/recaptcha/api/verify", content);
		logger.debug("Captcha response = " + content);
		return content.indexOf(Boolean.toString(Boolean.TRUE)) != -1;
	}
	
}
