package se.tipspromenad.ws.impl;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.apache.cxf.annotations.EndpointProperties;
import org.springframework.stereotype.Component;

import se.tipspromenad.ws.Test;

/**
 * Implementation of test WS.
 * 
 * @author guligo
 */
@Component("testServiceImpl")
@WebService(endpointInterface = "se.tipspromenad.ws.Test", serviceName = "testService")
public class TestImpl implements Test {
	
	public String doSomething() {
		return "Whatever";
	}
	
}
