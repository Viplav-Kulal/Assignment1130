package com.axelerant.testcases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.axelerant.base.BaseClass;
import com.axelerant.base.IBaseInterface;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.logger.Log;
import utils.restAssured.Payload;
import utils.restAssured.RestUtils;

public class TestCase_Rest_AccountOpen extends RestUtils {
	public static RequestSpecification request;
	public static Response resp;
	public static JsonPath jp;
	public static String customerID = custID;
	public static String fromAccount = BaseClass.fromAccount;
	public static String newCheckAccountID;
	public static String newSaveAccountID;
	public static float checkBalance;
	public static float saveBalance;
	public static String accountType;

	@BeforeMethod
	public void setUp() {
		RestAssured.baseURI = IBaseInterface.BASE_URL;
		request = RestAssured.given().auth().basic(user, pswd).header("Accept", "application/json")
				.header("Content-Type", "application/json");
	}

	@Test(priority = 0)
	public void tc_createCheckingsAccount() {
		Log.info("Create checking account with API");
		request = request.queryParam("customerId", customerID).queryParam("newAccountType", "0")
				.queryParam("fromAccountId", fromAccount);
		resp = request.when().post("/createAccount");
		int statusCode = resp.getStatusCode();
		if (statusCode == 200) {
			jp = resp.jsonPath();
			Log.info(jp.prettify());
			newCheckAccountID = jp.getString("id");
			newCheckingAccountID = newCheckAccountID; // Adding to globle variable to access activity page from UI
			accountType = jp.getString("type");
			Log.info("Checking account successfully created with ID : " + newCheckAccountID);
			Assert.assertTrue(true);
		} else {
			Log.info(resp.getStatusCode() + resp.getStatusLine());
			Log.info("Failed to create checking account with API");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 1)
	public void tc_getCheckingAccountDetails() {
		Log.info("Verify Cheking account details with API");
		resp = request.request(Method.GET, ("/accounts/" + newCheckAccountID));
		jp = resp.jsonPath();
		String s = jp.getString("type");
		if (s.equalsIgnoreCase(accountType)) {
			checkBalance = jp.get("balance");
			Log.info("Checking account details returned correctly");
			Log.info(jp.prettify());
			Assert.assertTrue(true);
		} else {
			Log.info("Failed to get correct account");
		}

	}

	@Test(priority = 2)
	public void tc_createSavingssAccount() {
		Log.info("Create Saving account with API");
		request = request.queryParam("customerId", customerID).queryParam("newAccountType", "1")
				.queryParam("fromAccountId", fromAccount);
		resp = request.when().post("/createAccount");
		jp = resp.jsonPath();
		int statusCode = resp.getStatusCode();
		if (statusCode == 200) {
			Log.info(jp.prettify());
			newSaveAccountID = jp.getString("id");
			newSavingsAccountID = newSaveAccountID; // Adding to globle variable to access activity page from UI
			accountType = jp.getString("type");
			Log.info("Savings account successfully created with ID : " + newSaveAccountID);
			Log.info(jp.prettify());
			Assert.assertTrue(true);
		} else {
			Log.info(resp.getStatusCode() + resp.getStatusLine());
			Log.info("Failed to create saving account with API");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 3)
	public void tc_getSavingAccountDetails() {
		Log.info("Verify Saving account details with API");
		resp = request.request(Method.GET, ("/accounts/" + newSaveAccountID));
		String s = jp.getString("type");
		if (s.equalsIgnoreCase(accountType)) {
			saveBalance = Float.parseFloat(jp.getString("balance"));
			jp = resp.jsonPath();
			Log.info("Fetched account details correctly");
			Log.info(jp.prettify());
			Assert.assertTrue(true);
		} else {
			Log.info("Failed to get correct account");
		}
	}

	@Test(priority = 4)
	public void tc_billPayFromCheckingsToSavings() {
		Log.info("Bill Pay using restAPI");
		request = request.queryParam("accountId", newCheckAccountID).queryParam("amount", "200")
				.body(Payload.createBillPayload(newSaveAccountID, "RestApi", "2342793472", "SomeStreet Address", "Pune",
						"MH", "234234"));
		resp = request.when().post("/billpay");
		jp = resp.jsonPath();
		String s = jp.getString("amount");
		if (resp.getStatusCode() == 200) {
			Log.info("Bill payment of $" + s + " is successfull.");
			Log.info(jp.prettify());
			Assert.assertTrue(true);
		} else {
			Log.info(resp.getStatusCode() + resp.getStatusLine());
			Log.info("Bill Payment failed");
			Assert.assertTrue(false);
		}

		// check if amount is deducted correctly from checkings account
		Log.info("Verifying amount is deducted correctly from checkings account and balance is updated");
		jp = request.request(Method.GET, ("/accounts/" + newCheckAccountID)).jsonPath();
		Log.info(jp.prettify());
		float newbalance = jp.get("balance");
		if (checkBalance - newbalance == 200.00) {
			Log.info("Amount deducted from checkings account is correct");
			CheckAccoutBalance = Float.toString(newbalance); // assigning new value to global variable for visual
																// testing
			Assert.assertTrue(true);
		} else {
			Log.info("Incorrect amount is deducted from checking account");
			Assert.assertTrue(false);
		}

		// check from checkings account transactions to see $200 deducted
		Log.info("Verify the last transaction amount for Checkings account");
		jp = RestAssured.given().auth().basic("john", "demo")
				.request(Method.GET, ("/accounts/" + newCheckAccountID + "/transactions")).jsonPath();
		List<Object> lastAmount = jp.getList("amount");
		List<Object> lastTransType = jp.getList("type");
		String la = lastAmount.get(lastAmount.size() - 1).toString();
		String ltt = lastTransType.get(lastTransType.size() - 1).toString();
		if (la.contains("200") && ltt.contains("Debit")) {
			Log.info("Last transaction shows 200 debited from checking account : " + ltt + " : " + la);
			Assert.assertTrue(true);
		} else {
			Log.info("Last transaction shows incorrect entry : " + ltt + " : " + la);
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 5)
	public void tc_verifySavingsAccountBalanceUpdated() {
		SoftAssert sa = new SoftAssert();
		jp = request.request(Method.GET, ("/accounts/" + newSaveAccountID)).jsonPath();
		Log.info("Verify Saving account balance is updated");
		float newbalance = Float.parseFloat(jp.getString("balance"));
		if (newbalance - saveBalance == 200.00) {
			SavingAccountBalance = Float.toString(newbalance);
			Log.info(jp.prettify());
			Log.info("Amount added to Savings account is correct");
			sa.assertTrue(true);
		} else {
			Log.info(jp.prettify());
			SavingAccountBalance = Float.toString(newbalance);
			Log.info("Incorrect amount is added to savings account");
			sa.assertTrue(false);
		}

		// check from Savings account transactions to see $200 deducted
		Log.info("Verify the last transaction amount for Checkings account");
		jp = request.request(Method.GET, ("/accounts/" + newSaveAccountID + "/transactions")).jsonPath();
		List<Float> lastAmount = jp.getList("amount");
		List<String> lastTransType = jp.getList("type");
		float la = lastAmount.get(lastAmount.size() - 1);
		String ltt = lastTransType.get(lastTransType.size() - 1);
		if (ltt.contains("Credit") && la == 200.00) {
			Log.info("Last transaction shows 200 credited to Savings account : " + ltt + " : " + la);
			sa.assertTrue(true);
		} else {
			Log.info("Last transaction does not show $200 added. Last transaction is : " + ltt + " : " + la);
			sa.assertTrue(false);
		}
		sa.assertAll();

	}

}
