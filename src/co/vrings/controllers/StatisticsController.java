package co.vrings.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.vrings.entities.Attribute;
import co.vrings.entities.Measurement;
import co.vrings.globals.Constants;
import co.vrings.security.UserWrapper;
import co.vrings.services.MeasurementService;

/**
 * MVC controller that is responsible for actions around "Statistics" page.
 * 
 * @author guligo
 */
@Controller
public class StatisticsController {
	
	@Autowired
	private MeasurementService measurementService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/stats")
	public @ResponseBody Map<Long, List<Measurement>> getMeasurements(long attributeId, long start, long end) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(start);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date _start = calendar.getTime();
		
		calendar.setTimeInMillis(end);		
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date _end = calendar.getTime();
		
		// usage of LinkedHashMap is important as it maintains order of keys
		Map<Long, List<Measurement>> result = new LinkedHashMap<Long, List<Measurement>>();
		while (true) {			
			if (_start.getTime() <= _end.getTime()) {
				result.put(_start.getTime(), measurementService.getMeasurementsByAttribute(attributeId, _start));			
				_start = new Date(_start.getTime() + 24 * 60 * 60 * 1000);
			} else {
				break;
			}
		}
		return result;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.STATISTICS_PAGE)
	public String doShowStatisticsPage(ModelMap map) {
		Map<String, List<Measurement>> measurements = new HashMap<String, List<Measurement>>();
		
		// 3 days ago
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -3);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		// show measurements for last 7 days starting from 3 days ago
		Date dates[] = new Date[7];
		for (int i = 0; i < 7; i++) {
			dates[i] = calendar.getTime();
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		// get current user
		String username = ((UserWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		
		// get performance attributes of current user
		List<? extends Attribute> attributes = measurementService.getPerformanceAttributes(username);		
		for (Attribute attribute : attributes) {
			for (Date date : dates) {
				date.setHours(0);
				measurements.put(attribute.getName() + date.getTime(), measurementService.getMeasurementsByAttribute(attribute.getId(), date));
			}
		}
		map.put("performanceAttributes", attributes);
		map.put("measurements", measurements);
		map.put("dates", dates);
		
		return Constants.Views.STATISTICS;
	}
	
}
