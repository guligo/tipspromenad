package se.tipspromenad.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.tipspromenad.entities.Game;
import se.tipspromenad.entities.impl.GameImpl;
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

	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.GAME_LIST_GET_LIST)
	public @ResponseBody List<Game> getGameList() {
		List<Game> games = new ArrayList<Game>();
		
		Game game = new GameImpl();
		game.setName("Game in Karlskrona");
		game.setCreationDate(new Date());
		games.add(game);
		
		game = new GameImpl();
		game.setName("Riga Game");
		game.setCreationDate(new Date());
		games.add(game);
		return games;
	}
	
}
