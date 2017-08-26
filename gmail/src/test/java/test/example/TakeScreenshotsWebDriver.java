package test.example;
import runner.BaseTestSuite;
import util.TakeScreenshot;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


public class TakeScreenshotsWebDriver {

	@Test
	public void testTakeScreenshot(){
		WebDriver firefoxDriver =  BaseTestSuite.getFirefoxDriver();
		firefoxDriver.get("https://dev.mysql.com/doc/refman/5.7/en/date-and-time-functions.html#function_date-add");
		TakeScreenshot.takeScreenshot(firefoxDriver);
		
		firefoxDriver.get("http://stackoverflow.com/questions/31744778/opening-ie-web-driver-in-selenium");
		TakeScreenshot.takeScreenshot(firefoxDriver);

		BaseTestSuite.closeDrivers();
	}
	
	@Test
	public void copyScreenshotsToTestOutput(){
		WebDriver firefoxDriver =  BaseTestSuite.getFirefoxDriver();
		firefoxDriver.get("https://dev.mysql.com/doc/refman/5.7/en/date-and-time-functions.html#function_date-add");
		TakeScreenshot.takeScreenshot(firefoxDriver);
		
		firefoxDriver.get("http://stackoverflow.com/questions/31744778/opening-ie-web-driver-in-selenium");
		TakeScreenshot.takeScreenshot(firefoxDriver);
		TakeScreenshot.copyScreenshotsToTestOutput();

		BaseTestSuite.closeDrivers();
	}
	
	@Test
	public void deleteScreenshotsInTestNGFolder(){
		WebDriver firefoxDriver =  BaseTestSuite.getFirefoxDriver();
		firefoxDriver.get("https://dev.mysql.com/doc/refman/5.7/en/date-and-time-functions.html#function_date-add");
		TakeScreenshot.takeScreenshot(firefoxDriver);
		
		TakeScreenshot.deleteScreenshotsInTestOutput();

		BaseTestSuite.closeDrivers();
	}
	
	@Test
	public void deleteScreenshotsInReportFolder(){
		WebDriver firefoxDriver =  BaseTestSuite.getFirefoxDriver();
		firefoxDriver.get("https://dev.mysql.com/doc/refman/5.7/en/date-and-time-functions.html#function_date-add");
		TakeScreenshot.takeScreenshot(firefoxDriver);
		
		
		TakeScreenshot.deleteScreenshotsInReportScreenshot();

		BaseTestSuite.closeDrivers();
	}
}
