package se.tipspromenad.tests.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import se.tipspromenad.controllers.game.GameController;
import se.tipspromenad.controllers.game.GameSaveResponse;
import se.tipspromenad.entities.Game;
import se.tipspromenad.globals.Constants;
import se.tipspromenad.tests.utils.Order;

/**
 * Unit tests for {@link GameController}.
 * 
 * @author guligo
 */
public class GameControllerTest extends AbstractControllerTest {

	@SuppressWarnings("unused")
    private final static Logger logger = Logger.getLogger(GameControllerTest.class);
	
	private final static Long DEFAULT_TEST_GAME_ID = 1L;
	
	private final static String TEST_GAME_NAME = "Riga Live";
	private final static String TEST_GAME_DATE = "06/28/2013";
	
	@Autowired
	private GameController gameController;

	@Before
	public void init() throws Exception {
		super.init();
	}

	@Test
	@Order(order = 1)
	public void testGetGame() throws Exception {
		Game game = getJSON(Game.class, gameController, "/" + Constants.URL.GAME_GET_ACTION.replace("{id}", Long.toString(DEFAULT_TEST_GAME_ID)));
		
		assertNotNull(game);
		assertNull(game.getCreator().getId());
		assertNull(game.getCreator().getPassword());
		assertNull(game.getCreator().getRole());
		assertNull(game.getCreator().getEnabled());
		assertEquals(DEFAULT_TEST_GAME_ID, game.getId());
	}

	@Test
	@Order(order = 2)
	public void testCreateGame() throws Exception {
		authenticate(UserControllerTest.DEFAULT_TEST_USER_EMAIL, UserControllerTest.DEFAULT_TEST_USER_PASSWORD);
		postJSON(
			GameSaveResponse.class,
			gameController,
			"/" + Constants.URL.GAME_SAVE_ACTION,
			"{'name':'" + TEST_GAME_NAME + "','date':'" + TEST_GAME_DATE + "'}"
		);
	}

	@Test
	@Order(order = 3)
	@SuppressWarnings("unchecked")
	public void testGetGameList() throws Exception {
		authenticate(UserControllerTest.DEFAULT_TEST_USER_EMAIL, UserControllerTest.DEFAULT_TEST_USER_PASSWORD);
        List<Game> games = getJSON(List.class, gameController, "/" + Constants.URL.GAME_LIST_ACTION);
        
        assertNotNull(games);
        assertEquals(1, games.size());
	}

}
