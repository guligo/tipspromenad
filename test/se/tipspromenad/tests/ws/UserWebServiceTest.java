package se.tipspromenad.tests.ws;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import se.tipspromenad.entities.User;
import se.tipspromenad.globals.Constants;
import se.tipspromenad.services.UserService;
import se.tipspromenad.tests.controllers.AbstractControllerTest;
import se.tipspromenad.tests.utils.Order;
import se.tipspromenad.ws.UserWebService;
import se.tipspromenad.ws.beans.UserLoginResponseBean;
import se.tipspromenad.ws.beans.UserRegistrationResponseBean;

/**
 * Tests {@link UserWebService}.
 * 
 * @author eigogul
 */
public class UserWebServiceTest extends AbstractControllerTest {
	
	private final static Logger logger = Logger.getLogger(UserWebServiceTest.class);
	
	private final static String TEST_USER_USERNAME = "foobar";
	private final static String TEST_USER_EMAIL = "foo.bar@qwer.ty";
	private final static String TEST_USER_PASSWORD = "qwerty123";
	
	private final static String TEST_USER_WRONG_USERNAME = "foo";
	private final static String TEST_USER_WRONG_EMAIL = "bar";
	
	@Autowired
	private UserService userService;
	
	@Before
	public void init() throws Exception {
		super.init();
	}
	
	@Test
	@Order(order = 1)
	public void unsuccessfulRegistrationTest() throws ServletException, IOException {
		MockHttpServletRequest request = new MockHttpServletRequest("POST", Constants.WS.USER_REGISTER);
		request.setContentType("application/json");
		String content = ("{'username':'" + TEST_USER_WRONG_USERNAME + "','email':'" + TEST_USER_WRONG_EMAIL + "','password':'" + TEST_USER_PASSWORD + "'}").replaceAll("'", "\"");
		request.setContent(content.getBytes());
		
		logger.info(content + " -> " + Constants.WS.USER_REGISTER);
		MockHttpServletResponse response = new MockHttpServletResponse();
		dispatcher.service(request, response);
		logger.info(Constants.WS.USER_REGISTER + " -> " + response.getContentAsString());
		
		assertEquals(HttpServletResponse.SC_OK, response.getStatus());
		UserRegistrationResponseBean bean = objectMapper.readValue(response.getContentAsByteArray(), UserRegistrationResponseBean.class);
		assertNotNull(bean.getErrorCodes());
		assertNull(bean.getUserId());
	}
	
	@Test
	@Order(order = 2)
	public void successfulRegistrationTest() throws JsonGenerationException, JsonMappingException, IOException, ServletException {
		// make sure that there is no such user in DB
		User user = userService.getUserByEmail(TEST_USER_EMAIL);
		if (user != null) {
			userService.removeUser(user.getId());
		}
		assertNull(userService.getUserByEmail(TEST_USER_EMAIL));
		
		// do the actual testing
		MockHttpServletRequest request = new MockHttpServletRequest("POST", Constants.WS.USER_REGISTER);
		request.setContentType("application/json");
		String content = ("{'username':'" + TEST_USER_USERNAME + "','email':'" + TEST_USER_EMAIL + "','password':'" + TEST_USER_PASSWORD + "'}").replaceAll("'", "\"");
		request.setContent(content.getBytes());
		
		logger.info(content + " -> " + Constants.WS.USER_REGISTER);
		MockHttpServletResponse response = new MockHttpServletResponse();
		dispatcher.service(request, response);
		logger.info(Constants.WS.USER_REGISTER + " -> " + response.getContentAsString());
		
		assertEquals(HttpServletResponse.SC_OK, response.getStatus());
		UserRegistrationResponseBean bean = objectMapper.readValue(response.getContentAsByteArray(), UserRegistrationResponseBean.class);
		assertEquals(0, bean.getErrorCodes().size());
		assertNotNull(bean.getUserId());
		
		user = userService.getUserByEmail(TEST_USER_EMAIL);
		assertEquals(bean.getUserId(), user.getId());
		assertEquals(TEST_USER_USERNAME, user.getUsername());
		assertEquals(TEST_USER_EMAIL, user.getEmail());
	}
	
	@Test
	@Order(order = 3)
	public void successfulLoginTest() throws ServletException, IOException {
		MockHttpServletRequest request = new MockHttpServletRequest("POST", Constants.WS.USER_LOGIN);
		request.setContentType("application/json");
		String content = ("{'email':'" + TEST_USER_EMAIL + "','password':'" + TEST_USER_PASSWORD + "'}").replaceAll("'", "\"");
		request.setContent(content.getBytes());
		
		logger.info(content + " -> " + Constants.WS.USER_LOGIN);
		MockHttpServletResponse response = new MockHttpServletResponse();
		dispatcher.service(request, response);
		logger.info(Constants.WS.USER_LOGIN + " -> " + response.getContentAsString());
		
		assertEquals(HttpServletResponse.SC_OK, response.getStatus());
		UserLoginResponseBean bean = objectMapper.readValue(response.getContentAsByteArray(), UserLoginResponseBean.class);
		assertEquals(0, bean.getErrorCodes().size());
		assertNotNull(bean.getSessionId());
	}
	
}
