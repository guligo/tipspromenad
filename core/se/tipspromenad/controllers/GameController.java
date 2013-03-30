package se.tipspromenad.controllers;

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
import se.tipspromenad.services.UserService;
import se.tipspromenad.utils.ValidationUtils;
import se.tipspromenad.ws.beans.ResponseBean;

/**
 * MVC paradigm controller responsible for actions on game page.
 * 
 * @author eigogul
 */
@Controller
public class GameController {

	public enum ErrorCode {
		
		NAME_EMPTY,
		NAME_TOO_LONG,
		NAME_TOO_SHORT
		
	}

	@Autowired
	private UserService userService;
	
	@Autowired
	private GameService gameService;

	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.GAME_PAGE)
	public String showGamePage() {
		return Constants.Views.GAME;
	}

	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.GAME_GET)
	public @ResponseBody Game getGame(Long id) {
		return gameService.getGame(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.GAME_SAVE)
	public @ResponseBody ResponseBean saveGame(Long id, String name) {
		String username = ((UserWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();	
		
		ResponseBean response = new ResponseBean();
		ValidationUtils.validate(name, Game.MIN_NAME_LENGTH, Game.MAX_NAME_LENGTH, response.getErrorCodes(), ErrorCode.NAME_EMPTY.name(), ErrorCode.NAME_TOO_SHORT.name(), ErrorCode.NAME_TOO_LONG.name());
		if (!response.hasErrors()) {
			gameService.saveGame(id, name, userService.getUserByUsername(username));
		}
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.GAME_REMOVE)
	public @ResponseBody void removeGame(Long id) {
		gameService.removeGame(id);
	}

}
