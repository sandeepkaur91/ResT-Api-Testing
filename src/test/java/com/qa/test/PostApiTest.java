package com.qa.test;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class PostApiTest extends TestBase {
	TestBase tb;
	String service_url;//all these are global so that
	String api_url;//so that they can b used in all the methods
	String url;
	RestClient rc;
	CloseableHttpResponse closeablehttp_response;
  
	@BeforeMethod
	  public void Set_up() throws IOException {
		  tb=new TestBase();
		  service_url = tb.prop.getProperty("url");
		  api_url=tb.prop.getProperty("service_url");
		  url=service_url+api_url;
		    }
  @Test
  public void postApiTest() throws ClientProtocolException, IOException {
	  rc=new RestClient();
	  HashMap<String, String> header_map = new HashMap<String,String>();
	  header_map.put("Content-Type", "application/json");
	  //header_map.put("username", "123");
	  //header_map.put("password", "sandeep");
	  //using Jackson Api
	  ObjectMapper om=new ObjectMapper();
	  Users user=new Users("sandeep", "automation tester");//expected user object
	  //MArshelling=convert javaobject to json file
	  om.writeValue(new File("C:\\Users\\Sandeep\\eclipse-workspace\\Rest_Webservices\\src\\main\\java\\com\\qa\\data\\users.json"), user);
	  //convert object to json in string
	  String userjson_string = om.writeValueAsString(user);
	  System.out.println("json payload sent by us"+userjson_string);//this string is used in post method
	   
	  closeablehttp_response=rc.post(url, userjson_string, header_map);
	  //validating the Status code
	  int status_code=closeablehttp_response.getStatusLine().getStatusCode();
	  Assert.assertEquals(status_code, status_response_code_201);
	  //validating the Json string that we posted 
	 String response_string = EntityUtils.toString(closeablehttp_response.getEntity(), "UTF-8");
	 //convert the raw string into json object to match with users.json file
	 JSONObject json_response=new JSONObject(response_string);
	 System.out.println("json response from API is :"+json_response);
	 
	 //Unmarshelling=json to java object 
	 Users userResobj=om.readValue(response_string, Users.class);//actual user object //this object will use user class constructor with no parameter
	 System.out.println("userResobj is :"+userResobj);
	 Assert.assertTrue(user.getName().equals(userResobj.getName()));
	 Assert.assertTrue(user.getJob().equals(userResobj.getJob()));
	 System.out.println("id is :"+userResobj.getId());
	 System.out.println("created at :"+userResobj.getCreatedAt());
	 //return closeablehttp_response;
	  
	  }
  @AfterMethod
  public void afterMethod() {
  }

}
