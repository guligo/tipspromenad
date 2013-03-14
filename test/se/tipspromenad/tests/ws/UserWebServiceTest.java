package se.tipspromenad.tests.ws;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import se.tipspromenad.controllers.UserController;
import se.tipspromenad.tests.controllers.AbstractControllerTest;
import se.tipspromenad.tests.utils.Order;
import se.tipspromenad.ws.UserWebService;

/**
 * Tests {@link UserWebService}.
 * 
 * @author eigogul
 */
public class UserWebServiceTest extends AbstractControllerTest {
	
	private final static Logger logger = Logger.getLogger(UserWebServiceTest.class);
	
	@Before
	public void init() throws Exception {
		super.init();
	}

	@Test
	@Order(order = 1)
	public void registerTest() throws JsonGenerationException, JsonMappingException, IOException, ServletException {
		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/ws/users/register");
		request.setContentType("application/json");
		request.setContent("{'username':'foobar','email':'foo.bar@qwer.ty','password':'qwerty123'}".replaceAll("'", "\"").getBytes());
		
		MockHttpServletResponse response = new MockHttpServletResponse();
		dispatcher.service(request, response);
		
		assertEquals(HttpServletResponse.SC_OK, response.getStatus());
		logger.debug(response.getContentAsString());
	}
	
	@Test
	@Order(order = 2)
	public void loginTest() throws ServletException, IOException {
		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/ws/users/login");
		request.setContentType("application/json");
		request.setContent("{'email':'foo.bar@qwer.ty','password':'qwerty123'}".replaceAll("'", "\"").getBytes());
		
		MockHttpServletResponse response = new MockHttpServletResponse();
		dispatcher.service(request, response);
		
		assertEquals(HttpServletResponse.SC_OK, response.getStatus());
		logger.debug(response.getContentAsString());
	}
	
}
