package se.tipspromenad.tests.controllers;

import org.apache.log4j.Logger;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import se.tipspromenad.controllers.user.UserController;
import se.tipspromenad.controllers.user.UserLoginResponse;
import se.tipspromenad.controllers.user.UserRegistrationResponse;
import se.tipspromenad.entities.User;
import se.tipspromenad.globals.Constants;
import se.tipspromenad.services.UserService;
import se.tipspromenad.tests.utils.Order;

/**
 * Tests {@link UserWebService}.
 * 
 * @author eigogul
 */
public class UserControllerTest extends AbstractControllerTest {
	
	private final static Logger logger = Logger.getLogger(UserControllerTest.class);
	
	private final static String TEST_USER_USERNAME = "foobar";
	private final static String TEST_USER_EMAIL = "foo.bar@qwer.ty";
	private final static String TEST_USER_PASSWORD = "qwerty123";
	
	private final static String TEST_USER_WRONG_USERNAME = "foo";
	private final static String TEST_USER_WRONG_EMAIL = "bar";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserController userController;
	
	@Before
	public void init() throws Exception {
		super.init();
	}
	
	@Test
	@Order(order = 1)
	public void unsuccessfulRegistrationTest() throws Exception {
		UserRegistrationResponse bean = postJSON(
			UserRegistrationResponse.class,
			userController,
			"/" + Constants.URL.USER_REGISTRATION_ACTION,
			"{'username':'" + TEST_USER_WRONG_USERNAME + "','email':'" + TEST_USER_WRONG_EMAIL + "','password':'" + TEST_USER_PASSWORD + "'}"
		);
		
		assertNotNull(bean.getErrors());
		assertTrue(bean.getErrors().size() > 0);
	}
	
	@Test
	@Order(order = 2)
	public void successfulRegistrationTest() throws Exception {
		// make sure that there is no such user in DB
		User user = userService.getUserByEmail(TEST_USER_EMAIL);
		if (user != null) {
			userService.removeUser(user.getId());
		}
		assertNull(userService.getUserByEmail(TEST_USER_EMAIL));
		
		UserRegistrationResponse bean = postJSON(
			UserRegistrationResponse.class,
			userController,
			"/" + Constants.URL.USER_REGISTRATION_ACTION,
			"{'username':'" + TEST_USER_USERNAME + "','email':'" + TEST_USER_EMAIL + "','password':'" + TEST_USER_PASSWORD + "'}"
		);
		
		assertEquals(0, bean.getErrors().size());
		assertNotNull(bean.getUserId());
		
		user = userService.getUserByEmail(TEST_USER_EMAIL);
		assertEquals(bean.getUserId(), user.getId());
		assertEquals(TEST_USER_USERNAME, user.getUsername());
		assertEquals(TEST_USER_EMAIL, user.getEmail());
	}
	
	@Test
	@Order(order = 3)
	public void successfulLoginTest() throws Exception {
		UserLoginResponse bean = postJSON(
			UserLoginResponse.class,
			userController,
			"/" + Constants.URL.USER_LOGIN_ACTION,
			"{'email':'" + TEST_USER_EMAIL + "','password':'" + TEST_USER_PASSWORD + "'}"
		);
		
		assertNotNull(bean);
	}
	
}
