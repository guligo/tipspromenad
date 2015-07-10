package se.tipspromenad.controllers.placemark;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.tipspromenad.entities.Placemark;
import se.tipspromenad.globals.Constants;
import se.tipspromenad.services.PlacemarkService;
import se.tipspromenad.services.QuestionService;

/**
 * MVC paradigm controller responsible for actions around placemark entity.
 * 
 * @author guligo
 * @author pavelefimov
 */
@Controller
public class PlacemarkController {
	
	@Autowired
	private QuestionService questionService;
	@Autowired
	private PlacemarkService placemarkService;
	
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.PLACEMARK_LIST_ACTION)
	public @ResponseBody List<Placemark> getPlacemarks(@PathVariable Long gameId) {
		List<Placemark> placemarks = placemarkService.getPlacemarksByGameId(gameId);
		if (placemarks != null) {
			for (Placemark placemark : placemarks) {
				placemark.getQuestion().setSequence(questionService.getSequence(gameId, placemark.getQuestion().getId()));
			}
		}
		return placemarks;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.PLACEMARK_SAVE_ACTION)
	public @ResponseBody Placemark savePlacemark(@RequestBody PlacemarkSaveRequest request) {
		return placemarkService.savePlacemark(request.getId(), request.getGameId(), request.getQuestionId(), request.getLatitude(), request.getLongitude());
	}
	
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.PLACEMARK_REMOVE_ACTION)
	public @ResponseBody void removePlacemark(@PathVariable Long id) {
		placemarkService.removePlacemark(id);
	}
	
}
