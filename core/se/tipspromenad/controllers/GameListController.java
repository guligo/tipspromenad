package se.tipspromenad.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.tipspromenad.entities.Game;
import se.tipspromenad.globals.Constants;
import se.tipspromenad.security.UserWrapper;
import se.tipspromenad.services.GameService;

/**
 * Represents MVC controller responsible for actions on and around game list page.
 * 
 * @author guligo
 */
@Controller
public class GameListController {

	@Autowired
	private GameService gameService;

	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.GAME_LIST_PAGE)
	public String showGameListPage() {
		return Constants.Views.GAME_LIST;
	}

	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.GAME_LIST_GET_LIST)
	public @ResponseBody List<Game> getGameList() {
		String username = ((UserWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		return gameService.getGamesByUsername(username);
	}

}
