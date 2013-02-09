package co.vrings.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.vrings.beans.DataTransferBean;
import co.vrings.beans.EntryBean;
import co.vrings.beans.EntryNoteBean;
import co.vrings.beans.EntrySickDayBean;
import co.vrings.beans.EntrySportBean;
import co.vrings.entities.Attribute;
import co.vrings.entities.AttributePerformance;
import co.vrings.entities.Entry;
import co.vrings.entities.EntryNote;
import co.vrings.entities.EntrySickDay;
import co.vrings.entities.EntrySport;
import co.vrings.entities.Measurement;
import co.vrings.entities.MeasurementUnit;
import co.vrings.entities.impl.EntryBodyImpl;
import co.vrings.entities.impl.EntryImpl;
import co.vrings.entities.impl.EntrySportImpl;
import co.vrings.globals.Constants;
import co.vrings.security.UserWrapper;
import co.vrings.services.EntryService;
import co.vrings.services.MeasurementService;

/**
 * Controller of MVC paradigm that is responsible for {@link Entry} related actions.
 * 
 * @author guligo
 */
@Controller
public class EntriesController {
	
	private final DateFormat DEFAULT_FORMATTER = new SimpleDateFormat("dd-MM-yyyy");
	
	@Autowired
	private EntryService entryService;
	@Autowired
	private MeasurementService measurementService;
	
	/**
	 * Shows entries page.
	 */
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.ENTRIES_PAGE)
	public String doShowEntriesPage() {
		return Constants.Views.ENTRIES;
	}
	
	/**
	 * Returns entry with corresponding id from database.
	 */
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.ENTRY_GET_ACTION)
	public @ResponseBody Entry getEntry(Long id) {
		return entryService.getEntry(id);
	}
	
	/**
	 * Removes entry with corresponding id from database.
	 */
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.ENTRY_REMOVE_ACTION)
	public @ResponseBody void removeEntry(Long id) {
		entryService.removeEntry(id);
	}
	
	/**
	 * Returns list of all entries that belongs to user.
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.ENTRY_ALL_ACTION)
	public @ResponseBody List<EntryBean> getEntries() {
		// get current user
		String username = ((UserWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		
		// generate result
		List<EntryBean> result = new ArrayList<EntryBean>();		
		List<EntryImpl> entries = (List<EntryImpl>) entryService.getEntriesByUsername(username);
		if (entries != null) {
			for (Entry entry : entries) {
				if (entry instanceof EntrySportImpl) {
					((EntrySportImpl) entry).setMeasurements(measurementService.getMeasurements(entry.getId()));
				} else if (entry instanceof EntryBodyImpl) {					
					((EntryBodyImpl) entry).setMeasurements(new HashSet<Measurement>(measurementService.getMeasurements(entry.getId())));
				}
				result.add(new EntryBean(entry));
			}
		}
		return result;
	}
	
	/**
	 * Returns list of user entries for corresponding date.
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.ENTRY_GET_LIST_ACTION)
	public @ResponseBody List<EntryBean> getEntries(@RequestParam(required = false) String date) throws ParseException {
		if (date == null) {
			date = DEFAULT_FORMATTER.format(new Date());
		} else {
			try {
				DEFAULT_FORMATTER.parse(date);
			} catch (Exception e) {
				date = DEFAULT_FORMATTER.format(new Date());
			}
		}
		
		// get current user
		String username = ((UserWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		
		// generate result
		List<EntryBean> result = new ArrayList<EntryBean>();		
		List<EntryImpl> entries = (List<EntryImpl>) entryService.getEntriesByUsername(username, DEFAULT_FORMATTER.parse(date));
		if (entries != null) {
			for (Entry entry : entries) {
				if (entry instanceof EntrySportImpl) {
					((EntrySportImpl) entry).setMeasurements(measurementService.getMeasurements(entry.getId()));
				} else if (entry instanceof EntryBodyImpl) {					
					((EntryBodyImpl) entry).setMeasurements(new HashSet<Measurement>(measurementService.getMeasurements(entry.getId())));
				}
				result.add(new EntryBean(entry));
			}
		}
		return result;
	}
	
	/**
	 * Creates sport entry.	
	 */
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.ENTRY_SPORT_CREATE_ACTION)
	public @ResponseBody DataTransferBean createSportEntry(String date, long sportId, Long duration, String note, Integer intensity, Integer mood, boolean isPrivate) throws ParseException {
		// get current user
		String username = ((UserWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		
		// validation
		EntrySportBean sportEntryBean = new EntrySportBean(date, sportId, duration, note, intensity, mood);
		if (sportEntryBean.getDuration() == null) {
			sportEntryBean.reject("duration", "Duration may not be empty");
		}
		if (sportEntryBean.getNote().length() > EntrySport.MAX_NOTES_LENGTH) {
			sportEntryBean.reject("note", "Note is too long");
		}
		try {
			DEFAULT_FORMATTER.parse(date);
		} catch (Exception e) {
			sportEntryBean.reject("date", "Something is very wrong here");
		}
		
		// actions		
		entryService.createSportEntry(username, DEFAULT_FORMATTER.parse(date), sportId, duration, note, intensity, mood, isPrivate);
		return sportEntryBean;
	}
	
	/**
	 * Creates body entry.
	 */
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.ENTRY_BODY_CREATE_ACTION)
	public @ResponseBody int createBodyEntry(String date, String note, boolean isPrivate) throws ParseException {
		// get current user
		String username = ((UserWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		
		// actions		
		entryService.createBodyEntry(username, DEFAULT_FORMATTER.parse(date), note, isPrivate);
		return DataTransferBean.STATUS_OK;
	}
	
	/**
	 * Creates sick day entry.
	 */
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.ENTRY_SICK_DAY_CREATE_ACTION)
	public @ResponseBody DataTransferBean createSickDayEntry(String date, String note, boolean isPrivate) throws ParseException {
		EntrySickDayBean sickDayEntryBean = new EntrySickDayBean(date, note);
		
		// validation
		if (sickDayEntryBean.getNote().length() > EntrySickDay.MAX_NOTES_LENGTH) {
			sickDayEntryBean.reject("note", "Note is too long");
		}
		
		// actions
		if (sickDayEntryBean.getStatus() == DataTransferBean.STATUS_OK) {
			String username = ((UserWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
			entryService.createSickDayEntry(username, DEFAULT_FORMATTER.parse(date), note, isPrivate);
		}
		return sickDayEntryBean;
	}
	
	/**
	 * Creates note entry.
	 */
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.ENTRY_NOTE_CREATE_ACTION)
	public @ResponseBody DataTransferBean createNoteEntry(String date, String note, boolean isPrivate) throws ParseException {
		EntryNoteBean noteEntryBean = new EntryNoteBean(date, note);

		// validation
		if (noteEntryBean.getNote() != null
				&& noteEntryBean.getNote().length() > EntryNote.MAX_NOTES_LENGTH) {
			noteEntryBean.reject("note", "Note is too long");
		} else if (noteEntryBean.getNote() == null
				|| noteEntryBean.getNote().length() < EntryNote.MIN_NOTES_LENGTH) {
			noteEntryBean.reject("note", "Sorry, empty note is not allowed here");
		}
		try {
			DEFAULT_FORMATTER.parse(date);
		} catch (Exception e) {
			noteEntryBean.reject("date", "Something is very wrong here");
		}
		
		// actions
		if (noteEntryBean.getStatus() == DataTransferBean.STATUS_OK) {
			String username = ((UserWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
			entryService.createNoteEntry(username, DEFAULT_FORMATTER.parse(date), note, isPrivate);
		}
		return noteEntryBean;
	}

	/**
	 * Get available measurement units.
	 */
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.ENTRY_GET_MEASUREMENT_UNITS)
	public @ResponseBody List<MeasurementUnit> getMeasurementUnits() {
		return measurementService.getMeasurementUnits();
	}
	
	/**
	 * Create measurement of predefined performance attribute.
	 */
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.ENTRY_CREATE_PERFORMANCE_MEASUREMENT_FOR_PREDEFINED_ATTRIBUTE_ACTION)
	public @ResponseBody void createPerformanceAttributeMeasurement(long entryId, long attributeId, double measurementValue) {		
		// actions
		measurementService.createMeasurement(entryId, attributeId, measurementValue);
	}
	
	/**
	 * Create measurement of custom performance attribute.
	 */
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.ENTRY_CREATE_PERFORMANCE_MEASUREMENT_FOR_CUSTOM_ATTRIBUTE_ACTION)
	public @ResponseBody void createPerformanceAttributeMeasurement(long entryId, String attributeName, double measurementValue, long measurementUnitId) {
		// get current user
		String username = ((UserWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		
		// actions	
		if (measurementService.getPerformanceAttributeByName(username, attributeName) == null) {
			measurementService.createPerformanceAttribute(username, 1, attributeName, measurementUnitId);
		}		
		Attribute attribute = measurementService.getPerformanceAttributeByName(username, attributeName);	
		measurementService.createMeasurement(entryId, attribute.getId(), measurementValue);
	}
	
	/**
	 * Create measurement of performance attribute.
	 */
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.ENTRY_CREATE_BODY_MEASUREMENT_ACTION)
	public @ResponseBody void createBodyAttributeMeasurement(long entryId, String attributeName, double measurementValue, long measurementUnitId) {
		// get current user
		String username = ((UserWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		
		// actions	
		if (measurementService.getBodyAttributeByName(username, attributeName) == null) {
			measurementService.createBodyAttribute(username, attributeName, measurementUnitId);
		}		
		Attribute attribute = measurementService.getBodyAttributeByName(username, attributeName);	
		measurementService.createMeasurement(entryId, attribute.getId(), measurementValue);
	}
	
	/**
	 * Remove measurement.
	 */
	@RequestMapping(method = RequestMethod.POST, value = Constants.URL.ENTRY_REMOVE_MEASUREMENT_ACTION)
	public @ResponseBody void removeMeasurement(long measurementId) {
		measurementService.removeMeasurement(measurementId);
	}
	
	/**
	 * Returns list of performance attributes available for particular user and particular sport.
	 */
	@RequestMapping(method = RequestMethod.GET, value = Constants.URL.ENTRY_GET_PERFORMANCE_ATTRIBUTES_ACTION)
	public @ResponseBody List<AttributePerformance> getPerformanceAttributes(long sportId) {
		// get current user
		String username = ((UserWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();		
		return measurementService.getPerformanceAttributes(username, sportId);
	}
	
}
