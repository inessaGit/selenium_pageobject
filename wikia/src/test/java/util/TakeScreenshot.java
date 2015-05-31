package util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;


public class TakeScreenshot {

	private static final Logger LOGGER = Logger.getLogger(TakeScreenshot.class);
	private static final Constants CONSTANTS =Constants.getInstance();
	
		public  void takeScreenshot(WebDriver driver)

	    {
	   	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destDir = System.getProperty("user.dir")+CONSTANTS.getUsergenPath();
		LOGGER.info("UserGen screenshot saved in "   + destDir);
		new File(destDir).mkdirs();
		String destFile =TimeUtil.CurrentDate()+"_"+TimeUtil.CurrentTime() + ".png";

		try {
			
			FileUtils.copyFile(scrFile, new File(destDir + destFile));

		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.debug("Could not take screenshot ");//getInstanceName =package+className
		}

	         //Reporter.log("<img src=\"file:///" + str +"/target/screenshot/"+ screenshotName + "\" alt=\"\"/><br/>");
	 		Reporter.setEscapeHtml(false);
	 		Reporter.log("Saved <a href=../screenshot/UserGen/" + destFile + ">Screenshot</a>");
	    }
	}

