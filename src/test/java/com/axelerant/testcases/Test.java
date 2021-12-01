package com.axelerant.testcases;

import com.axelerant.base.IBaseInterface;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Test {

	public static void main(String[] args) {

		getUser();
	}

	public static void getUser() {
		RestAssured.baseURI = IBaseInterface.BASE_URL;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/accounts/21225");
		System.out.println(response);
	}

}
