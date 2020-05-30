package com.qa.API.tests;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.API.base.TestBase;
import com.qa.API.client.RestClient;

public class GetAPITest extends TestBase {

	TestBase testBase;
	RestClient restClient;
	String AppURL;
	String ServiceURL;
	String URL;
	CloseableHttpResponse httpResponse;

	@BeforeMethod
	public void setUp() {

		testBase = new TestBase();

		AppURL = prop.getProperty("AppURL");
		ServiceURL = prop.getProperty("ServiceURL");

		URL = AppURL + ServiceURL;

	}

	@Test
	public void getAPITestWithoutHeader() {

		try {

			restClient = new RestClient();
			httpResponse = restClient.get(URL);

			// a. Status Code
			int statuscode = httpResponse.getStatusLine().getStatusCode(); // get the status
			
		//	StatusLine statuscode = httpResponse.getStatusLine();  //HTTP/1.1 200 OK
			
			System.out.println("Status Code : " + statuscode);
			
			Assert.assertEquals(statuscode, 200 , "Status Code is not 200. : " +statuscode );

			// b. JSON String
			String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8"); // get the response string

		//	JSONObject ResponseJSON = new JSONObject(responseString); // get the response json
			System.out.println("JSON from API : " + responseString);

			// c. All Headers
			Header[] HeaderArrays = httpResponse.getAllHeaders(); // returns a array so storing in Array
			Map<String, String> allHeader = new HashMap<String, String>();
			for (Header header : HeaderArrays) {
				allHeader.put(header.getName(), header.getValue());
			}

			System.out.println("All the Headers --> " + allHeader);

		}

		catch (Exception e) {
			System.out.println(e);
		}

	}
	
	
	@Test
	public void getAPITestWithHeader() {

		try {

			restClient = new RestClient();
			
			HashMap<String,String> headerMap = new HashMap<String, String>();
			headerMap.put("Content-Type", "application/json");
			/*headerMap.put("Content-Type", "application/json");
			headerMap.put("Content-Type", "application/json");
			headerMap.put("Content-Type", "application/json");*/
			
			httpResponse = restClient.get(URL,headerMap);

			// a. Status Code
			int statuscode = httpResponse.getStatusLine().getStatusCode(); // get the status
			System.out.println("Status Code : " + statuscode);
			
			Assert.assertEquals(statuscode, 200 , "Status Code is not 200. : " +statuscode );

			// b. JSON String
			String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8"); // get the response string

			JSONObject ResponseJSON = new JSONObject(responseString); // get the response json
			System.out.println("JSON from API : " + ResponseJSON);

			// c. All Headers
			Header[] HeaderArrays = httpResponse.getAllHeaders(); // returns a array so storing in Array
			Map<String, String> allHeader = new HashMap<String, String>();
			for (Header header : HeaderArrays) {
				
				System.out.println("Key : " + header.getName() +" , "+"Value : " + header.getValue()  );
				allHeader.put(header.getName(), header.getValue());
				

			}

			System.out.println("All the Headers --> " + allHeader);

		}

		catch (Exception e) {
			System.out.println(e);
		}

	}

}
