package com.axelerant.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageFactory.BaseMethods;
import utils.logger.Log;

public class TestCase_AccountActivity extends BaseMethods {

	@Test(priority = 0)
	public void tc_verifyCheckingAccoutnActivityPage() {
		SoftAssert sa = new SoftAssert();
		Log.info("Verify Checking account created through Rest API");
		login(user, pswd);
		getUrl(accountActivityURL + newCheckingAccountID);
		waitFor(1);
		if (isVisible(getAccountDetailsHead(), 5)) {
			Log.info("Verifying Checking account activity details");
			String accountID = getContent(getAccountID());
			String accountBalance = cleanCurrency(getContent(getAccountBalance()));
			String accountType = getContent(getAccountType());

			if (accountID.equalsIgnoreCase(newCheckingAccountID)) {
				Log.info("Account ID is correct : " + accountID);
				sa.assertTrue(true);
			} else {
				Log.info("Account ID is not correct : " + accountID);
				sa.assertTrue(false);
			}

			if (accountType.equalsIgnoreCase("CHECKING")) {
				Log.info("Account Type is correct : " + accountType);
				sa.assertTrue(true);
			} else {
				Log.info("Account Type is not correct : " + accountType);
				sa.assertTrue(false);
			}

			if (accountBalance.contains(CheckAccoutBalance)) {
				Log.info("Account balance is correct : " + accountBalance);
				sa.assertTrue(true);
			} else {
				Log.info("Account balance is not correct : " + accountBalance);
				sa.assertTrue(false);
			}
		} else {
			Log.info("Could not open Checking account activity page");
			Assert.assertTrue(false);
		}
		sa.assertAll();

	}

	@Test(priority = 1)
	public void tc_verifySavingAccoutnActivityPage() {
		SoftAssert sa = new SoftAssert();
		Log.info("Verify Savings account created through Rest API");
		getUrl(accountActivityURL + newSavingsAccountID);
		waitFor(1);
		if (isVisible(getAccountDetailsHead(), 5)) {
			Log.info("Verifying Savings account activity details");
			String accountID = getContent(getAccountID());
			String accountBalance = cleanCurrency(getContent(getAccountBalance()));
			String accountType = getContent(getAccountType());

			if (accountID.equalsIgnoreCase(newSavingsAccountID)) {
				Log.info("Account ID is correct : " + accountID);
				sa.assertTrue(true);
			} else {
				Log.info("Account ID is not correct : " + accountID);
				sa.assertTrue(false);
			}

			if (accountType.equalsIgnoreCase("SAVINGS")) {
				Log.info("Account Type is correct : " + accountType);
				sa.assertTrue(true);
			} else {
				Log.info("Account Type is not correct : " + accountType);
				sa.assertTrue(false);
			}

			if (accountBalance.contains(SavingAccountBalance)) {
				Log.info("Account balance is correct : " + accountBalance);
				sa.assertTrue(true);
			} else {
				Log.info("Account balance is not correct : " + accountBalance);
				sa.assertTrue(false);
			}
		} else {
			Log.info("Could not open Saving account activity page");
			Assert.assertTrue(false);
		}
		sa.assertAll();

	}
}
