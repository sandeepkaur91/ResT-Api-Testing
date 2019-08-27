
package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class RestClient  {
    
	//get method without headers
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();//this method is used to create connection
	    HttpGet httpget = new HttpGet(url);//this method will make the connection with the url
	    CloseableHttpResponse closeablehttp_response=httpclient.execute(httpget);//this will execute the operation eg clicking on send button in postman
	    return closeablehttp_response;
	}
	//get method with headers
	public CloseableHttpResponse get(String url,HashMap<String,String> header_map) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		for(Map.Entry<String, String> entry:header_map.entrySet()) {
		      httpget.addHeader(entry.getKey(),entry.getValue());//this for loop will add as many haeders u want
		}
		
		CloseableHttpResponse closeablehttp_response = httpclient.execute(httpget);
		return closeablehttp_response;
	}
	//Post method with Headers
	public CloseableHttpResponse post(String url,String entity_string,HashMap<String,String> header_map) throws ClientProtocolException, IOException {
	CloseableHttpClient httpclient = HttpClients.createDefault();
	HttpPost httppost = new HttpPost(url);//http post request
	httppost.setEntity(new StringEntity(entity_string));//for payload
	//for adding headers in url
	for(Map.Entry<String, String> entry:header_map.entrySet()) {
             httppost.addHeader(entry.getKey(),entry.getValue());
         }
	CloseableHttpResponse closeablehttp_response=httpclient.execute(httppost);
	return closeablehttp_response;
	}}
