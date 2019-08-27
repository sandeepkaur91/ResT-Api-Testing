package com.qa.data;
//pojo simple java object we have created
//marshelling =convert this java object to json to send with post request
//we use a utility for this =Jackson APi
public class Users {
   String name;
   String job;
   String id;
   String createdAt;
   
   public Users() {
	   //this constructor is used for initialising userResobj object 
   }

   
   public Users(String name,String job) {
	   this.name=name;
	   this.job=job;
	   
	   
	   
   }
   //create getter and setter methods for name and job
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getJob() {
	return job;
}
public void setJob(String job) {
	this.job = job;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getCreatedAt() {
	return createdAt;
}
public void setCreatedAt(String createdAt) {
	this.createdAt = createdAt;
}
}
