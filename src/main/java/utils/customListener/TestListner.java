package utils.customListener;

import java.util.Set;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.axelerant.base.BaseClass;

import utils.generic.FileOperations;
import utils.logger.Log;

public class TestListner implements ITestListener {

	BaseClass base = new BaseClass();

	public void onTestStart(ITestResult result) {

		Log.info("Test Execution started for -> " + result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		Log.info("Test Execution PASSED for -> " + result.getName());
		if (result.getTestClass().toString().contains("TestCase_Rest_AccountOpen")) {
			

		}
	}

	public void onTestFailure(ITestResult result) {
		Log.info("Test Execution FAILED for -> " + result.getName());
		if (result.getTestClass().toString().contains("TestCase_Rest_AccountOpen")) {

		} else {
			FileOperations.takeScreenShot(result.getName().toString().trim(), BaseClass.driver);
		}
	}

	public void onTestSkipped(ITestResult result) {
		Log.info("Test Execution SKIPPED for -> " + result.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onStart(ITestContext context) {
		Log.info("Staring Suite  -> " + context.getSuite().getName());
	}

	public void onFinish(ITestContext context) {
		Log.info("Suite completed  -> " + context.getSuite().getName());

		Set<ITestResult> failedTests = context.getFailedTests().getAllResults();
		for (ITestResult temp : failedTests) {
			ITestNGMethod method = temp.getMethod();
			if (context.getFailedTests().getResults(method).size() > 1) {
				failedTests.remove(temp);
			} else {
				if (context.getPassedTests().getResults(method).size() > 0) {
					failedTests.remove(temp);
				}
			}
		}
	}

}
