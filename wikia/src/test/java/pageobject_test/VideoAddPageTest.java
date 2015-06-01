package pageobject_test;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobject.BaseSuite;
import pageobject.Contribute;
import pageobject.HomePage;
import pageobject.Login;
import pageobject.VideoAddPage;
import util.Constants;

public class VideoAddPageTest extends BaseSuite {
	
	HomePage homePage;
	Login login;
	Contribute contribute;
	VideoAddPage videoAddPage;
	
	String pswd = Constants.getInstance().getRp().readConfigProperties("testuser.password");
    String username = Constants.getInstance().getRp().readConfigProperties("testuser.username");
    
    String fileName ="The Best Classical Music In The World";
    
    @Test(priority=1)
    public  void initTest(){
    	
    	homePage = new HomePage(BaseSuite.getDriver());
    	homePage.get();
    	login = homePage.Login();
    	login.get();
    	contribute=homePage.Contribute();
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
