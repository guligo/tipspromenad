package se.tipspromenad.controllers.game;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.tipspromenad.controllers.ResponseBean;
import se.tipspromenad.entities.Game;
import se.tipspromenad.globals.Constants;
import se.tipspromenad.security.UserWrapper;
import se.tipspromenad.services.GameService;
import se.tipspromenad.services.UserService;
import se.tipspromenad.validation.BasicDateValidator;
import se.tipspromenad.validation.BasicStringValidator;

/**
 * MVC paradigm controller responsible for actions on game page.
 * 
 * @author eigogul
 */
@Controller
public class GameController {
	
	private static Logger logger = Logger.getLogger(GameController.class);
	
	private final DateFormat DATE_FORMATTER = new SimpleDateFormat("MM/dd/yyyy");
	
	// services
	@Autowired
	private UserService userService;
	@Autowired
	private GameService gameService;
	
	// validators
	private BasicStringValidator gameNameValidator;
	private BasicDateValidator   gameDateValidator;
	
	public GameController() {
		gameNameValidator = new BasicStringValidator(Game.MIN_NAME_LENGTH, Game.MAX_NAME_LENGTH, GameError.NAME_EMPTY, GameError.NAME_TOO_SHORT, GameError.NAME_TOO_LONG);
		gameDateValidator = new BasicDateValidator(DATE_FORMATTER, GameError.DATE_EMPTY, GameError.DATE_WRONG_FORMAT);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.GAME_PAGE)
	public String showGamePage() {
		return Constants.Views.GAME;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.GAME_GET_ACTION)
	public @ResponseBody Game getGame(@PathVariable Long id) {
		return gameService.getGame(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.GAME_LIST_ACTION)
	public @ResponseBody List<Game> getGameList() {
		String username = ((UserWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		return gameService.getGamesByUsername(username);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.GAME_SAVE_ACTION)
	public @ResponseBody ResponseBean saveGame(@RequestBody GameSaveRequest request) throws ParseException, IOException {
		String username = ((UserWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();	
		
		GameSaveResponse response = new GameSaveResponse();
		gameNameValidator.validate(request.getName(), response.getErrors());
		gameDateValidator.validate(request.getDate(), response.getErrors());
		if (!response.hasErrors()) {
			try {
				response.setId(gameService.saveGame(request.getId(), userService.getUserByUsername(username), request.getName(), DATE_FORMATTER.parse(request.getDate())));
			} catch (Exception e) {
				logger.error("Error on saving game", e);
				response.addError(GameError.UNKNOWN);
			}
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = Constants.URL.GAME_REMOVE_ACTION)
	public @ResponseBody void removeGame(@PathVariable Long id) {
		gameService.removeGame(id);
	}
	
}
