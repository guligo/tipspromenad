package se.tipspromenad.tests.ws;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import se.tipspromenad.tests.controllers.AbstractControllerTest;
import se.tipspromenad.utils.SerializationUtils;
import se.tipspromenad.ws.beans.UserLoginRequestBean;
import se.tipspromenad.ws.beans.UserRegistrationRequestBean;


public class UserWebServiceTest extends AbstractControllerTest {
	
	@Before
	public void init() throws Exception {
		super.init();
	}
	
	@Test
	public void loginTest() throws ServletException, IOException {
		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/ws/users/login");
		request.setContentType("application/json");
		request.setContent("{'email':'foo.bar@qwer.ty','password':'qwerty123'}".replaceAll("'", "\"").getBytes());
		
		MockHttpServletResponse response = new MockHttpServletResponse();
		dispatcher.service(request, response);
		
		assertEquals(HttpServletResponse.SC_OK, response.getStatus());
	}
	
	@Test
	public void registerTest() throws JsonGenerationException, JsonMappingException, IOException, ServletException {
		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/ws/users/register");
		request.setContentType("application/json");
		request.setContent("{'username':'foobar','email':'foo.bar@qwer.ty','password':'qwerty123'}".replaceAll("'", "\"").getBytes());
		
		MockHttpServletResponse response = new MockHttpServletResponse();
		dispatcher.service(request, response);
		
		assertEquals(HttpServletResponse.SC_OK, response.getStatus());
	}
	
}
