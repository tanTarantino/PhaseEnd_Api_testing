package com.reqresAPI_test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import log.BaseLoggerTest;

import static org.hamcrest.CoreMatchers.containsString;

public class CreateUser_req extends BaseLoggerTest {

	public static HashMap<String, String> usrInfo = new HashMap<String, String>();
	// store new user id in Json file for later use
	static String id;

	@BeforeMethod
	public void putData() {
		usrInfo.put("name", "John");
		logger.info("Added added");
		usrInfo.put("email", "dummymail434543@gmai.com");
		logger.info("Added email");
		usrInfo.put("gender", "male");
		logger.info("Added gender");
		usrInfo.put("status", "active");
		logger.info("Added status");

		// set URI path
		RestAssured.baseURI = "https://gorest.co.in";
		RestAssured.basePath = "/public/v2/users";
	}

	@Test
	public void CreateUser() throws IOException {
		Response response = (Response) RestAssured
				.given()
					.header("Authorization"
							, "Bearer 16e65c5f941fbb7b80ebbe8553eef0a3e40671bb8fbe63c7fe6c7cf943e85e7d")
					.body(usrInfo)
					.contentType(ContentType.JSON)
				.when()
					.post()
				.then()
					.log().all()
				.and()
					.statusCode(201)
					.body("name", containsString("John"))
						.contentType(ContentType.JSON).extract().response();

		logger.info("Resource Created & Response Captured");

		JsonPath response_jsonPath = response.jsonPath();
		logger.info("jsonPath of response Created");
		id = response_jsonPath.getString("id").toString();
		logger.info("ID captured");

		BufferedWriter bw = new BufferedWriter(new FileWriter("JsonOutput.json"));
		bw.write(id);
		bw.flush();
		bw.close();

	}

}
