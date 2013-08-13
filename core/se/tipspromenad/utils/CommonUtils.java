package se.tipspromenad.utils;

import java.util.UUID;

/**
 * Contains misc helper functions.
 * 
 * @author guligo
 */
public class CommonUtils {

	public final static int CODE_LENGTH = 8;

	public static String generateCode() {
		return generateCode(CODE_LENGTH);
	}

	public static String generateCode(int length) {
		try {
			String code = String.valueOf(UUID.randomUUID());
			code.replace("-", "");
			return code.substring(0, length);
		} catch (Exception e) {
			return null;
		}
	}

}
