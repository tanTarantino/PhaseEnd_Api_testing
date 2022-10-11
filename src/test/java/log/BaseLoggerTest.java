package log;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeSuite;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseLoggerTest {

	public static RequestSpecification specifiation;
	public static Response response;
	
	public Logger logger;
	
	@BeforeSuite
	public void setup() {
		logger = Logger.getLogger("LogDemo");
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
	}
	
}
