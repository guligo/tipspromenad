package se.tipspromenad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.tipspromenad.beans.DataTransferBean;
import se.tipspromenad.beans.GameBean;
import se.tipspromenad.entities.Game;
import se.tipspromenad.globals.Constants;
import se.tipspromenad.security.UserWrapper;
import se.tipspromenad.services.GameService;
import se.tipspromenad.services.UserService;
import se.tipspromenad.utils.ValidationUtils;

/**
 * MVC paradigm controller responsible for actions on game page.
 * 
 * @author eigogul
 */
@Controller
public class GameController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private GameService gameService;

	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.GAME_PAGE)
	public String showGamePage() {
		return Constants.Views.GAME;
	}

	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.GAME_GET)
	public @ResponseBody DataTransferBean getGame(Long id) {
		Game game = gameService.getGame(id);
		return new GameBean(game.getId(), game.getName());
	}

	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.GAME_SAVE)
	public @ResponseBody DataTransferBean saveGame(Long id, String name) {
		String username = ((UserWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();	
		
		GameBean gameBean = new GameBean(id, name);
		ValidationUtils.validate(gameBean, "name", "Name", gameBean.getName(), Game.MIN_NAME_LENGTH, Game.MAX_NAME_LENGTH);
		if (gameBean.getStatus() == DataTransferBean.STATUS_OK) {
			gameService.saveGame(gameBean.getId(), gameBean.getName(), userService.getUserByUsername(username));
		}
		return gameBean;
	}

}
