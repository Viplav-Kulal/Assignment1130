package utils.generic;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.axelerant.base.IBaseInterface;

public class FileOperations implements IBaseInterface {

	public static void takeScreenShot(String methodName, WebDriver driver) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(SCREENSHOT_PATH + methodName + TIME_STAMP + ".png"));
			System.out.println("*******Screenshot added @ " + SCREENSHOT_PATH + " ********");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
