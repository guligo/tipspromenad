package se.tipspromenad.club;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.tipspromenad.globals.Constants;

/**
 * MVC paradigm controller responsible for actions on clubs page.
 * 
 * @author guligo
 */
@Controller
public class ClubPageController {

	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.CLUB_LIST_PAGE)
	public String showGameListPage() {
		return Constants.Views.CLUB_LIST;
	}

}
