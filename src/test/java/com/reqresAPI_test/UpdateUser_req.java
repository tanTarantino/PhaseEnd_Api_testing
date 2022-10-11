package com.reqresAPI_test;

import java.io.IOException;
import java.util.HashMap;

import static org.hamcrest.Matchers.is;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import log.BaseLoggerTest;

public class UpdateUser_req extends BaseLoggerTest {

	public static HashMap<String, String> usrInfo = new HashMap<String, String>();

	// get user id from CreateUSerTest.class
	private String id;

	@BeforeMethod
	public void putData() throws IOException {
		id = LoadUserID.getID();
		logger.info("ID Loeaded");
		usrInfo.put("name", "Micky");
		logger.info("Added added");
		usrInfo.put("email", "dummymail434543@gmai.com");
		logger.info("Added email");
		usrInfo.put("gender", "male");
		logger.info("Added gender");
		usrInfo.put("status", "inactive");
		logger.info("Added status");

		// set URI path
		RestAssured.baseURI = "https://gorest.co.in";
		RestAssured.basePath = "/public/v2/users/" + id;
	}

	@Test
	public void UpdateUser() {
		Response response = (Response) RestAssured
				.given()
					.header("Authorization"
							, "Bearer 16e65c5f941fbb7b80ebbe8553eef0a3e40671bb8fbe63c7fe6c7cf943e85e7d")
					.body(usrInfo)
						.contentType(ContentType.JSON)
					.when()
						.put()
					.then()
						.statusCode(200)
					.and()
						.body("name", is(usrInfo.get("name")))
						.log().all()
					// extract a response as JSON Type
					.contentType(ContentType.JSON).extract().response();
					
		logger.info("Resource Created & Response Captured");

		JsonPath response_jsonPath = response.jsonPath();
		logger.info("jsonPath of response Created");

		id = response_jsonPath.getString("id").toString();
		logger.info("ID Captured");

	}
}