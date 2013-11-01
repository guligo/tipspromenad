package se.tipspromenad.tests.controllers;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import se.tipspromenad.controllers.play.PlayController;
import se.tipspromenad.tests.utils.Order;

/**
 * Tests {@link PlayController}.
 * 
 * @author guligo
 */
public class PlayControllerTest extends AbstractControllerTest {
	
	private final static Logger logger = Logger.getLogger(PlayControllerTest.class);
	
	@Autowired
	private PlayController playController;
	
	@Before
	public void setUp() throws Exception {
		super.init();
		logger.debug("Set up phase completed");
	}
	
	@After
	public void tearDown() {
		logger.debug("Tear down phase completed");
	}
	
	@Test
	@Order(order = 1)
	public void testGetPlay() {
		authenticate(UserControllerTest.DEFAULT_TEST_USER_EMAIL, UserControllerTest.DEFAULT_TEST_USER_PASSWORD);
	}
	
	@Test
	@Order(order = 2)
	public void testGetPlaysByUser() {
		authenticate(UserControllerTest.DEFAULT_TEST_USER_EMAIL, UserControllerTest.DEFAULT_TEST_USER_PASSWORD);
	}
	
}
