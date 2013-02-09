package co.vrings.tests.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @see http://stackoverflow.com/questions/3089151/specifying-an-order-to-junit-4-tests-at-the-method-level-not-class-level
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Order {
	
	public int order();
	
}
