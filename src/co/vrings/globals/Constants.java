package co.vrings.globals;

/**
 * Contains constants used by classes across the whole application.
 * 
 * @author guligo
 */
public class Constants {
	
	/**
	 * Holds URLs.
	 */
	public static class URL {
		
		public final static String LOGIN_PAGE = "login";
		public final static String LOGIN_GET_SUCCESS_RESULT_ACTION = "login/success";
		public final static String LOGIN_GET_FAIL_RESULT_ACTION = "login/fail";
		
		public final static String USER_REGISTRATION_ACTION = "user/register";
		public final static String USER_PROFILE_GET_ACTION = "user/get-profile";
		public final static String USER_PROFILE_UPDATE_ACTION = "user/update-profile";
		
		public final static String CALENDAR_PAGE = "calendar";
		
		public final static String ENTRIES_PAGE = "entries";
		public final static String ENTRY_GET_ACTION = "entry/get";
		public final static String ENTRY_REMOVE_ACTION = "entry/remove";
		public final static String ENTRY_ALL_ACTION = "entry/all";
		public final static String ENTRY_GET_LIST_ACTION = "entry/list";
		public final static String ENTRY_SPORT_CREATE_ACTION = "entry/create-sport-entry";
		public final static String ENTRY_BODY_CREATE_ACTION = "entry/create-body-entry";
		public final static String ENTRY_SICK_DAY_CREATE_ACTION = "entry/create-sick-day-entry";
		public final static String ENTRY_NOTE_CREATE_ACTION = "entry/create-note-entry";
		public final static String ENTRY_GET_MEASUREMENT_UNITS = "entry/measurement-units";		
		public final static String ENTRY_CREATE_PERFORMANCE_MEASUREMENT_FOR_PREDEFINED_ATTRIBUTE_ACTION = "entry/create-performance-measurement-for-predefined-attribute";
		public final static String ENTRY_CREATE_PERFORMANCE_MEASUREMENT_FOR_CUSTOM_ATTRIBUTE_ACTION = "entry/create-performance-measurement-for-custom-attribute";
		public final static String ENTRY_CREATE_BODY_MEASUREMENT_ACTION = "entry/create-body-measurement";
		public final static String ENTRY_REMOVE_MEASUREMENT_ACTION = "entry/remove-measurement";
		public final static String ENTRY_GET_PERFORMANCE_ATTRIBUTES_ACTION = "entry/get-performance-attributes";
		
		public final static String STATISTICS_PAGE = "statistics";
		
		public final static String ABOUT_PAGE = "about";
		
	}
	
	/**
	 * Holds view names.
	 */
	public static class Views {
		
		public final static String LOGIN = "login";
		
		public final static String CALENDAR = "calendar";
		
		public final static String ENTRIES = "entries";
		
		public final static String STATISTICS = "statistics";
		
		public final static String ABOUT = "about";
		
	}
	
}
