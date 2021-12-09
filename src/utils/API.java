package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;

/**
 * class API
 * @author TranVanTuyen_20184012
 *
 */
public class API {

	/**
	 * Attribute help format time
	 */
	public static DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	/**
	 * Attribute help log info into console
	 */
	private static Logger LOGGER = Utils.getLogger(Utils.class.getName());

	/**
	 * get data by GET method 
	 * @param url path request to server
	 * @param token code provide to authenticate user
	 * @return response from server
	 * @throws Exception
	 * @author TuyenTV_20184012
	 */
	public static String get(String url, String token) throws Exception {
		LOGGER.info("Request URL: " + url + "\n");
		
		//setup connection
		HttpURLConnection conn = (HttpURLConnection) API.getConnection(url, token, "GET");
		
		//read data from server
		String response = readResponse(conn);
		return response;
	}
	
	/**
	 * call POST payment api 
	 * @param url request path to server
	 * @param data data send to server
	 * @return response from server
	 * @throws IOException
	 * @author TuyenTV_20184012
	 */
	public static String post(String url, String data, String token) throws IOException {
		allowMethods("PATCH");
		
		//part 1: setup
		HttpURLConnection conn = (HttpURLConnection) API.getConnection(url, token, "POST");
		String payload = data;
		LOGGER.info("Request Info:\nRequest URL: " + url + "\n" + "Payload Data: " + payload + "\n");
		
		//part 2: send data
		Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		writer.write(payload);
		writer.close();
		
		//part 3: read data return from server
		String response = readResponse(conn);
		return response;
	}

	/**
	 * Method allow call API method type as PATCH, PUT,..( only run with Java 11)
	 * @deprecated only run with Java <=11
	 * @param methods method need allow (PATCH, PUT,..)
	 * @author TranVanTuyen_20184012
	 */
	private static void allowMethods(String... methods) {
		try {
			Field methodsField = HttpURLConnection.class.getDeclaredField("methods");
			methodsField.setAccessible(true);

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

			String[] oldMethods = (String[]) methodsField.get(null);
			Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
			methodsSet.addAll(Arrays.asList(methods));
			String[] newMethods = methodsSet.toArray(new String[0]);

			methodsField.set(null/* static field */, newMethods);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}
	
	/**
	 * method get data return from server
	 * @param conn connection to server
	 * @return response from server
	 * @throws IOException
	 */
	private static String readResponse(HttpURLConnection conn) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuilder respone = new StringBuilder(); // ising StringBuilder for the sake of memory and performance
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		respone.append(inputLine + "\n");
		in.close();
		LOGGER.info("Respone Info: " + respone.substring(0, respone.length() - 1).toString());
		return respone.substring(0, respone.length() - 1).toString();
	}
	
	/**
	 * get connection with server with token
	 * @param url path to connect with server
	 * @param token code which server can authenticate user
	 * @param method method call api
	 * @return HttpUrlConnection
	 * @throws IOException
	 * @author TranVanTuyen_20184012
	 */
	
	public static HttpURLConnection getConnection(String url, String token, String method) throws IOException {
		URL line_api_url = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) line_api_url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod(method);
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", "Bearer " + token);
		return conn;
	}

}
