package se.tipspromenad.tests.controllers;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import org.junit.runner.RunWith;
import junit.framework.TestCase;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import se.tipspromenad.globals.Constants;
import se.tipspromenad.tests.utils.OrderedRunner;

/**
 * Base test case class for MVC controller tests.
 * 
 * @author guligo
 */
@RunWith(OrderedRunner.class)
@ContextConfiguration(locations = { "classpath:spring-application.xml" })
public abstract class AbstractControllerTest extends TestCase {

	private final static Logger              logger                 = Logger.getLogger(AbstractControllerTest.class);

	private final String                     HTTP_CONTENT_TYPE_JSON = "application/json";
	private final String                     HTTP_ACCEPT_JSON       = "application/json";

	protected DispatcherServlet              dispatcher;
	protected ObjectMapper                   objectMapper;
	protected ServletContext                 servletContext;
	@Autowired
	protected AnnotationMethodHandlerAdapter handler;

	@Autowired
	private ApplicationContext               applicationContext;

	@SuppressWarnings("serial")
	protected void init() throws Exception {
		servletContext = new MockServletContext();
		dispatcher = new DispatcherServlet() {

			@Override
			protected WebApplicationContext createWebApplicationContext(WebApplicationContext parent) throws BeansException {
				GenericWebApplicationContext gwac = new GenericWebApplicationContext();
				gwac.setParent(applicationContext);
				gwac.refresh();
				return gwac;
			}
		};
		dispatcher.init(new MockServletConfig(servletContext));
		objectMapper = new ObjectMapper();
	};

	protected <T> T postJSON(Class<T> clazz, Object controller, String url, String content) throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest(servletContext, "POST", "/" + Constants.WS.USER_REGISTER);
		request.addHeader("Accept", HTTP_ACCEPT_JSON);
		request.addHeader("Content-Type", HTTP_CONTENT_TYPE_JSON);
		request.setCharacterEncoding(Constants.System.DEFAULT_ENCODING);

		if (content != null) {
			request.setContent(content.replaceAll("'", "\"").getBytes(Constants.System.DEFAULT_ENCODING));
		}
		logger.info(content + " -> " + url);
		MockHttpServletResponse response = new MockHttpServletResponse();
		handler.handle(request, response, controller);
		logger.info(url + " -> " + response.getContentAsString());
		return (T) objectMapper.readValue(response.getContentAsByteArray(), clazz);
	}

}
