package se.tipspromenad.tests.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import se.tipspromenad.controllers.game.GameController;
import se.tipspromenad.controllers.game.GameSaveResponse;
import se.tipspromenad.controllers.placemark.PlacemarkController;
import se.tipspromenad.controllers.question.QuestionController;
import se.tipspromenad.controllers.user.UserController;
import se.tipspromenad.controllers.user.UserLoginResponse;
import se.tipspromenad.controllers.user.UserRegistrationResponse;
import se.tipspromenad.entities.Game;
import se.tipspromenad.entities.Placemark;
import se.tipspromenad.services.GameService;
import se.tipspromenad.tests.utils.Order;

/**
 * Tests game creation flow that is to be performed by web-service client app.
 * 
 * @author guligo
 * @author pavelefimov
 */
public class GameCreateTest extends AbstractControllerTest {

	private final DateFormat DATE_FORMATTER = new SimpleDateFormat("MM/dd/yyyy");

	@Autowired
	private GameService gameService;

	@Autowired
	private UserController userController;
	@Autowired
	private GameController gameController;
	@Autowired
	private QuestionController questionController;
	@Autowired
	private PlacemarkController placemarkController;

	@Before
	public void init() throws Exception {
		super.init();
	}

	@Test
	@Order(order = 1)
	public void register() throws Exception {
		UserRegistrationResponse response = postJSON(
			UserRegistrationResponse.class,
			userController,
			"/user/register",
			"{'email':'foobar@foo.bar','name':'Foo Bar', 'password':'qwerty', 'confirm': 'qwerty'}"
		);
		
		assertNotNull(response);
		assertEquals(0, response.getErrors().size());
		assertNotNull(response.getUserId());
	}

	@Test
	@Order(order = 2)
	public void login() throws Exception {
		UserLoginResponse response = postJSON(
			UserLoginResponse.class,
			userController,
			"/user/login",
			"{'email':'foobar@foo.bar','password':'qwerty'}"
		);
		
		// assertion
		assertNotNull(response);
		assertEquals(0, response.getErrors().size());
		assertNotNull(response.getSessionId());
		
		// authenticate user
		authenticate("foobar@foo.bar", "qwerty");
	}

	@Test
	@Order(order = 3)
	public void createGame() throws Exception {
		GameSaveResponse response = postJSON(
			GameSaveResponse.class,
			gameController,
			"/game/save",
			"{'name':'Test Game','date':'06/28/2013'}"
		);
		
		assertNotNull(response);
		assertEquals(0, response.getErrors().size());
		assertNotNull(response.getId());
	}

	@Test
	@Order(order = 4)
	public void createQuestion1() throws Exception {
		Long response = postJSON(
			Long.class,
			questionController,
			"/question/save",
			"{'gameId':1,'question':{'text':'How much is 2 x 2?','answers':[{'text':'1','correct':false},{'text':'4','correct':true},{'text':'Infinity','correct':false}],'sequence':1}}"
		);
		
		assertNotNull(response);
		assertEquals(Long.valueOf(1), response);
	}

	@Test
	@Order(order = 5)
	public void createQuestion2() throws Exception {
		Long response = postJSON(
			Long.class,
			questionController,
			"/question/save",
			"{'gameId':1,'question':{'text':'What is the capital of Sweden?','answers':[{'text':'Oslo','correct':false},{'text':'Helsinki','correct':false},{'text':'Stockholm','correct':true}],'sequence':2}}"
		);
		
		assertNotNull(response);
		assertEquals(Long.valueOf(2), response);
	}

	@Test
	@Order(order = 6)
	public void createQuestion3() throws Exception {
		Long response = postJSON(
			Long.class,
			questionController,
			"/question/save",
			"{'gameId':1,'question':{'text':'What is the best app in the world?','answers':[{'text':'Facebook','correct':false},{'text':'Twitter','correct':false},{'text':'Tipspromenad','correct':true}],'sequence':3}}"
		);
		
		assertNotNull(response);
		assertEquals(Long.valueOf(3), response);
	}

	@Test
	@Order(order = 7)
	public void rearrangeQuestions() throws Exception {
		Integer response = postJSON(
			Integer.class,
			questionController,
			"/question/movedown/1/1",
			null
		);
		assertEquals(Integer.valueOf(2), response);
		
		response = postJSON(
			Integer.class,
			questionController,
			"/question/moveup/1/3",
			null
		);
		assertEquals(Integer.valueOf(2), response);
		
		response = postJSON(
			Integer.class,
			questionController,
			"/question/moveup/1/2",
			null
		);
		assertEquals(Integer.valueOf(1), response);
	}

	@Test
	@Order(order = 8)
	public void putPlacemarks() throws Exception {
		Placemark response = postJSON(
			Placemark.class,
			placemarkController,
			"/placemark/save",
			"{'gameId':1,'questionId':1,'latitude':1,'longitude':3}"
		);
		
		assertNotNull(response);
		assertNotNull(response.getId());
		assertEquals(Long.valueOf(1), response.getId());
		
		response = postJSON(
			Placemark.class,
			placemarkController,
			"/placemark/save",
			"{'gameId':1,'questionId':2,'latitude':3,'longitude':2}"
		);
		
		assertNotNull(response);
		assertNotNull(response.getId());
		assertEquals(Long.valueOf(2), response.getId());
		
		response = postJSON(
			Placemark.class,
			placemarkController,
			"/placemark/save",
			"{'gameId':1,'questionId':3,'latitude':2,'longitude':1}"
		);
		
		assertNotNull(response);
		assertNotNull(response.getId());
		assertEquals(Long.valueOf(3), response.getId());
	}

	@Test
	@Order(order = 9)
	public void finalize() throws Exception {
		Boolean response = postJSON(
			Boolean.class,
			gameController,
			"/game/finalize/1",
			null
		);
		
		assertNotNull(response);
		assertTrue(response);
	}

	@Test
	@Order(order = 10)
	public void getCode() throws Exception {
		Game game = getJSON(
			Game.class,
			gameController,
			"/game/get/1"
		);
		
		assertNotNull(game);
		assertEquals("Test Game", game.getName());
		assertEquals(DATE_FORMATTER.parse("06/28/2013"), game.getDate());
		assertNotNull(game.getCode());
	}

	@Test
	@Order(order = 11)
	public void discard() throws Exception {
		Boolean response = postJSON(
			Boolean.class,
			gameController,
			"/game/discard/1",
			null
		);
		
		assertNotNull(response);
		assertTrue(response);
		assertNull(gameService.getGame(1L));
	}

}
