package se.tipspromenad.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.tipspromenad.globals.Constants;

/**
 * Represents MVC controller responsible for actions on and around game list page.
 * 
 * @author guligo
 */
@Controller
public class GameListController {

	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.GAME_LIST_PAGE)
	public String showGameListPage() {
		return Constants.Views.GAME_LIST;
	}

}
