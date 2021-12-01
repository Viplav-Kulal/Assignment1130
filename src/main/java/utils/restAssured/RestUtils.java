package utils.restAssured;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import com.axelerant.base.IBaseInterface;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pageFactory.BaseMethods;
import utils.logger.Log;

public class RestUtils extends BaseMethods {

	public static RequestSpecification req;

	public RequestSpecification requestSpecification() {
		if (req == null) {
			try {
				PrintStream log = new PrintStream(new FileOutputStream("APIlog.txt"));
				req = new RequestSpecBuilder().setBaseUri(IBaseInterface.BASE_URL)
						.addFilter(RequestLoggingFilter.logRequestTo(log))
						.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
				return req;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				Log.info("Something is wrong in requestSpecification or Printstream");
				return null;
			}
		} else
			return req;
	}

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		RestAssured.baseURI = IBaseInterface.BASE_URL;
		Response response = RestAssured.given().auth().basic("john", "demo").request(Method.GET, "/accounts/14454");
		System.out.println(response);
	}
}
