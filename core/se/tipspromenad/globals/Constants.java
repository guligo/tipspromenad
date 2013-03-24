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

		public final static String USER_REGISTRATION_ACTION = "user/register";
		public final static String USER_PROFILE_GET_ACTION = "user/get-profile";
		public final static String USER_PROFILE_UPDATE_ACTION = "user/update-profile";

		public final static String HOME_PAGE = "home";

		public final static String GAME_LIST_PAGE = "games";
		public final static String GAME_LIST_GET_LIST = "games/list";
		
	}

	/**
	 * Holds view names.
	 */
	public static class Views {

		public final static String LOGIN = "login";

		public final static String HOME = "home";

		public final static String GAME_LIST = "game-list";

	}

	/**
	 * Holds web-service end-points.
	 */
	public static class WS {

		public final static String USER_LOGIN = "/ws/users/login";
		public final static String USER_REGISTER = "/ws/users/register";

	}

}
