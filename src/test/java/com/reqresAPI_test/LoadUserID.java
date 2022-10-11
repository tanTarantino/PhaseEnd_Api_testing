package com.reqresAPI_test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoadUserID {

	// get user id from Json file
	public static String getID() throws IOException {
		String id;
		
		BufferedReader in = new BufferedReader(new FileReader("JsonOutput.json"));
		id = in.readLine().toString();
		in.close();
		return id;
	}
}
