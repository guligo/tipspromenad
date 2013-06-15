package se.tipspromenad.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.tipspromenad.globals.Constants;

/**
 * Represents MVC home page controller.
 * 
 * @author guligo
 */
@Controller
public class HomePageController {
	
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.HOME_PAGE)
	public String showHomePage() {
		return Constants.Views.HOME;
	}
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception e) {
		return Constants.Views.ERROR;
	}
	
}
