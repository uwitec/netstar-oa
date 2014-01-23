package com.zs198893.netstar_oa.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.zs198893.netstar_oa.config.DefaultConfig;
import com.zs198893.netstar_oa.config.WebServerConfig;

/**
 * This class encapsulates methods for requesting a server via HTTP GET/POST and
 * provides methods for parsing response from the server.
 * 
 * @author zhangshuai
 * 
 */
public class HttpUtility {
	private CookieManager manager = new CookieManager();
	/**
	 * Represents an HTTP connection
	 */
	private HttpURLConnection httpConn;
	private static SharedPreferences sp;

	private HttpUtility() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static synchronized HttpUtility getInstance(Context context) {
		sp = context.getSharedPreferences(DefaultConfig.DefaultConfigFileName,
				Context.MODE_PRIVATE);
		return new HttpUtility();
	}

	/**
	 * 获取连接对象
	 * 
	 * @param requestURL
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public HttpURLConnection getHttpURLConnection(String requestURL)
			throws MalformedURLException, IOException, URISyntaxException {
		URL url = new URL(requestURL);
		httpConn = (HttpURLConnection) url.openConnection();
		// 设置超时
		httpConn.setConnectTimeout(WebServerConfig.timeout);
		// 设置cookie
		addCookie(httpConn, sp.getString(WebServerConfig.cookieName, ""));
		return httpConn;
	}

	/**
	 * Makes an HTTP request using GET method to the specified URL.
	 * 
	 * @param requestURL
	 *            the URL of the remote server
	 * @return An HttpURLConnection object
	 * @throws IOException
	 *             thrown if any I/O error occurred
	 */
	public HttpURLConnection sendGetRequest(HttpURLConnection httpConn)
			throws IOException {
		httpConn.setUseCaches(false);

		httpConn.setDoInput(true); // true if we want to read server's response
		httpConn.setDoOutput(false); // false indicates this is a GET request

		return httpConn;
	}

	/**
	 * Makes an HTTP request using POST method to the specified URL.
	 * 
	 * @param requestURL
	 *            the URL of the remote server
	 * @param params
	 *            A map containing POST data in form of key-value pairs
	 * @return An HttpURLConnection object
	 * @throws IOException
	 *             thrown if any I/O error occurred
	 */
	public void sendPostRequest(HttpURLConnection httpConn,
			Map<String, String> params) throws IOException {
		httpConn.setUseCaches(false);

		httpConn.setDoInput(true); // true indicates the server returns response

		StringBuffer requestParams = new StringBuffer();

		if (params != null && params.size() > 0) {

			httpConn.setDoOutput(true); // true indicates POST request

			// creates the params string, encode them using URLEncoder
			Iterator<String> paramIterator = params.keySet().iterator();
			while (paramIterator.hasNext()) {
				String key = paramIterator.next();
				String value = params.get(key);
				requestParams.append(URLEncoder.encode(key, "UTF-8"));
				requestParams.append("=").append(
						URLEncoder.encode(value, "UTF-8"));
				requestParams.append("&");
			}

			// sends POST data
			OutputStreamWriter writer = new OutputStreamWriter(
					httpConn.getOutputStream());
			writer.write(requestParams.toString());
			writer.flush();
		}
	}

	/**
	 * Returns only one line from the server's response. This method should be
	 * used if the server returns only a single line of String.
	 * 
	 * @return a String of the server's response
	 * @throws IOException
	 *             thrown if any I/O error occurred
	 */
	public String readSingleLineRespone(HttpURLConnection httpConn)
			throws IOException {
		InputStream inputStream = null;
		if (httpConn != null) {
			inputStream = httpConn.getInputStream();
		} else {
			throw new IOException("Connection is not established.");
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputStream));

		String response = reader.readLine();
		reader.close();

		return response;
	}

	/**
	 * Returns an array of lines from the server's response. This method should
	 * be used if the server returns multiple lines of String.
	 * 
	 * @return an array of Strings of the server's response
	 * @throws IOException
	 *             thrown if any I/O error occurred
	 */
	public String[] readMultipleLinesRespone(HttpURLConnection httpConn)
			throws IOException {
		InputStream inputStream = null;
		if (httpConn != null) {
			inputStream = httpConn.getInputStream();
		} else {
			throw new IOException("Connection is not established.");
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputStream));
		List<String> response = new ArrayList<String>();

		String line = "";
		while ((line = reader.readLine()) != null) {
			response.add(line);
		}
		reader.close();

		return (String[]) response.toArray(new String[0]);
	}

	/**
	 * Closes the connection if opened
	 */
	public void disconnect(HttpURLConnection httpConn) {
		if (httpConn != null) {
			httpConn.disconnect();
		}
	}

	/**
	 * Cookie
	 * 
	 * @param connection
	 * @throws URISyntaxException
	 */
	public void addCookie(URLConnection connection, String CookieString)
			throws URISyntaxException {
		if (TextUtils.isEmpty(CookieString)) {
			connection.setRequestProperty("Cookie", CookieString);
		}
	}

	/**
	 * Cookie
	 * 
	 * @param conn
	 * @return
	 * @throws URISyntaxException
	 */
	public String getCookieString(URLConnection conn) throws URISyntaxException {
		StringBuffer cookies = null;
		if (conn.getHeaderFields().containsKey(WebServerConfig.cookieName)) {
			cookies = new StringBuffer();
			List<String> list = conn.getHeaderFields().get(
					WebServerConfig.cookieName);
			for (String cookie : list) {
				cookies.append(cookie);
			}
		}
		return cookies.toString();
	}
}
