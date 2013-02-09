package se.tipspromenad;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Represents console launcher of the application.
 * 
 * @author guligo
 */
public class Launcher {
	
	private final static Logger log = Logger.getLogger(Launcher.class);
	
	private final static String CONFIG_LOCATION = "application.xml";
	
	public static void main(String[] args) {
		log.debug("Starting up 5rings.co console app");
		new ClassPathXmlApplicationContext(CONFIG_LOCATION);
	}
	
}
