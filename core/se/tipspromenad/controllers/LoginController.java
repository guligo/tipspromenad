package se.tipspromenad.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.tipspromenad.beans.DataTransferBean;
import se.tipspromenad.globals.Constants;


/**
 * MVC controller responsible for actions around authentication and authorization mechanisms.
 * 
 * @author guligo
 */
@Controller
public class LoginController {
	
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
	
}
