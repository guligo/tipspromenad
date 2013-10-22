package se.tipspromenad.globals;

import java.util.Properties;

/**
 * Contains constants used by classes across the whole application.
 * 
 * @author guligo
 */
public class Constants {
	
	private static Properties props; 
	
	static {
		props = new Properties();
		try {
			props.load(Constants.class.getResourceAsStream("/tipspromenad.properties"));
		} catch (Exception e) {
			throw new RuntimeException("Could not read tipspromenad.properties file");
		}
	}
	
	/**
	 * Holds system constants.
	 */
	public static interface System {
		
		public final static String VERSION          = props.getProperty("system.version");
		public final static String BUILD_NUMBER     = props.getProperty("system.buildNumber");
		public final static String BUILD_SERVER     = props.getProperty("system.buildServer");
		public final static String DEFAULT_ENCODING = "UTF-8";
		
	}
	
	/**
	 * Holds URLs.
	 */
	public static class URL {
		
		public final static String LOGIN_PAGE                                = "login";
		public final static String LOGIN_GET_SUCCESS_RESULT_ACTION           = "login/success";
		public final static String LOGIN_GET_FAIL_RESULT_ACTION              = "login/fail";
		public final static String LOGIN_VERIFY_FACEBOOK_ACCESS_TOKEN_ACTION = "login/verify";
		
		public final static String LOGOUT_ACTION = "logout";
		
		public final static String USER_LOGIN_ACTION          = "user/login";
		public final static String USER_REGISTRATION_ACTION   = "user/register";
		public final static String USER_PROFILE_GET_ACTION    = "user/get-profile";
		public final static String USER_PROFILE_UPDATE_ACTION = "user/update-profile";
		
		public final static String HOME_PAGE = "home.page";
		
		public final static String WALLPAPER_PAGE = "wallpaper.page";
		
		public final static String GAME_LIST_PAGE     = "gamelist.page";
		public final static String GAME_SAVE_PAGE     = "gamesave.page";
		public final static String GAME_GET_ACTION    = "game/get/{id}";
		public final static String GAME_LIST_ACTION   = "game/list";
		public final static String GAME_SAVE_ACTION   = "game/save";
		public final static String GAME_REMOVE_ACTION = "game/remove/{id}";
		
		public final static String QUESTION_GET_ACTION       = "question/get/{id}";
		public final static String QUESTION_LIST_ACTION      = "question/list/{gameId}";
		public final static String QUESTION_SAVE_ACTION      = "question/save";
		public final static String QUESTION_SAVE_LIST_ACTION = "question/savelist";
		public final static String QUESTION_REMOVE_ACTION    = "question/remove/{id}";
		public final static String QUESTION_MOVEUP_ACTION    = "question/moveup/{gameId}/{questionId}";
		public final static String QUESTION_MOVEDOWN_ACTION  = "question/movedown/{gameId}/{questionId}";
		
		public final static String PLACEMARK_GET_ACION     = "placemark/get/{gameId}/{questionId}";
		public final static String PLACEMARK_LIST_ACTION   = "placemark/list/{gameId}";
		public final static String PLACEMARK_SAVE_ACTION   = "placemark/save";
		public final static String PLACEMARK_REMOVE_ACTION = "placemark/remove/{id}";
		
	}
	
	/**
	 * Holds view names.
	 */
	public static class Views {
		
		public final static String WALLPAPER = "wallpaper";
		
		public final static String ERROR = "error";
		
		public final static String LOGIN = "login";
		
		public final static String HOME = "home";

		public final static String GAME_LIST = "game-list";
		
		public final static String GAME_SAVE = "game-save";
		
	}
	
}
