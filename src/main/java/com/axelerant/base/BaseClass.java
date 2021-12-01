package com.axelerant.base;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utils.generic.PropertyReader;
import utils.logger.Log;

public class BaseClass implements IBaseInterface {
	public static WebDriver driver = null;
	static Properties configProp = new Properties();
	public static final String URL;
	public static final String user;
	public static final String pswd;
	public static final String custID;
	public static final String accountActivityURL;
	public BaseClass base;


	static {
		configProp = PropertyReader.getProperty(PROP_PATH);
		URL = configProp.getProperty("appURL");
		user = configProp.getProperty("userName");
		pswd = configProp.getProperty("password");
		custID = configProp.getProperty("customerID");
		accountActivityURL = configProp.getProperty("accountActivityURL");
	}
	
	@BeforeSuite
	public void setUp() {
		driver = createDriver();
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(URL);
	}
	
	public WebDriver createDriver() {
		System.setProperty("webdriver.chrome.driver", CHROME_PATH);
		driver = new ChromeDriver();
		return driver;
	}
		

	@AfterSuite
	public void tearDown() throws Exception {
		Log.info("Closing driver...");
		if (driver != null) {
			driver.quit();
		}
	}

}
