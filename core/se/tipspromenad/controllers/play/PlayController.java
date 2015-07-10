package se.tipspromenad.controllers.play;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.tipspromenad.entities.Play;
import se.tipspromenad.globals.Constants;
import se.tipspromenad.services.PlayService;

/**
 * MVC controller responsible for actions around {@link Play} entity.
 * 
 * @author guligo
 * @author pavelefimov
 */
@Controller
public class PlayController {
	
	private static Logger logger = Logger.getLogger(PlayController.class);
	
	private final DateFormat DATE_FORMATTER = new SimpleDateFormat("MM/dd/yyyy");
	
	@Autowired
	private PlayService playService;
	
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.PLAY_GET_ACTION)
	public @ResponseBody Play getPlay(Long id) {
		return playService.getPlay(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.PLAY_LIST_BY_USER_ACTION)
	public @ResponseBody List<Play> getPlaysByUser(Long userId) {
		return playService.getPlaysByUser(userId);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.PLAY_LIST_BY_GAME_ACTION)
	public @ResponseBody List<Play> getPlaysByGame(Long gameId) {
		return playService.getPlaysByGame(gameId);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = Constants.URL.PLAY_CREATE_ACTION)
	public @ResponseBody PlayCreateResponse createPlay(@RequestBody PlayCreateRequest request) {
		PlayCreateResponse response = new PlayCreateResponse();
		try {
			playService.createPlay(request.getUserId(), DATE_FORMATTER.parse(request.getStart()));
		} catch (ParseException pe) {
			logger.error("Error on parsing start date", pe);
			response.addError(PlayError.START_DATE_WRONG_FORMAT);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.PLAY_UPDATE_ACTION)
	public @ResponseBody PlayUpdateResponse updatePlay(@RequestBody PlayUpdateRequest request) {
		PlayUpdateResponse response = new PlayUpdateResponse();
		try {
			playService.updatePlay(request.getId(), DATE_FORMATTER.parse(request.getEnd()));
		} catch (ParseException pe) {
			logger.error("Error on parsing end date", pe);
			response.addError(PlayError.END_DATE_WRONG_FORMAT);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = Constants.URL.PLAY_REMOVE_ACTION)
	public void removePlay(Long id) {
		playService.removePlay(id);
	}
	
}
