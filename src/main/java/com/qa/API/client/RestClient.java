package com.qa.API.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	// 1. GET Method without Headers
	CloseableHttpResponse httpResponse;

	public CloseableHttpResponse get(String url) {

		try {

			CloseableHttpClient httpClients = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(url); // http get request
			httpResponse = httpClients.execute(httpget); // hit the get URL

		}

		catch (Exception e) {
			System.out.println(e);
		}
		return httpResponse;

	}

	// 2. GET Method with Headers
	public CloseableHttpResponse get(String url, HashMap<String, String> requestHeader) {

		try {

			CloseableHttpClient httpClients = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(url); // http get request

			for (Map.Entry<String, String> entry : requestHeader.entrySet()) {
				httpget.addHeader(entry.getKey(), entry.getValue());
			}

			httpResponse = httpClients.execute(httpget); // hit the get URL

		}

		catch (Exception e) {
			System.out.println(e);
		}
		return httpResponse;

	}

	// 3. POST Method

	public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> HeaderMap)

	{
		try {
			CloseableHttpClient httpClients = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url); // for post request

			httpPost.setEntity(new StringEntity(entityString)); // for payload
			
			for (Map.Entry<String, String> entry : HeaderMap.entrySet()) {
				httpPost.addHeader(entry.getKey(), entry.getValue());
			}
			
			httpResponse = httpClients.execute(httpPost);
		}

		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return httpResponse;

	}

}
