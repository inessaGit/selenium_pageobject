package util;

import runner.BaseTestSuite;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;


public class TakeScreenshot {

	private static final Logger LOGGER = Logger.getLogger(TakeScreenshot.class);
	private static final Constants CONSTANTS;
	private static final String destDir;
	private static final String tempScreenshotFolderPath; 
	private static final String testngTestOutputFolderPath;

	static {
		CONSTANTS =Constants.getInstance();
		destDir = System.getProperty("user.dir")+CONSTANTS.getUsergenPath();
		tempScreenshotFolderPath =System.getProperty("user.dir")+"/report/screenshot";
		testngTestOutputFolderPath =System.getProperty("user.dir")+"/test-output/screenshot/";

		new File(destDir).mkdirs();
	}
	
	public static  void takeScreenshot(WebDriver driver)

	{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		LOGGER.info("Generated screenshot. Screenshot saved in "   + destDir);
		String destFile =TimeUtil.getCurrentDate()+"_"+TimeUtil.getCurrentTime() + ".png";
		try {

			FileUtils.copyFile(scrFile, new File(destDir + destFile));

		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.debug("Could not take screenshot.");
		}

		//Reporter.log("<img src=\"file:///" + str +"/target/screenshot/"+ screenshotName + "\" alt=\"\"/><br/>");
		Reporter.setEscapeHtml(false);
		Reporter.log("Saved <a href=../screenshot/UserGen/" + destFile + ">Screenshot</a>");
	}
	
	//should be called once in @AfrerSuite
	public static void copyScreenshotsToTestOutput(){
		
		File srcDir = new File(tempScreenshotFolderPath);
		File testngTestOutputFolder = new File (testngTestOutputFolderPath);
		
		try {
			FileUtils.copyDirectory(srcDir, testngTestOutputFolder);
		} catch (IOException e) {
			
			e.printStackTrace();
			LOGGER.debug("Could not copyScreenshotsToTestOutput.");

		}
	}
	
	public static void deleteScreenshotsInTestOutput(){
		File testngTestOutputFolder = new File (testngTestOutputFolderPath);
		FileUtils.deleteQuietly(testngTestOutputFolder);

	}
	
	public static void deleteScreenshotsInReportScreenshot(){
		File srcDir = new File(tempScreenshotFolderPath);
		FileUtils.deleteQuietly(srcDir);

	}
}

class TestTakeScreenshot{
	
	 public static void main (String args[]){
		 
		WebDriver firefoxDriver =  BaseTestSuite.getFirefoxDriver();
		firefoxDriver.get("https://dev.mysql.com/doc/refman/5.7/en/date-and-time-functions.html#function_date-add");
		TakeScreenshot.takeScreenshot(firefoxDriver);
		
		firefoxDriver.get("http://stackoverflow.com/questions/31744778/opening-ie-web-driver-in-selenium");
		TakeScreenshot.takeScreenshot(firefoxDriver);

		BaseTestSuite.destroyWebDrivers();
		
	 }
}
