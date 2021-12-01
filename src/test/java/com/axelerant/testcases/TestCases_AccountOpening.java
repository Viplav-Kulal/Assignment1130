package com.axelerant.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageFactory.BaseMethods;
import utils.logger.Log;

public class TestCases_AccountOpening extends BaseMethods {
//	Verification of opening two new accounts for both account types - CHECKING and SAVINGS.
//	Help - Click the “Open New Account” link in the “Account Services” section

	@Test(priority = 0)
	public void tc_login() {
		login(user, pswd);
		if (isVisible(getaccountOverview(), 5)) {
			Log.info(user + " logged in successfully");
			Assert.assertTrue(true);
		} else {
			Log.info("Login failed");
			Assert.assertTrue(false);
		}

	}

	@Test(priority = 1)
	public void tc_CheckingAccountOpening() {
		Log.info("Opening new Checking account");
		click(getOpenNewAccountLink());
		selectFromDropdown(getAccountTypeDropdown(), "CHECKING");
		waitFor(2);
		click(getSubmitButton());
		if (isVisible(getAccountOpenHeading(), 10)) {
			newCheckingAccountID = getNewAccountId().getText();
			Log.info("Checking account open successfully. Account ID is " + newCheckingAccountID);
			Assert.assertTrue(true);
		} else {
			Log.info("Failed to open account");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 2)
	public void tc_VerifyCheckingsAccountDetails() {
		Log.info("Verifying Checking account details page");
		click(getNewAccountId());
		if (isVisible(getAccountID())) {
			waitFor(2); // Because the balance and account number loads after one second
			checkAccountID = getContent(getAccountID());
			CheckAccoutBalance = getContent(getAccountBalance());
			Log.info("Checking Account number is : " + checkAccountID);
			Log.info("Account type is : " + getContent(getAccountType()));
			Log.info("Account Balance is : " + CheckAccoutBalance);
			Assert.assertEquals(checkAccountID, newCheckingAccountID, "Account ID is correct");
			Assert.assertEquals(CheckAccoutBalance, "$100.00", "Account balance is correct");
			Assert.assertTrue(true);
		} else {
			Log.info("Failed to load Checking account details screen");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 3)
	public void tc_SavingsAccountOpening() {
		Log.info("Opening new Savings account");
		click(getOpenNewAccountLink());
		selectFromDropdown(getAccountTypeDropdown(), "SAVINGS");
		waitFor(2);
		click(getSubmitButton());
		if (isVisible(getAccountOpenHeading(), 10)) {
			newSavingsAccountID = getNewAccountId().getText();
			Log.info("Savings account open successfully. Account ID is " + newSavingsAccountID);
			Assert.assertTrue(true);
		} else {
			Log.info("Failed to open Savings account");
			Assert.assertTrue(false);
		}

	}

	@Test(priority = 4)
	public void tc_VerifySavingsAccountDetails() {
		Log.info("Verifying Savings account details page");
		click(getNewAccountId());
		if (isVisible(getAccountID(), 5)) {
			waitFor(2);
			Log.info("Savings Account number is : " + getContent(getAccountID()));
			Log.info("Account type is : " + getContent(getAccountType()));
			Log.info("Account Balance is : " + getContent(getAccountBalance()));
			SavingAccountBalance = getBalance().getText();
			Assert.assertEquals(newSavingsAccountID, getContent(getAccountID()), "Account ID is correct");
			Assert.assertEquals(SavingAccountBalance, "$100.00", "Account balance is correct");
			Assert.assertTrue(true);
		} else {
			Log.info("Failed to load Savings account details screen");
			Assert.assertTrue(false);
		}
	}

	/********************************************************************************************/

	@Test(priority = 5)
	public void tc_validateErrorMessages() {
		SoftAssert sa = new SoftAssert();
		click(getBillPayLink());
		if (isVisible(getBillPayHead(), 10)) {
			Log.info("Successfully navigated to Bill Payment services screen.");
			sa.assertTrue(true);
		} else {
			Log.info("Failed to load Bill payment services screen");
			Assert.assertTrue(false);
		}

		// Validate all error messages;
		Log.info("Validate fields on Bill pay form");
		click(getSubmitButton());
		waitFor(2);
		if (getAllErrors().equals(initiateErrorStrings())) {
			Log.info(getAllErrors().toString());
			Log.info("Error messages matching with expected copy");
			sa.assertTrue(true);
		} else {
			Log.info("Error message incorrect");
			sa.assertTrue(false);
		}

		// No other found validation for Payee name, adresss, city, state, zip and
		// phone number

		// check invalid Account number
		Log.info("Check error message for invalid account number");
		enterText(getPayeeAccountNumber(), randomString(5));
		click(getSubmitButton());
		waitFor(1);
		if (getInvalidAccountErrorMsg(2).contains(accountNoError)) {
			Log.info(getInvalidAccountErrorMsg(2));
			Log.info("Correct error message dissplayed for invalid account number");
			sa.assertTrue(true);
		} else {
			Log.info(getInvalidAccountErrorMsg(2));
			Log.info("Inorrect error message dissplayed for invalid account number");
			sa.assertTrue(false);
		}
		enterText(getVerifyAccount(), randomString(5));
		click(getSubmitButton());
		waitFor(1);
		if (getInvalidVerifyAccountErrorMsg(2).contains(accountNoError)) {
			Log.info(getInvalidVerifyAccountErrorMsg(2));
			Log.info("Correct error message displayed for mismatch in account number");
			sa.assertTrue(true);
		} else {
			Log.info(getInvalidVerifyAccountErrorMsg(2));
			Log.info("Inorrect error message displayed for mismatch in account number");

			sa.assertTrue(false);
		}

		// Check mismatch in account number validation

		Log.info("Check Error message for incorrect account number in Account# and Verify Account# field");
		enterText(getPayeeAccountNumber(), randomNumber(5));
		enterText(getVerifyAccount(), randomNumber(5));
		click(getSubmitButton());
		waitFor(1);
		if (getInvalidVerifyAccountErrorMsg(3).contains(verifyAccountNoError)) {
			Log.info(getInvalidVerifyAccountErrorMsg(3));
			Log.info("Correct error message displayed for mismatch in account number");
			sa.assertTrue(true);
		} else {
			Log.info(getInvalidVerifyAccountErrorMsg(3));
			Log.info("Inorrect error message displayed for mismatch in account number");

			sa.assertTrue(false);
		}

		// Invalid amount

		Log.info("Validate Ammount field");
		enterText(getPayAmount(), randomString(5));
		click(getSubmitButton());
		waitFor(1);
		if (getInvalidAmmountErrorMsg(2).equalsIgnoreCase(amoutError)) {
			Log.info(getInvalidAmmountErrorMsg(2));
			Log.info("Correct error message dissplayed for invalid account number");
			sa.assertTrue(true);
		} else {
			Log.info("Incorrect error message dissplayed for invalid account number");
			sa.assertTrue(false);
		}

		sa.assertAll();
	}

	@Test(priority = 6)
	public void tc_transferFunds() {
		Log.info("Transfering funds from savings to checking account");
		// Form fill up for transfer from
		// savings to checkings account

		fillUpBillPayForm(newSavingsAccountID, newCheckingAccountID, "200");
		click(getSubmitButton());
		if (isVisible(getBillPaymentCompleteHeadline(), 10)) {
			Log.info("Bill payment successfull");
			Assert.assertTrue(true);
		} else {
			Log.info("Bill payment failed");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 7)
	public void tc_checkDeductionFromSavings() {
		SoftAssert sa = new SoftAssert();
		// Check the amount deduction
		Log.info("Checking the right amout is deducted from Savings balance");
		getUrl(accountActivityURL + newSavingsAccountID);
		waitFor(2);
		if (isVisible(gettransactionTable(), 5)) {
			String lastTransaction = getLastTransactionFromTable();
			if (lastTransaction.equalsIgnoreCase("$200.00")) {
				Log.info("Last transaction is : " + lastTransaction);
				Log.info("Transaction amount deducted is correct");
				sa.assertTrue(true);
			} else {
				Log.info("Last transaction is : " + lastTransaction);
				Log.info("Incorrect transaction amount deducted");
				sa.assertTrue(false);
			}
			// Now substract the current available balance with previous opening balance. It
			// should be equal to the amount displayed in the table for last transaction i.e
			// $200

			String newBalance = getBalance().getText();
			if (Float.parseFloat(cleanCurrency(SavingAccountBalance))
					- Float.parseFloat(cleanCurrency(newBalance)) == Float.parseFloat(cleanCurrency(lastTransaction))) {
				Log.info("Previous balance was " + SavingAccountBalance);
				Log.info("Previous balance is " + newBalance);
				Log.info("Savings account balance is deducted by $200.00");
				sa.assertTrue(true);
			} else {
				Log.info("Deduction in savings account balance is incorrect.");
				sa.assertTrue(false);
			}
			sa.assertAll();
		}
	}

	@Test(priority = 8)
	public void tc_checkAdditionToChecking() {
		Log.info("Checking the right amout is Added to Checking balance");
		getUrl(accountActivityURL + newCheckingAccountID);
		waitFor(2);
		if (isVisible(gettransactionTable(), 5)) {
			String lastTransactionCheck = getLastTransactionFromTable();
			if (lastTransactionCheck.equalsIgnoreCase("$200.00")) {
				Log.info("Last transaction is : " + lastTransactionCheck);
				Log.info("Transaction amount added is correct");
				Assert.assertTrue(true);
			} else {
				Log.info("Last transaction is : " + lastTransactionCheck);
				Log.info("Transaction amount is not added");
				Assert.assertTrue(false);
			}
			// Now substract the current available balance with previous opening balance. It
			// should be equal to the amount displayed in the table for last transaction i.e
			// $200

			String newBalanceCheck = getBalance().getText();
			if (Float.parseFloat(cleanCurrency(newBalanceCheck))
					- Float.parseFloat(cleanCurrency(CheckAccoutBalance)) == Float
							.parseFloat(cleanCurrency(lastTransactionCheck))) {
				Log.info("Previous balance was " + CheckAccoutBalance);
				Log.info("Previous balance is " + newBalanceCheck);
				Log.info("Checking account balance is added by $200.00");
				Assert.assertTrue(true);
			} else {
				Log.info("Addition in checking account balance is incorrect.");
				Assert.assertTrue(false);
			}

		} else {
			Log.info("Unable to load Account details screen");
			Assert.assertTrue(false);
		}
	}
}
