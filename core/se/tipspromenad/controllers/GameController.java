package se.tipspromenad.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.tipspromenad.globals.Constants;

/**
 * MVC paradigm controller responsible for actions on game page.
 * 
 * @author eigogul
 */
@Controller
public class GameController {

	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.GAME_PAGE)
	public String showGamePage() {
		return Constants.Views.GAME;
	}

}
