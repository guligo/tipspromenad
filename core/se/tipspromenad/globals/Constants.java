package se.tipspromenad.globals;

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
		
		public final static String USER_LOGIN_ACTION = "user/login";
		public final static String USER_REGISTRATION_ACTION = "user/register";
		public final static String USER_PROFILE_GET_ACTION = "user/get-profile";
		public final static String USER_PROFILE_UPDATE_ACTION = "user/update-profile";
		
		public final static String HOME_PAGE = "home.page";
		
		public final static String GAME_LIST_PAGE     = "gamelist.page";
		public final static String GAME_SAVE_PAGE     = "gamesave.page";
		public final static String GAME_GET_ACTION    = "game/get/{id}";
		public final static String GAME_LIST_ACTION   = "game/list";
		public final static String GAME_SAVE_ACTION   = "game/save";
		public final static String GAME_REMOVE_ACTION = "game/remove/{id}";
		
		public final static String QUESTION_LIST_ACTION      = "question/list/{gameId}";
		public final static String QUESTION_SAVE_ACTION      = "question/save";
		public final static String QUESTION_SAVE_LIST_ACTION = "question/savelist";
		public final static String QUESTION_REMOVE_ACTION    = "question/remove/{id}";
		
		public final static String PLACEMARK_GET_ACION   = "placemark/get/{gameId}/{questionId}";
		public final static String PLACEMARK_LIST_ACTION = "placemark/list/{gameId}";
		public final static String PLACEMARK_SAVE_ACTION = "placemark/save";
		
	}
	
	/**
	 * Holds view names.
	 */
	public static class Views {
		
		public final static String LOGIN = "login";
		
		public final static String HOME = "home";
		
		public final static String GAME_LIST = "game-list";
		
		public final static String GAME_SAVE = "game-save";
		
	}
	
	/**
	 * Holds web-service end-points.
	 */
	public static class WS {
		
		public final static String USER_REGISTER = "ws/users/register";
		
	}
	
}
