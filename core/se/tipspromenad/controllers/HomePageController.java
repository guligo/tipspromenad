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
	
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.NEWS_PAGE)
	public String showNewsPage() {
		return Constants.Views.NEWS;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.RULES_PAGE)
	public String showRulesPage() {
		return Constants.Views.RULES;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.GETTING_STARTED_PAGE)
	public String showGettingStartedPage() {
		return Constants.Views.GETTING_STARTED;
	}
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception e) {
		return Constants.Views.ERROR;
	}
	
}
