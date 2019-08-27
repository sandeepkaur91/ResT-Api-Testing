package com.qa.test;

import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class GetApiTest extends TestBase {
	
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
  public void getApiTest_withoutheaders() throws ClientProtocolException, IOException {
	  rc=new RestClient();
	  closeablehttp_response=rc.get(url);
	  
	  //status codes
	  int status_code=closeablehttp_response.getStatusLine().getStatusCode(); 
	    System.out.println("status code is: "+status_code);
	    Assert.assertEquals(status_code, status_response_code_200,"status is not 200");
	    String string_response=EntityUtils.toString(closeablehttp_response.getEntity(),"UTF-8");//json object response will be converted into String
	    //but we want the Json object so here we will use json dependency
	    
	    //json object
	    JSONObject json_object=new JSONObject(string_response);
	    System.out.println("json response is: "+json_object);
	    String perpage_value=TestUtil.getValueByJPath(json_object, "/per_page");
	    System.out.println("value of per page is :"+perpage_value);
	    Assert.assertEquals(Integer.parseInt(perpage_value), 3,"values of per_page not match");
	    
	    String total=TestUtil.getValueByJPath(json_object, "/total");
	    System.out.println("value of total is: "+total);
	    Assert.assertEquals(Integer.parseInt(total), 12,"value of total did not match");
	    
	    //fetching movie titles from json array
	    String id = TestUtil.getValueByJPath(json_object, "/data[1]/id");
	    String email=TestUtil.getValueByJPath(json_object, "/data[1]/email");
	    String first_name=TestUtil.getValueByJPath(json_object, "/data[1]/first_name");
	    String last_name=TestUtil.getValueByJPath(json_object, "/data[1]/last_name");
	    String avatar=TestUtil.getValueByJPath(json_object, "/data[1]/avatar");
	    
	    System.out.println("movies are :"+ id+" "+email+" "+first_name+" "+last_name+" "+avatar);
	    //headers
	    Header[] header_array = closeablehttp_response.getAllHeaders();
	    HashMap<String, String> all_headers = new HashMap<String,String>();
	    for(Header header:header_array) {
	    	all_headers.put(header.getName(), header.getValue());
	    	
	    }
	    System.out.println("all headers are : "+all_headers);
	    
	
  }
  
  @Test
  public void getApiTest_withheaders() throws ClientProtocolException, IOException {
	  rc=new RestClient();
	  HashMap<String, String> header_map = new HashMap<String,String>();
	  header_map.put("username", "123");
	  header_map.put("password", "sandeep");//you can add multiple headers
	  closeablehttp_response=rc.get(url,header_map);
	  
	  //status codes
	  int status_code=closeablehttp_response.getStatusLine().getStatusCode(); 
	    System.out.println("status code is: "+status_code);
	    Assert.assertEquals(status_code, status_response_code_200,"status is not 200");
	    String string_response=EntityUtils.toString(closeablehttp_response.getEntity(),"UTF-8");//json object response will be converted into String
	    //but we want the Json object so here we will use json dependency
	    
	    //json object
	    JSONObject json_object=new JSONObject(string_response);
	    System.out.println("json response is: "+json_object);
	    String perpage_value=TestUtil.getValueByJPath(json_object, "/per_page");
	    System.out.println("value of per page is :"+perpage_value);
	    Assert.assertEquals(Integer.parseInt(perpage_value), 3,"values of per_page not match");
	    
	    String total=TestUtil.getValueByJPath(json_object, "/total");
	    System.out.println("value of total is: "+total);
	    Assert.assertEquals(Integer.parseInt(total), 12,"value of total did not match");
	    
	    //fetching movie titles from json array
	    String id = TestUtil.getValueByJPath(json_object, "/data[0]/id");
	    String email=TestUtil.getValueByJPath(json_object, "/data[0]/email");
	    String first_name=TestUtil.getValueByJPath(json_object, "/data[0]/first_name");
	    String last_name=TestUtil.getValueByJPath(json_object, "/data[0]/last_name");
	    String avatar=TestUtil.getValueByJPath(json_object, "/data[0]/avatar");
	    System.out.println("movies are :"+ id+" "+email+" "+first_name+" "+last_name+" "+avatar);
	    //headers
	    Header[] header_array = closeablehttp_response.getAllHeaders();
	    HashMap<String, String> all_headers = new HashMap<String,String>();
	    for(Header header:header_array) {
	    	all_headers.put(header.getName(), header.getValue());
	    	
	    }
	    System.out.println("all headers are : "+all_headers);
	    
	
  }
  @AfterMethod
  public void afterMethod() {
  }

}
