package com.reqresAPI_test;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import log.BaseLoggerTest;

public class GetUser_req extends BaseLoggerTest{

	private String id;
	
	@BeforeMethod
	public void invokePath() throws IOException {
		// load id 
		id = LoadUserID.getID();
		RestAssured.baseURI = "https://gorest.co.in";
		RestAssured.basePath = "/public/v2/users/" + id;
		logger.info("Request URI & Path Set.");
	}
	
	@Test
	public void getUser() {
		RestAssured
			.given()
				.header("authorization"
						,"Bearer 16e65c5f941fbb7b80ebbe8553eef0a3e40671bb8fbe63c7fe6c7cf943e85e7d")
			.when()
				.get()
			.then()
			.log().all()
				.statusCode(200);
		
		logger.info("Get request Executed");
	}
}