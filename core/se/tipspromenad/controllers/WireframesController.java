package se.tipspromenad.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.tipspromenad.globals.Constants;

/**
 * MVC controller responsible for actions around wireframes dialog.
 * 
 * @author guligo
 */
@Controller
public class WireframesController {

	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.WIREFRAMES_PAGE)
	public String showWireframesPage() {
		return Constants.Views.WIREFRAMES;
	}

}
