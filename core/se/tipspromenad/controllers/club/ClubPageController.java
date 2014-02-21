package se.tipspromenad.controllers.club;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.tipspromenad.entities.Club;
import se.tipspromenad.globals.Constants;
import se.tipspromenad.services.ClubService;

/**
 * MVC paradigm controller responsible for actions on clubs page.
 * 
 * @author guligo
 */
@Controller
public class ClubPageController {

	@Autowired
	private ClubService clubService;

	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.CLUB_LIST_PAGE)
	public String showGameListPage(ModelMap map) {
		map.put("clubs", clubService.getClubs());
		return Constants.Views.CLUB_LIST;
	}

	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.CLUB_LIST_ACTION)
	public @ResponseBody List<Club> getClubs() {
		return clubService.getClubs();
	}

}
