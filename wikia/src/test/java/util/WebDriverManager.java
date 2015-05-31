package util;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.Reporter;

import pageobject.BasePage;

public class WebDriverManager {
	private static WebDriver driver =null; 
	private static final Logger LOGGER = Logger.getLogger(WebDriverManager.class);
	
	private static DesiredCapabilities capability;
	static ProfilesIni allProfiles = new ProfilesIni();
	static FirefoxProfile webdriver;
	
	
	private static Constants CONSTANTS = Constants.getInstance();
	ReadingProperties rp = CONSTANTS.getRp();
	
	//default no args is Firefox 
	public static WebDriver startDriver (){

		 capability = DesiredCapabilities.firefox();
	     capability.setBrowserName("Firefox");
	     capability.setPlatform(org.openqa.selenium.Platform.ANY);
	     
	    webdriver = allProfiles.getProfile("WebDriver");
	
	    webdriver.setPreference("browser.cache.disk.enable", false);
	    webdriver.setPreference("browser.cache.memory.enable", false);
	    webdriver.setPreference("browser.cache.offline.enable", false);
	    webdriver.setPreference("network.http.use-cache", false);
	    webdriver.setPreference("extensions.checkCompatibility", false);
	    
		webdriver.setEnableNativeEvents(true);
		driver=new FirefoxDriver(webdriver);
		return driver;
	}

	public static WebDriver startDriver(String browser)
	{
		if(browser.equalsIgnoreCase("firefox")) 
		{
	        capability = DesiredCapabilities.firefox();
	        capability.setBrowserName("Firefox");
	        capability.setPlatform(org.openqa.selenium.Platform.ANY);
	        webdriver = allProfiles.getProfile("WebDriver");
		    webdriver.setPreference("browser.cache.disk.enable", false);
			webdriver.setEnableNativeEvents(true);
			driver=new FirefoxDriver(webdriver);
	    }

		else if(browser.equalsIgnoreCase("ie")) {
			
			String iePath="";
	        capability = DesiredCapabilities.internetExplorer();
	        capability.setBrowserName("IE");
	        capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
            capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

			iePath=System.getProperty("user.dir")+CONSTANTS.getIePath();//reading from config.properties file 
			System.setProperty("webdriver.ie.driver", iePath);	
			driver=new InternetExplorerDriver(capability);
						
	    }
		else  if(browser.equalsIgnoreCase("chrome")) {
	        capability = DesiredCapabilities.chrome();
	        capability.setBrowserName("CHROME");
	        capability.setPlatform(org.openqa.selenium.Platform.ANY);
			ChromeOptions options = new ChromeOptions(); 
			 
			String chromePath = System.getProperty("user.dir")+CONSTANTS.getChromePathMac();
            if (System.getProperty("os.name").contains("Windows")) {
                chromePath =System.getProperty("user.dir")+CONSTANTS.getChromePathWin();
            }
            System.setProperty("webdriver.chrome.driver", chromePath);
		    options.addArguments("test-type");
			capability.setCapability(ChromeOptions.CAPABILITY, options);
			driver =new ChromeDriver(capability);

	    }
		else if(browser.equalsIgnoreCase("safari")) {
	    	SafariOptions options = new SafariOptions();
	        capability = DesiredCapabilities.safari();
	        capability.setBrowserName("Safari");
	        capability.setPlatform(org.openqa.selenium.Platform.ANY);

	        if(isSupportedPlatform()==true);

			{
			    driver = new SafariDriver(capability);

			}			
	    }
		else {
            throw new RuntimeException("Browser type unsupported");//super for IllegalArgumentException
        }
		LOGGER.info("Started browser");
		browserInfo(driver);
		return driver;
	}


	public  static String  browserInfo(WebDriver driver)
	{
		 Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
		    String browserName = caps.getBrowserName().toUpperCase();
			String browserVersion = caps.getVersion();
			
			Reporter.log("Browser name: "+browserName+" browser version: "+browserVersion);
			System.out.println("Browser name: "+browserName+" browser version:  "+browserVersion);
			
			return browserName;
	}
	private static boolean isSupportedPlatform() {
	    Platform current = Platform.getCurrent();
	    return Platform.MAC.is(current) || Platform.WINDOWS.is(current);
	  }


	public static void stopDriver(WebDriver driver)
	{
		driver.quit();
		LOGGER.info("driver.quit()");
	}

	public static WebDriver getDriverInstance()
	{
		//in progress
		return driver;
	}

	public static  void defaultWindowSize(WebDriver driver)
	{
		//diver.manage().window().maximize(); //this would work only for Firefox and IE
		driver.manage().window().setPosition(new Point(0,0));
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		//Dimension dim = new Dimension(1024, 768);

		Dimension dim = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
		driver.manage().window().setSize(dim);
	}

	public static Logger LoggerGetInstance() {
		return LOGGER;
	}
}