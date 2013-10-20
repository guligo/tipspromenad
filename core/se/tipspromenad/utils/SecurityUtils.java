package se.tipspromenad.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Helper functions for security enhancements.
 * 
 * @author eigogul
 */
public class SecurityUtils {

	public static boolean isUserAuthenticated(SecurityContext context) {
		Authentication auth = context.getAuthentication();
		return auth != null && auth.isAuthenticated();
	}

	public static byte[] toMD5(String plain) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(plain.getBytes());
		return messageDigest.digest();
	}

	public static String toBase64(byte[] data) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}

	public static byte[] fromBase64(String data) throws IOException {
		BASE64Decoder decoder = new BASE64Decoder();
		return decoder.decodeBuffer(data);
	}

	public static String generate(int length) {
		byte[] password = new byte[length];
		new Random().nextBytes(password);
		return toBase64(password)
			.replaceAll("\n", "")
			.replaceAll("\r", "");
	}

}
