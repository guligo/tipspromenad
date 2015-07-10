package se.tipspromenad.utils;

import java.security.MessageDigest;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Helper functions for security enhancements.
 * 
 * @author guligo
 * @author pavelefimov
 */
public class SecurityUtils {

	private final static Logger logger = Logger.getLogger(SecurityUtils.class);

	public static boolean isUserAuthenticated(SecurityContext context) {
		Authentication auth = context.getAuthentication();
		return auth != null && auth.isAuthenticated();
	}

	public static byte[] toMD5(String plain) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(plain.getBytes());
			return messageDigest.digest();
		} catch (Exception e) {
			logger.error("Error on encoding to MD5", e);
		}
		return null;
	}

	public static String toBase64(byte[] data) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}

	public static byte[] fromBase64(String data) {
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			return decoder.decodeBuffer(data);
		} catch (Exception e) {
			logger.error("Error on decoding from Base64", e);
		}
		return null;
	}

	public static String generate(int length) {
		byte[] password = new byte[length];
		new Random().nextBytes(password);
		return toBase64(password)
			.replaceAll("\n", "")
			.replaceAll("\r", "");
	}

}
