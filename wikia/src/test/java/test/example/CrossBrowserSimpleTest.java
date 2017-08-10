package test.example;

import java.io.File;

import runner.BaseTestSuite;
import util.CommonMethods;
import util.TakeScreenshot;
import util.WebDriverManager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.annotations.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;


/*
 * WebDriver 3.4 requires JDK 8
 * 
 */
public class CrossBrowserSimpleTest {

	private final String firefoxPath=System.getProperty("user.dir")+ "/src/test/java/config/geckodriver.exe";
    private final String url = "https://www.ifonly.com/";
	 
	public void getBrowserInfo(WebDriver driver){

		String info = (String) ((JavascriptExecutor) driver).executeScript("return navigator.userAgent;");
		System.out.println("Browser info: "+info);
	}


	@Test(invocationCount=1)
	public void testGetFirefoxViaNativeApi(){	

		System.setProperty("webdriver.gecko.driver", firefoxPath);	
		Capabilities capabilities  = DesiredCapabilities.firefox();

		System.out.println(capabilities.getBrowserName());
		System.out.println(capabilities.getPlatform());
		System.out.println(capabilities.getVersion());
		System.out.println (capabilities.asMap());

		/*
		 * The Profile class may be used to configure the browser profile used with WebDriver, 
		 * with functions to install additional extensions, configure browser preferences;
		 * Ultimately FirefoxProfile and ChromeOptions are just wrappers over DesiredCapabilities 
           in the end, everything is converted down to a dictionary of DesiredCapabilities
		 */
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setPreference("network.http.phishy-userpass-length", 255);
		firefoxProfile.setAcceptUntrustedCertificates(true);

		//Manage firefox specific settings in a way that geckodriver can understand
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.addPreference("browser.startup.page", 1);

		WebDriver driver =new FirefoxDriver(firefoxOptions);
		driver.get(url);
		driver.close();
	}

	@Test
	public void testWebDriverManagerGetFirefox(){

		WebDriverManager DRIVER_MANAGER =WebDriverManager.getInstance();
		WebDriver firefoxDriver = DRIVER_MANAGER.getDriver("firefox");
		firefoxDriver.get(url);
		DRIVER_MANAGER.closeDriverWindows("firefox"); //using wrapper method
	}	
	
	@Test
	//need to have Chrome v56 or higher 
	public void testWebDriverManagerGetChrome(){

		WebDriverManager DRIVER_MANAGER =WebDriverManager.getInstance();
		WebDriver chromeDriver = DRIVER_MANAGER.getDriver("chrome");
		chromeDriver.get(url);
		CommonMethods.pause(1500);
		chromeDriver.quit();//using native WebDriver API
	//	DRIVER_MANAGER.closeDriverWindows("chrome"); //using wrapper method leaves chromedriver.exe process running on windows
		//DRIVER_MANAGER.destroyWebDriverInstances("chrome"); //using wrapper method leaves chromedriver.exe process running on windows

	}

	@Test
	public void testWebDriverManagerGetInternetExplorer(){

		WebDriverManager DRIVER_MANAGER =WebDriverManager.getInstance();
		WebDriver ieDriver = DRIVER_MANAGER.getDriver("ie");
		ieDriver.get(url);
		CommonMethods.pause(1500);
		DRIVER_MANAGER.closeDriverWindows("ie"); //using wrapper method
	}


	@Test
	public void testBaseTestSuiteFirefox(){
		WebDriver firefoxDriver = BaseTestSuite.getFirefoxDriver();
		firefoxDriver.get(url);
		BaseTestSuite.closeDrivers();

	}

	@Test
	public void testBaseTestSuiteChrome(){
		WebDriver chromeDriver = BaseTestSuite.getChromeDriver();
		chromeDriver.get(url);
		BaseTestSuite.closeDrivers();


	}
	
	@Test
	public void testWebDriverManagerGetIosMobileDriver(){

		WebDriverManager DRIVER_MANAGER =WebDriverManager.getInstance();
		WebDriver iosMobileDriver = DRIVER_MANAGER.getDriver("iosMobileDriver");
		iosMobileDriver.get(url);
		DRIVER_MANAGER.closeDriverWindows("iosMobileDriver"); //using wrapper method
	}

	

}