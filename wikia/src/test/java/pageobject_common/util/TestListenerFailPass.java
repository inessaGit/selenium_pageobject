package pageobject_common.util;

import pageobject_framework.runner.BaseTestSuite;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.apache.log4j.*;


/*
 * It's very easy to generate your own reports with TestNG with Listeners and Reporters:
Listeners implement the interface org.testng.ITestListener and are notified in real time 
of when a test starts, passes, fails, etc...
Reporters implement the interface org.testng.IReporter and are notified when all the suites 
have been run by TestNG. The IReporter instance receives a list of objects
 that describe the entire test run.
 */

/*
 * extended TestListenerAdapter, which implements ITestListener with empty methods, 
 * so  don't have to override other methods from the interface that I have no interest in. 
 * If you try public class 'TestScreenshotOnFailure implements ITestResult' - implementing an interface  instead of 
 * extending your class with a TestNG class that implements the interface you want (ITestResult) then
 * you will have to override ALL (15-18) methods declared in ITestResult interface which is not productive
 */

/*THIS CLASS DEFINES RULES for DEFAULT BEHAVIOUR of  org.testng.ITestResult.Failure or Success
 * http://testng.org/javadocs/constant-values.html#org.testng.ITestResult.FAILURE

 * TO USE THIS CLASS:
 * 
 *put this annotation before you class where you define your test methods
 @Listeners({ util.TestListenerFailPass.class })
 */
public class TestListenerFailPass extends TestListenerAdapter {

	WebDriver driver;
	private int m_count = 0;
	private static final Logger LOGGER = Logger.getLogger(TestListenerFailPass.class);
	private static final Constants CONSTANTS =Constants.getInstance();

	@Override
	public void onTestFailure(ITestResult tr) {
		System.out.println("TestListener test failed "+tr.getInstanceName());
		
		
		//2/13 temp only firefox 
		driver   = BaseTestSuite.getFirefoxDriver();

		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destDir = System.getProperty("user.dir")+CONSTANTS.getFailPath();
		new File(destDir).mkdirs();
		String destFile =TimeUtil.getCurrentDate()+"_"+TimeUtil.getCurrentTime() + ".png";


		try {
			FileUtils.copyFile(scrFile, new File(destDir + destFile));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not take screenshot on failure "+ tr.getInstanceName());//getInstanceName =package+className
			LOGGER.debug("Could not take screenshot on failure "+ tr.getInstanceName());//getInstanceName =package+className
		}
		Reporter.setEscapeHtml(false);
		Reporter.log("Saved <a href=../screenshot/FAIL/" + destFile + ">Screenshot</a>");
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		LOGGER.info("Skipped test");
		Reporter.log("Skipped test");
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		driver   = BaseTestSuite.getFirefoxDriver();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destDir = System.getProperty("user.dir")+CONSTANTS.getPassPath();
		//System.out.println("destDir onTestSuccess "+ destDir);
		new File(destDir).mkdirs();
		String destFile =TimeUtil.getCurrentDate()+"_"+TimeUtil.getCurrentTime() + ".png";

		try {
			FileUtils.copyFile(scrFile, new File(destDir + destFile));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not take screenshot on success "+ tr.getInstanceName());//getInstanceName =package+className

			LOGGER.debug("Could not take screenshot on success "+ tr.getInstanceName());//getInstanceName =package+className
		}
		Reporter.setEscapeHtml(false);
		Reporter.log("Saved <a href=../screenshot/PASS/" + destFile + ">Screenshot</a>");
	}

	private void log(String string) {
		System.out.print(string);
		if (++m_count % 40 == 0) {
			System.out.println("");
		}
	}
}