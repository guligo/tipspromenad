package se.tipspromenad.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.tipspromenad.globals.Constants;

/**
 * MVC controller responsible for actions around wallpaper page.
 * 
 * @author guligo
 */
@Controller
public class WallpaperController {

	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.WALLPAPER_PAGE)
	public String showWallpaperPage() {
		return Constants.Views.WALLPAPER;
	}
	
}
