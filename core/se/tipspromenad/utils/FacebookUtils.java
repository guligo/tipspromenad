package se.tipspromenad.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import se.tipspromenad.entities.User;
import se.tipspromenad.entities.UserProfile;
import se.tipspromenad.entities.enums.Gender;

/**
 * Helper functions for working with FB social network.
 * 
 * @author guligo
 * @author pavelefimov
 */
public class FacebookUtils {

	private final static String GET_FACEBOOK_ACCESS_TOKEN_URL = "https://graph.facebook.com/me?access_token=%s";

	public final static String FIELD_ID            = "id";	
	public final static String FIELD_EMAIL         = "email";
	public final static String FIELD_NAME          = "name";
	public final static String FIELD_GENDER        = "gender";
	public final static String FIELD_LOCATION      = "location";
	public final static String FIELD_LOCATION_NAME = "name";
	public final static int    FIELD_CITY          = 0;
	public final static int    FIELD_COUNTRY       = 1;
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getAccessToken(String accessToken) throws MalformedURLException, IOException {
		String content = CommonUtils.doHttpsGet(String.format(GET_FACEBOOK_ACCESS_TOKEN_URL, accessToken));
		return new ObjectMapper().readValue(content, HashMap.class);
	}

	public static void createUser(Map<String, Object> info, User user) {		
		String email = (String) info.get(FIELD_EMAIL);				
        if (email != null) {
        	user.setEmail(email);
        }
        
        updateUser(info, user);
	}

	public static void updateUser(Map<String, Object> info, User user) {
		String id = (String) info.get(FIELD_ID);                        
        if (id != null) {
        	user.setFbUserId(id);
        }
        
        String name = (String) info.get(FIELD_NAME);
        if (name != null) {
        	user.setName(name);
        }
	}

	@SuppressWarnings("unchecked")
	public static void updateUserProfile(Map<String, Object> info, UserProfile userProfile) {				
		String name = (String) info.get(FIELD_NAME);
		if (name != null) {
			userProfile.setName(name);
		}
		
		String gender = (String) info.get(FIELD_GENDER);		
		if (gender != null) {
			userProfile.setGender(Gender.valueOf(gender.toUpperCase()));
		}
		
		Map<String, Object> location = (Map<String, Object>) info.get(FIELD_LOCATION);
		if (location != null) {
			name = (String) location.get(FIELD_LOCATION_NAME);
			if (name != null) {
				String parts[] = name.split(", ");
				if (parts.length > FIELD_COUNTRY) {
					userProfile.setCity(parts[FIELD_CITY]);
					userProfile.setCountry(parts[FIELD_COUNTRY]);
				}
			}
		}
	}

}
