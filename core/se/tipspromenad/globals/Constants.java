package se.tipspromenad.globals;

import java.util.Properties;

/**
 * Contains constants used by classes across the whole application.
 * 
 * @author guligo
 * @author pavelefimov
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
	public static interface URL {
		
		public final static String LOGIN_PAGE                                = "login";
		public final static String LOGIN_GET_SUCCESS_RESULT_ACTION           = "login/success";
		public final static String LOGIN_GET_FAIL_RESULT_ACTION              = "login/fail";
		public final static String LOGIN_VERIFY_FACEBOOK_ACCESS_TOKEN_ACTION = "login/verify";
		public final static String LOGIN_VERIFY_CAPTCHA                      = "login/captcha";
		
		public final static String LOGOUT_ACTION = "logout";
		
		public final static String USER_LOGIN_ACTION            = "user/login";
		public final static String USER_REGISTRATION_ACTION     = "user/register";
		public final static String USER_PROFILE_GET_ACTION      = "user/profile";
		public final static String USER_PROFILE_UPDATE_ACTION   = "user/profile";
		public final static String USER_PROFILE_FACEBOOK_ACTION = "user/profile/facebook";
		
		public final static String NEWS_PAGE            = "news.page";
		public final static String RULES_PAGE           = "rules.page";
		public final static String GETTING_STARTED_PAGE = "getting-started.page";
		public final static String HOME_PAGE            = RULES_PAGE;
		
		public final static String WALLPAPER_PAGE = "wallpaper.page";
		
		public final static String WIREFRAMES_PAGE = "wireframes.jsp"; // NOTE: Dirty solution for dev / production env problem
		
		public final static String GAME_LIST_PAGE       = "gamelist.page";
		public final static String GAME_SAVE_PAGE       = "gamesave.page";
		public final static String GAME_GET_ACTION      = "game/get/{id}";
		public final static String GAME_LIST_ACTION     = "game/list";
		public final static String GAME_SAVE_ACTION     = "game/save";
		public final static String GAME_REMOVE_ACTION   = "game/remove/{id}";
		public final static String GAME_FINALIZE_ACTION = "game/finalize/{id}";
		public final static String GAME_DISCARD_ACTION  = "game/discard/{id}";
		
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
		
		public final static String INVITATION_LIST_ACTION   = "invitation/list/{gameId}";
		public final static String INVITATION_SAVE_ACTION   = "invitation/save";
		public final static String INVITATION_REMOVE_ACTION = "invitation/remove/{gameId}/{fbUserId}";
		
		public final static String PLAY_GET_ACTION          = "play/{id}";
		public final static String PLAY_LIST_BY_USER_ACTION = "play/listbyuser/{userId}";
		public final static String PLAY_LIST_BY_GAME_ACTION = "play/listbygame/{gameId}";
		public final static String PLAY_CREATE_ACTION       = "play";
		public final static String PLAY_UPDATE_ACTION       = "play";
		public final static String PLAY_REMOVE_ACTION       = "play/{id}";
		
		public final static String CLUB_LIST_PAGE   = "clubs.page";
		public final static String CLUB_LIST_ACTION = "club/list";
		
	}
	
	/**
	 * Holds view names.
	 */
	public static interface Views {
		
		public final static String WALLPAPER = "wallpaper";
		
		public final static String WIREFRAMES = "wireframes";
		
		public final static String ERROR = "error";
		
		public final static String LOGIN = "login";
		
		public final static String NEWS = "news";
		
		public final static String RULES = "rules";
		
		public final static String GETTING_STARTED = "getting-started";
		
		public final static String GAME_LIST = "game-list";
		
		public final static String GAME_SAVE = "game-save";
		
		public final static String CLUB_LIST = "club-list";
		
	}
	
	/**
	 * Holds names for session attributes.
	 */
	public static interface Attributes {
		
		public final static String SHOW_LOGIN_DIALOG = "showLoginDialog";
		
	}
	
}
