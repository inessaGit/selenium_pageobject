package pageobject;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import util.Constants;
import util.TakeScreenshot;
import util.WebDriverManager;

public class BaseSuite  {

	    private static Logger LOGGER=Logger.getLogger(BaseSuite.class);
		public static final Constants CONSTANTS = Constants.getInstance();
		
		public static final   String base_url;
		public static final String homepage_url;
		
	    private static   WebDriver driver; 
		public  static final TakeScreenshot ts;

		static{
			   ts= new TakeScreenshot();
			   base_url=CONSTANTS.getBase_url();
			   homepage_url=CONSTANTS.getHomepage_url();
		}
		
		public static WebDriver getDriver(){
			return driver;
		}
		
		@AfterClass(alwaysRun=true)
		public void closeBasePage(){
			if (driver!=null){
			driver.close();
			}
		}
		
		@AfterSuite(alwaysRun=true)
		public static void quitDriver(){
			if (driver!=null)
			{
				driver.quit();
			}
		}
		
		@Parameters("browser")
		@BeforeClass
		public void getDriverInstance(@Optional("firefox") String browser) {

			if (browser.equalsIgnoreCase("firefox")) 

			{       
				driver=WebDriverManager.startDriver("firefox");
			}

			else if (browser.equalsIgnoreCase("chrome")) 

			{
				driver=WebDriverManager.startDriver("chrome");
			} 

			else if (browser.equalsIgnoreCase("safari")) 				
			{
				driver=WebDriverManager.startDriver("safari");
			} 

			else if (browser.equalsIgnoreCase("ie")) 				
			{
				driver=WebDriverManager.startDriver("ie");
			} 
			
		}
		
		@Parameters("url")
		@BeforeClass//runs before first test method in the current test class run
		public void getToHomePage(@Optional("homepage_url")String url)
		{
			
			  if (url.equalsIgnoreCase("base_url"))
			{
				driver.get(base_url);
				LOGGER.info("Environment for test: "+base_url);
				System.out.println("Environment for test: "+base_url);

			}
			else  if (url.equalsIgnoreCase("dev_homepage_env"))
			{
				//driver.get(dev_homepage_env);
				LOGGER.info("Environment for test: DEV");
				System.out.println("Environment for test: DEV");

			}

		}
}