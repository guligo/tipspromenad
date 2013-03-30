package se.tipspromenad.utils;

import java.io.Reader;
import java.io.StringReader;

import org.apache.log4j.Logger;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * Contains helper function for serialization.
 * 
 * @author eigogul
 */
public class SerializationUtils {

	public static final String TYPE_JSON = "application/json";

	private final static Logger logger = Logger.getLogger(SerializationUtils.class);

	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * FIXME: Get rid of this method as soon as Spring configurations are fixed!
	 */
	public static <T> T deserialize(Reader src, Class<T> clazz) {
		return deserialize(TYPE_JSON, src, clazz);
	}
	
	public static <T> T deserialize(String type, String src, Class<T> clazz) {
		return deserialize(type, new StringReader(src),clazz);
	}
	
	public static <T> T deserialize(String type, Reader src, Class<T> clazz) {
		try {
			if (TYPE_JSON.equals(type)) {
				return mapper.readValue(src, clazz);
			}
		} catch (Exception e) {
			logger.error("Deserialization error", e);
		}
		return null;
	}
	
	public static byte[] serialize(Object obj) {
		return serialize(TYPE_JSON, obj);
	}
	
	public static byte[] serialize(String type, Object obj) {
		try {
			if (TYPE_JSON.equals(type)) {
				return mapper.writeValueAsBytes(obj);
			}
		} catch (Exception e) {
			logger.error("Serialization error", e);
		}
		return null;
	}

}
