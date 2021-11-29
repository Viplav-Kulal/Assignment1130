package com.axelerant.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public interface IBaseInterface {
	public static final String PROJECT_PATH = new File("").getAbsolutePath();
	public static final String RESOURCE_PATH = new File("").getAbsolutePath() + "/src/main/resources";
	public static final String CHROME_PATH = PROJECT_PATH + "/chromedriver/chromedriver.exe";
	public static final String TIME_STAMP = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss")
			.format(Calendar.getInstance().getTime());
	public final static String PROP_PATH = RESOURCE_PATH + "/properties/environment.properties";
	public static final String SCREENSHOT_PATH = PROJECT_PATH + "/ScreenShots";
}
