package se.tipspromenad.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * Helper functions for working with FB social network.
 * 
 * @author guligo
 */
public class FacebookUtils {

	private final static String GET_FACEBOOK_ACCESS_TOKEN_URL = "https://graph.facebook.com/me?access_token=%s";

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getAccessToken(String accessToken) throws MalformedURLException, IOException {
		String content = CommonUtils.doHttpGet(String.format(GET_FACEBOOK_ACCESS_TOKEN_URL, accessToken));
		return new ObjectMapper().readValue(content, HashMap.class);
	}

}
