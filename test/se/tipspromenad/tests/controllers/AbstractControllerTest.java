package se.tipspromenad.tests.controllers;

import javax.servlet.ServletContext;

import org.codehaus.jackson.map.ObjectMapper;

import org.junit.runner.RunWith;
import junit.framework.TestCase;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import se.tipspromenad.tests.utils.OrderedRunner;


/**
 * Base test case class for MVC controller tests.
 * 
 * @author guligo
 */
@RunWith(OrderedRunner.class)
@ContextConfiguration(locations = { "classpath:application.xml" })
public abstract class AbstractControllerTest extends TestCase {
	
	protected DispatcherServlet dispatcher;	
	protected ObjectMapper objectMapper;
	
	@Autowired
	private ApplicationContext applicationContext;	
	private ServletContext servletContext;
	
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
	
}
