package com.qa.API.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.API.base.TestBase;
import com.qa.API.client.RestClient;
import com.qa.API.data.Users;

public class PostAPITest extends TestBase {

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
	public void postAPITest() {
		try {

			restClient = new RestClient();
			HashMap<String, String> headerMap = new HashMap<String, String>();
			headerMap.put("Content-Type", "application/json");
			/*
			 * headerMap.put("Content-Type", "application/json");
			 * headerMap.put("Content-Type", "application/json");
			 * headerMap.put("Content-Type", "application/json");
			 */

			// Jackson API to convert

			ObjectMapper mapper = new ObjectMapper();
			Users users = new Users("morpheus", "leader");  // Expected

			// Object to JSON File
			mapper.writeValue(new File(
					"F:\\WorkSpace\\TESTProject\\RestAPIAutomation\\src\\main\\java\\com\\qa\\API\\data\\users.json"),
					users);

			// Object to JSONString
			String userStringJSON = mapper.writeValueAsString(users);
			System.out.println(userStringJSON);

			// call post method

			httpResponse = restClient.post(URL, userStringJSON, headerMap);
			
			//1. Status Code
			
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			System.out.println("Status Code -- > "+statusCode);
			
			Assert.assertEquals(statusCode, 201);
			
			//2. JSON String
			
			String responseString =	EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
			
			JSONObject responseJSON = new JSONObject(responseString);
			System.out.println("Response from API : "+responseJSON);
			
			// JSON to Java Obj
			
			Users userRespObj = mapper.readValue(responseString, Users.class); // Actual 
			System.out.println(userRespObj);
			
			Assert.assertTrue(users.getName().equals(userRespObj.getName()));
			Assert.assertTrue(users.getJob().equals(userRespObj.getJob()));

			System.out.println("ID : "+ userRespObj.getId());
			
			System.out.println("Created At : "+ userRespObj.getCreatedAt());
			
			

		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
