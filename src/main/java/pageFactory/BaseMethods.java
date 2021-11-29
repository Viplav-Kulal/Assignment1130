package pageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.axelerant.base.BaseClass;

import utils.logger.Log;

public class BaseMethods extends BaseClass {

	public static String newCheckingAccountID;
	public static String newSavingsAccountID;
	public static String checkAccountID;
	public static String CheckAccoutBalance;
	public static String SavingAccountBalance;
	public static String currentBalance;
	public static float availableBalnce;
	public static float updatedBalance;
	protected static String accountNoError = "Please enter a valid number.";
	protected static String verifyAccountNoError = "The account numbers do not match.";
	protected static String amoutError = "Please enter a valid amount.";

	String userq1 = user;

	@FindBy(xpath = "//a[contains(string(),'Bill Pay')]")
	public WebElement billPaylink;

	@FindBy(xpath = "//h1[contains(string(),'Bill Payment Service')]")
	public WebElement billPayHeadline;

	@FindBy(xpath = "//h1[contains(string(),'Bill Payment Complete')]")
	public WebElement billPayComplete;

	@FindBy(css = "input.button")
	public WebElement subMitButton;

	@FindBy(xpath = "//input[@name='payee.name']")
	public WebElement payeeName;

	@FindBy(xpath = "//input[@name='payee.address.street']")
	public WebElement payeeAdress;

	@FindBy(xpath = "//input[@name='payee.address.city']")
	public WebElement payeeCity;

	@FindBy(xpath = "//input[@name='payee.address.state']")
	public WebElement payeeState;

	@FindBy(xpath = "//input[@name='payee.address.zipCode']")
	public WebElement payeeZip;

	@FindBy(xpath = "//input[@name='payee.phoneNumber']")
	public WebElement payeePhone;

	@FindBy(xpath = "//input[@name='payee.accountNumber']")
	public WebElement payeeAccountNumber;

	@FindBy(xpath = "//input[@name='verifyAccount']")
	public WebElement verifyAccount;

	@FindBy(xpath = "//input[@name='amount']")
	public WebElement payAmount;

	@FindAll({ @FindBy(xpath = "//select[@id='fromAccountId']"), @FindBy(xpath = "//select[@name='fromAccountId']") })
	public WebElement fromAccountId;

	@FindBy(css = "input[name=username]")
	public WebElement unm;

	@FindBy(css = "input[name=password]")
	public WebElement pwd;

	@FindBy(css = "input.button")
	public WebElement loginButton;

	@FindBy(xpath = "//p[contains(string(),'John Smith')]")
	public WebElement accountName;

	@FindBy(xpath = "//a[contains(string(),'Open New Account')]")
	public WebElement openAccount;

	@FindBy(id = "type")
	public WebElement accounttypeDrop;

	@FindBy(xpath = "//h1[contains(string(),'Account Opened!')]")
	public WebElement accountOpened;

	@FindBy(id = "newAccountId")
	public WebElement newAccountId;

	@FindBy(id = "accountId")
	public WebElement accountId;

	@FindBy(xpath = "//h1[contains(string(),'Account Details')]")
	public WebElement accountDetailsHead;

	@FindBy(xpath = "//h1[contains(string(),'Accounts Overview')]")
	public WebElement accountOverview;

	@FindBy(id = "accountType")
	public WebElement accountType;

	@FindBy(xpath = "//table[@id='transactionTable']")
	public WebElement transactionTable;

	@FindBy(xpath = "//td[@id='balance']")
	public static WebElement balance;

	@FindBy(xpath = "//td[@id='availableBalance']")
	public static WebElement availableBalance;

	public WebElement getaccountOverview() {
		return accountOverview;
	}

	public static WebElement getBalance() {
		return balance;
	}

	public static WebElement getavailableBalance() {
		return availableBalance;
	}

	public WebElement getAccountDetailsHead() {
		return accountDetailsHead;
	}

	public WebElement gettransactionTable() {
		return transactionTable;
	}

	public WebElement getAccountBalance() {
		return balance;
	}

	public WebElement getAccountType() {
		return accountType;
	}

	public WebElement getAccountID() {
		return accountId;
	}

	public WebElement getNewAccountId() {
		return newAccountId;
	}

	public WebElement getAccountOpenHeading() {
		return accountOpened;
	}

	public WebElement getAccountTypeDropdown() {
		return accounttypeDrop;
	}

	public WebElement getFromAccount() {
		return fromAccountId;
	}

	public WebElement getOpenNewAccountLink() {
		return openAccount;
	}

	public WebElement getUserNameField() {
		return unm;
	}

	public WebElement getPasswordField() {
		return pwd;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	public WebElement GetAccount() {
		return accountName;
	}

	public WebElement getFromAccountIDDropdown() {
		return fromAccountId;
	}

	public WebElement getBillPaymentCompleteHeadline() {
		return billPayComplete;
	}

	public WebElement getPayeeName() {
		return payeeName;
	}

	public WebElement getPayeeAdress() {
		return payeeAdress;
	}

	public WebElement getPayeeCity() {
		return payeeCity;
	}

	public WebElement getPayeeState() {
		return payeeState;
	}

	public WebElement getPayeeZip() {
		return payeeZip;
	}

	public WebElement getPayeePhone() {
		return payeePhone;
	}

	public WebElement getPayeeAccountNumber() {
		return payeeAccountNumber;
	}

	public WebElement getPayAmount() {
		return payAmount;
	}

	public WebElement getSubmitButton() {
		return subMitButton;
	}

	public WebElement getBillPayHead() {
		return billPayHeadline;
	}

	public WebElement getVerifyAccount() {
		return verifyAccount;
	}

	public WebElement getBillPayLink() {
		return billPaylink;
	}

	public ArrayList<String> getAllErrors() {
		Log.info("Fetching all error strings from the website");
		ArrayList<String> errorMessages = new ArrayList<String>();
		try {
			String s = null;
			for (int i = 1; i <= 6; i++) {
				s = driver
						.findElement(
								By.xpath("//*[@id='rightPanel']/div/div[1]/form/table/tbody/tr[" + i + "]/td[3]/span"))
						.getText();
				s.trim();
				errorMessages.add(s);
			}
			for (int i = 8; i <= 9; i++) {
				s = driver
						.findElement(
								By.xpath("//*[@id='rightPanel']/div/div[1]/form/table/tbody/tr[" + i + "]/td[3]/span"))
						.getText();
				s.trim();
				errorMessages.add(s);
			}
			s = driver.findElement(By.xpath("//*[@id='rightPanel']/div/div[1]/form/table/tbody/tr[11]/td[3]/span"))
					.getText();
			s.trim();
			errorMessages.add(s);
			return errorMessages;
		} catch (Exception e) {
			Log.info("Failed to get all error messages");
			return null;
		}
	}

	public ArrayList<String> initiateErrorStrings() {
		ArrayList<String> messages = new ArrayList<String>();
		try {
			messages.add("Payee name is required.");
			messages.add("Address is required.");
			messages.add("City is required.");
			messages.add("State is required.");
			messages.add("Zip Code is required.");
			messages.add("Phone number is required.");
			messages.add("Account number is required.");
			messages.add("Account number is required.");
			messages.add("The amount cannot be empty.");
			return messages;
		} catch (Exception e) {
			Log.info("Failed to get all error messages");
			return null;
		}
	}

	public String getInvalidAccountErrorMsg(int i) {
		try {
			return driver
					.findElement(
							By.xpath("//*[@id='rightPanel']/div/div[1]/form/table/tbody/tr[8]/td[3]/span[" + i + "]"))
					.getText();
		} catch (Exception e) {
			return null;
		}
	}

	public String getInvalidVerifyAccountErrorMsg(int i) {
		try {
			return driver
					.findElement(
							By.xpath("//*[@id='rightPanel']/div/div[1]/form/table/tbody/tr[9]/td[3]/span[" + i + "]"))
					.getText();
		} catch (Exception e) {
			return null;
		}
	}

	public String getInvalidAmmountErrorMsg(int i) {
		try {
			return driver
					.findElement(By
							.xpath("//*[@id=\"rightPanel\"]/div/div[1]/form/table/tbody/tr[11]/td[3]/span[" + i + "]"))
					.getText();
		} catch (Exception e) {
			return null;
		}
	}

	public void fillUpBillPayForm(String fromAccNo, String toAccNo, String amount) {
		enterText(getPayeeName(), randomString(10));
		enterText(getPayeeAdress(), randomString(20));
		enterText(getPayeeCity(), randomString(5));
		enterText(getPayeeState(), randomString(10));
		enterText(getPayeeZip(), randomAlphNumeric(6));
		enterText(getPayeePhone(), randomNumber(10));
		enterText(getPayeeAccountNumber(), toAccNo);
		enterText(getVerifyAccount(), toAccNo);
		enterText(getPayAmount(), amount);
		selectFromDropdown(getFromAccountIDDropdown(), fromAccNo);
	}

	public void login(String unm, String pwd) {
		Log.info("Logging into " + unm + "'s account");
		enterText(getUserNameField(), unm);
		enterText(getPasswordField(), pwd);
		click(getLoginButton());
	}

	public void enterText(WebElement ele, String text) {
		if (isVisibleWeb(ele)) {
			try {
				ele.clear();
				Log.info("Entering text " + text + " in " + ele.getText() + " field");
				ele.sendKeys(text);
			} catch (Exception e) {
				System.out.println(e);
				Log.info("Unable to enter text");
			}
		}
	}

	public void selectFromDropdown(WebElement ele, String txt) {
		Select drpAccount = new Select(ele);
		drpAccount.selectByVisibleText(txt);
	}

	public void selectFromDropdown(WebElement ele, int index) {
		Select drpAccount = new Select(ele);
		drpAccount.selectByIndex(index);
		;
	}

	public boolean isClickable(WebElement ele) {
		@SuppressWarnings("unused")
		WebElement element = null;
		try {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			element = wait.until(ExpectedConditions.elementToBeClickable(ele));
			// Log.info("Element is visible...");

			return true;
		} catch (org.openqa.selenium.TimeoutException e) {
			Log.info("Element " + ele.getText() + " not Clickable...");
		}
		return false;
	}

	public String getContent(WebElement ele) {
		try {
			if (isVisible(ele, 10)) {
				waitFor(0);
				return ele.getText();
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public void click(WebElement ele) {
		if (isClickable(ele)) {
			try {
				ele.click();
			} catch (Exception e) {
				Log.info("Unable to click");
			}
		}
	}

	public void clickFast(WebElement ele) {
		try {
			ele.click();
		} catch (Exception e) {
			Log.info("Unable to click");
		}
	}

	public boolean isVisibleWeb(WebElement ele) {
		@SuppressWarnings("unused")
		WebElement element = null;
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 20);
			element = wait.until(ExpectedConditions.visibilityOf(ele));
			return true;
		} catch (org.openqa.selenium.TimeoutException e) {
			Log.info("Element not visible...");
		}
		return false;
	}

	public boolean isElementPresent(WebElement ele, String name) {
		if (ele.isDisplayed()) {
			Log.info(name + " is displayed");
			return true;
		} else {
			Log.info(name + " is Not displayed");
			return false;
		}

	}

	public static void waitFor(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean isVisible(WebElement ele) {
		@SuppressWarnings("unused")
		WebElement element = null;
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			element = wait.until(ExpectedConditions.visibilityOf(ele));
			return true;
		} catch (org.openqa.selenium.TimeoutException e) {
			Log.info("Element not visible...");
		}
		return false;
	}

	public boolean isVisible(WebElement ele, int time) {
		@SuppressWarnings("unused")
		WebElement element = null;
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, time);
			element = wait.until(ExpectedConditions.visibilityOf(ele));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
			return true;
		} catch (org.openqa.selenium.TimeoutException e) {
			Log.info("Element not visible...");
		}
		return false;
	}

	public String randomString(int length) {
		return RandomStringUtils.randomAlphabetic(length);
	}

	public String randomAlphNumeric(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}

	public String randomNumber(int length) {
		return RandomStringUtils.randomNumeric(length);
	}

	public void updateCurrentBalance() {
		// currentBalance =
		// Float.parseFloat(cleanCurrency(BillPay.getBalance().getText()));
		availableBalnce = Float.parseFloat(cleanCurrency(getavailableBalance().getText()));
	}

	public String cleanCurrency(String str) {
		return str.replace("$", "");
	}

	public void getUrl(String url) {
		try {
			Log.info("Navigating to URL - " + url);
			driver.get(url);
		} catch (Exception e) {
			Log.info("Unable to navigate to URL - " + url);
		}
	}

	public String getLastTransactionFromTable() {
		try {
			List<WebElement> TotalRowsList = gettransactionTable().findElements(By.tagName("tr"));
			int i = TotalRowsList.size();
			i--;
			return driver.findElement(By.xpath("//*[@id='transactionTable']/tbody/tr[" + i + "]/td[3]")).getText();
		} catch (Exception e) {
			Log.info("Unable to get last transaction");
			return null;
		}

	}

}
