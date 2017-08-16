package runner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import pages.BasePage;
import util.Constants;
import util.Return2D;
import util.TakeScreenshot;
import util.WebDriverManager;

/**
 * @author Inessa 
 * 1)Every PageTest class should extend this class
 * 2) This class contains common logic for all test classes; driven by TestNG
 *
 */
public abstract class BaseTestSuite {

	private static WebDriverManager DRIVER_MANAGER = null;
	private static   WebDriver firefoxDriver = null;
	private static  WebDriver chromeDriver = null;
	private static WebDriver ieDriver = null;
	private static  WebDriver safariDriver = null;
	private static WebDriver iosMobileDriver = null;


    private static final Logger LOGGER = Logger.getLogger(BaseTestSuite.class);
	public static  Constants CONSTANTS=null;

	//spreadsheet for different environments
	public static  Sheet SHEET_PRODUCTS;
	public static   Sheet SHEET_REGISTRATION;
	public static   Sheet SHEET_PROMO;
	public static   Sheet SHEET_VENDOR_SIGNUP;

	public static final Return2D RETURN2D;

	//static block should be executed only once when the class loaded; a good place to put initialization of static variables.
	static
	{
		DRIVER_MANAGER = WebDriverManager.getInstance();//all drivers are set in constructor 
		CONSTANTS = Constants.getInstance();
		RETURN2D = new Return2D();
		
		String filePath =System.getProperty("user.dir")+CONSTANTS.getTest_excelPath();
		try {
			LOGGER.info(BaseTestSuite.class.getName()+" attempting to load test data  spreadsheets");	 

			SHEET_PRODUCTS=RETURN2D.getSheet(filePath, "TEST");
			SHEET_REGISTRATION=RETURN2D.getSheet(filePath, "TEST_Registration");
			SHEET_PROMO=RETURN2D.getSheet(filePath, "TEST_Promo");
			LOGGER.info(BaseTestSuite.class.getName()+" Test data  spreadsheets successfully loaded");	 

		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.debug(e);
		}
	}
	// DRIVER getters 
	public static  WebDriver getFirefoxDriver() {
		firefoxDriver = DRIVER_MANAGER.getDriver("firefox");
		return firefoxDriver;
	}

	public static  WebDriver getIOSMobileDriver() {
		firefoxDriver = DRIVER_MANAGER.getDriver("iosMobileDriver");
		return iosMobileDriver;
	}

	
	public static WebDriver getChromeDriver() {
		chromeDriver = DRIVER_MANAGER.getDriver("chrome");
		return chromeDriver;
	}

	public static WebDriver getIeDriver() {
		ieDriver = DRIVER_MANAGER.getDriver("ie");
		return ieDriver;
	}

	public static WebDriver getSafariDriver() {
		safariDriver = DRIVER_MANAGER.getDriver("safari");
		return safariDriver;
	}

	
	//@BeforeTest() or @BeforeSuite 
	public static void startDrivers(){
		LOGGER.info(BaseTestSuite.class.getName()+" running @BeforeTest - startDrivers()");	 
		 
		firefoxDriver = DRIVER_MANAGER.getDriver("firefox");
		chromeDriver = DRIVER_MANAGER.getDriver("chrome");
		ieDriver = DRIVER_MANAGER.getDriver("ie");
		safariDriver = DRIVER_MANAGER.getDriver("safari");
		iosMobileDriver = DRIVER_MANAGER.getDriver("iosMobileDriver");

		
		LOGGER.info(BaseTestSuite.class.getName()+" running @BeforeTest - all drivers started");	 
	}

	
	//@BeforeTest - For suite test, run before any test method belonging to the classes inside the <test> tag is run. 
	//@AfterTest - For suite test, run after all the test methods belonging to the classes inside the <test> tag have run.  
	@AfterTest(alwaysRun=true)
	public static void closeDrivers(){

		LOGGER.info(BaseTestSuite.class.getName()+ " Attempting to  close  WebDriver browser windows.");
		DRIVER_MANAGER.closeDriverWindows("firefox");
		DRIVER_MANAGER.closeDriverWindows("chrome");
		DRIVER_MANAGER.closeDriverWindows("ie");
		DRIVER_MANAGER.closeDriverWindows("safari");
		DRIVER_MANAGER.closeDriverWindows("iosMobileDriver");

		LOGGER.info(BaseTestSuite.class.getName()+ " Successfully closed  WebDriver browser windows.");

	}

   // @AfterSuite(alwaysRun=true)
	public static void destroyWebDrivers()
	{
		LOGGER.info(BaseTestSuite.class.getName()+ " Attempting to  destroy WebDriver instances.");
		DRIVER_MANAGER.destroyWebDriverInstances("firefox");
		DRIVER_MANAGER.destroyWebDriverInstances("chrome");
		DRIVER_MANAGER.destroyWebDriverInstances("safari");
		DRIVER_MANAGER.destroyWebDriverInstances("ie");
		DRIVER_MANAGER.destroyWebDriverInstances("iosMobileDriver");

		
		LOGGER.info(BaseTestSuite.class.getName()+ " Successfully destroyed  webdriver instances.");

		}

 
	}