package co.vrings.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.vrings.globals.Constants;

@Controller
public class CalendarPageController {
	
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.CALENDAR_PAGE)
	public String showCalendarPage() {
		return Constants.Views.CALENDAR;
	}
	
}
