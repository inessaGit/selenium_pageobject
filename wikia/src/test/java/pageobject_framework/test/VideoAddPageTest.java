package pageobject_framework.test;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageobject_common.util.Constants;
import pageobject_framework.pages.Contribute;
import pageobject_framework.pages.HomePage;
import pageobject_framework.pages.Login;
import pageobject_framework.pages.VideoAddPage;
import pageobject_framework.runner.BaseTestSuite;

public class VideoAddPageTest extends BaseTestSuite {
	
	private WebDriver driver;
	private static final Logger LOGGER = Logger.getLogger(VideoAddPageTest.class);
	
	HomePage homePage;
	Login login;
	Contribute contribute;
	VideoAddPage videoAddPage;
	
	String pswd = Constants.getInstance().getRp().readConfigProperties("testuser.password");
    String username = Constants.getInstance().getRp().readConfigProperties("testuser.username");
    
    String fileName ="The Best Classical Music In The World";
    
    @Test(priority=1)
    public  void initTest(){
    	
    	this.driver = super.getFirefoxDriver();
		homePage = new HomePage(this.driver);
    	homePage.get();
    	login = homePage.login();
    	login.get();
    	contribute=homePage.contribute();
    	contribute.get();
    	
    }
    
    @Test(priority=2)
    public void testGetVideoPage(){
    	videoAddPage =contribute.VideoAddPage();
    	videoAddPage.get();
    	//Assert.assertTrue("you must login message displayed");
    }
    @Test(priority=3)
    public void testAddVideoFlashMessage(){
    	
		login.login(username, pswd);

    	contribute.clickContribute();
    	contribute.clickAddVideo(); 
    	
    	String testVideo="https://www.youtube.com/watch?v=h9tRIZyTXTI";
    	videoAddPage.addVideo(testVideo);
    	
    	String actualFlashMessage =videoAddPage.getFlashMessage();
    	String expectedFlashMessage ="Video page File:"+fileName+" was successfully added.";
    	System.out.println("Flash message expected: "+expectedFlashMessage);
    	Assert.assertTrue(actualFlashMessage.equalsIgnoreCase(expectedFlashMessage));
    }
    
    @Test(priority=3)
    public void testClickFlashMessage(){
    	
    	String actualUrl =videoAddPage.clickFlashMessage();
    	String expectedUrl ="http://qm-homework.wikia.com/wiki/File:"+fileName.replace(" ", "_");
    	System.out.println("Expected url: "+expectedUrl);
    	Assert.assertTrue(actualUrl.equalsIgnoreCase(expectedUrl));
    	Assert.assertTrue(actualUrl.contains(fileName.replace(" ", "_")));

    }
    
}
