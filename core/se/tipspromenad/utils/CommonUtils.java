package se.tipspromenad.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;

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

	public static String doHttpsGet(String url) throws MalformedURLException, IOException {
		// establish connection and send request
		HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
		connection.setRequestMethod("GET");
		connection.setDoOutput(false);
		connection.setDoInput(true);
		
		// read response
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line, content = "";
		while ((line = reader.readLine()) != null) {
			content = content + line;
		}
		return content;
	}

	public static String doHttpPost(String url, String request) throws MalformedURLException, IOException {
		// establish connection and send request
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Length", String.valueOf(request.length()));
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.setDoOutput(true);
		connection.setDoInput(true);
		
		// write request
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
		writer.write(request);
		writer.flush();
		
		// read response
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line, content = "";
		while ((line = reader.readLine()) != null) {
			content = content + line + "\n";
		}
		return content;
	}

}
