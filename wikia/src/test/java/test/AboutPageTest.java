package test;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.AboutPage;

import runner.BaseTestSuite;
import util.CommonMethods;
import util.Constants;

public class AboutPageTest extends BaseTestSuite{

	
	
	private WebDriver driver;
	private static final Logger LOGGER = Logger.getLogger(AboutPageTest.class);
	
	private AboutPage aboutPage ;
    String username = Constants.getInstance().getTestUser();
	String pswd = Constants.getInstance().getTestUserPassword();
    
    @Test(priority=1)
    public  void initPages(){
    	
    	this.driver = super.getFirefoxDriver();
    	 aboutPage = new AboutPage(this.driver);
    	
    	//	    CommonMethods.loadUrl(driver, Constants.getInstance().getTest_env());

    	aboutPage.get();
    	
    }
    
    @Test(priority=2)
    public void testTopNavLogoDisplayed(){
		;
    	boolean isForWorkLinkDisplayed = aboutPage.isTopNavLogoDisplayed();
    	Assert.assertTrue(isForWorkLinkDisplayed==true);
    }
    
  
   
}
