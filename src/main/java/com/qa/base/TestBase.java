package com.qa.base;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;


public class TestBase {
	 public Properties prop;
	 public int status_response_code_200=200;
	 public int status_response_code_201=201;
	 public int status_response_code_300=300;
	 public int status_response_code_400=400;
	 public int status_response_code_404=404;
       public TestBase() {
    	   try {
    	    prop = new Properties();
    	    FileInputStream ip = new FileInputStream("C:\\Users\\Sandeep\\eclipse-workspace\\Rest_Webservices\\src\\main\\java\\com\\qa\\config\\config.properties");
    	    prop.load(ip);
    	   }catch(IOException e) {
    		   e.printStackTrace();
    	   }
    
       }}

