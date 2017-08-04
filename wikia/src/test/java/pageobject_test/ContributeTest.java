package pageobject_test;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageobject_framework.runner.BaseTestSuite;
import pageobject.Contribute;
import pageobject.HomePage;
import pageobject.Login;
import pageobject.VideoAddPage;
import pageobject.util.CommonMethods;
import pageobject.util.Constants;

public class ContributeTest extends BaseTestSuite{

	
	
	private WebDriver driver;
	private static final Logger LOGGER = Logger.getLogger(ContributeTest.class);
	
	HomePage homePage;
	Login login;
	Contribute contribute;
	VideoAddPage videoAddPage;
	
	String pswd = Constants.getInstance().getRp().readConfigProperties("testuser.password");
    String username = Constants.getInstance().getRp().readConfigProperties("testuser.username");
    
    @Test(priority=1)
    public  void initPages(){
    	
    	this.driver = super.getFirefoxDriver();
    	homePage = new HomePage(this.driver);
    	//	    CommonMethods.loadUrl(driver, Constants.getInstance().getTest_env());

    	homePage.get();
    	
    	login = homePage.login();
    	login.get();
    	
    	contribute=homePage.contribute();
    	contribute.get();
    }
    
    @Test(priority=2)
    public void testContributeDropdownDisplayed(){
		login.login(username, pswd);

    	contribute.clickContribute();
    	boolean isContributeDropdownDispayed = contribute.isContributeDropdownDisplayed();
    	Assert.assertTrue(isContributeDropdownDispayed==true);
    }
    
    @Test(priority=3)
    public void testAddVideoRedirect(){
    	String actualUrl = contribute.clickAddVideo();
    	Assert.assertTrue(actualUrl.equalsIgnoreCase(contribute.videoUrl));
    }
    
    @Test(priority=4)
    public void testGetVideoAddPage(){
    	videoAddPage =contribute.VideoAddPage();
    	videoAddPage.get();
    }
   
}
