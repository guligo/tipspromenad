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

import se.tipspromenad.entities.Game;
import se.tipspromenad.entities.Question;
import se.tipspromenad.entities.enums.GameState;
import se.tipspromenad.globals.Constants;
import se.tipspromenad.security.UserWrapper;
import se.tipspromenad.services.GameService;
import se.tipspromenad.services.PlacemarkService;
import se.tipspromenad.services.UserService;
import se.tipspromenad.validation.BasicDateValidator;
import se.tipspromenad.validation.BasicStringValidator;

/**
 * MVC paradigm controller responsible for actions on game page.
 * 
 * @author guligo
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
	@Autowired
	private PlacemarkService placemarkService;

	// validators
	private BasicStringValidator<GameError> gameNameValidator;
	private BasicDateValidator<GameError>   gameDateValidator;

	public GameController() {
		gameNameValidator = new BasicStringValidator<GameError>(Game.MIN_NAME_LENGTH, Game.MAX_NAME_LENGTH, GameError.NAME_EMPTY, GameError.NAME_TOO_SHORT, GameError.NAME_TOO_LONG);
		gameDateValidator = new BasicDateValidator<GameError>(DATE_FORMATTER, GameError.DATE_EMPTY, GameError.DATE_WRONG_FORMAT);
	}

	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.GAME_LIST_PAGE)
	public String showGameListPage() {
		return Constants.Views.GAME_LIST;
	}

	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.GAME_SAVE_PAGE)
	public String showGameSavePage() {
		return Constants.Views.GAME_SAVE;
	}

	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.GAME_GET_ACTION)
	public @ResponseBody Game getGame(@PathVariable Long id) {
		Game game = gameService.getGame(id);
		if (game != null) {
			game.getCreator().secure();
		}
		return game;
	}

	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.GAME_LIST_ACTION)
	public @ResponseBody List<Game> getGameList() {
		String email = ((UserWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		return gameService.getGamesByEmail(email);
	}

	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.GAME_SAVE_ACTION)
	public @ResponseBody GameSaveResponse saveGame(@RequestBody GameSaveRequest request) throws ParseException, IOException {
		String email = ((UserWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();	
		
		GameSaveResponse response = new GameSaveResponse();
		gameNameValidator.validate(request.getName(), response.getErrors());
		gameDateValidator.validate(request.getDate(), response.getErrors());
		if (!response.hasErrors()) {
			try {
				response.setId(gameService.saveGame(request.getId(), userService.getUserByEmail(email), request.getName(), DATE_FORMATTER.parse(request.getDate())));
			} catch (Exception e) {
				logger.error("Error on saving game", e);
				response.addError(GameError.UNKNOWN);
			}
		}
		response.normalize();
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.GAME_FINALIZE_ACTION)
	public @ResponseBody Boolean finalize(@PathVariable Long id) {
		Game game = gameService.getGame(id);
		if (game.getQuestions() != null) {
			for (Question question : game.getQuestions()) {
				if (placemarkService.getPlacemarkByGameAndQuestionId(id, question.getId()) == null) {
					return false;
				}
			}
		}
		gameService.saveGame(id, GameState.READY);
		return true;
	}

	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.GAME_DISCARD_ACTION)
	public @ResponseBody Boolean discard(@PathVariable Long id) {
		Game game = gameService.getGame(id);
		if (game.getState() == GameState.IN_PROGRESS) {
			return false;
		}
		gameService.removeGame(id);
		return true;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = Constants.URL.GAME_REMOVE_ACTION)
	public @ResponseBody void removeGame(@PathVariable Long id) {
		gameService.removeGame(id);
	}

}
