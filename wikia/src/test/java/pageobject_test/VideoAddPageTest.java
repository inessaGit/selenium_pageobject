package pageobject_test;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobject.BaseSuite;
import pageobject.Contribute;
import pageobject.HomePage;
import pageobject.Login;
import pageobject.VideoAddPage;
import util.Constants;

public class VideoAddPageTest {
	
	HomePage homePage;
	Login login;
	Contribute contribute;
	VideoAddPage videoAddPage;
	
	String pswd = Constants.getInstance().getRp().readConfigProperties("testuser.password");
    String username = Constants.getInstance().getRp().readConfigProperties("testuser.username");
    
    @Test(priority=1)
    public  void initTest(){
    	homePage = new HomePage(BaseSuite.getDriver());
    	homePage.get();
    	login = homePage.Login();
    	login.get();
    	contribute=homePage.Contribute();
    	contribute.get();
    	videoAddPage =contribute.VideoAddPage();
    	videoAddPage.get();
    }
    
    
    @Test(priority=2)
    public void testAddVideoFlashMessage(){
    	
    	contribute.clickContribute();
    	contribute.clickAddVideo(); 
    	
    	String testVideo="https://www.youtube.com/watch?v=h9tRIZyTXTI";
    	videoAddPage.addVideo(testVideo);
    	
    	String actualFlashMessage =videoAddPage.getFlashMessage();
    	String expectedFlashMessage ="Video page File:The Best Classical Music In The World was successfully added.";
    	
    	Assert.assertTrue(actualFlashMessage.equalsIgnoreCase(expectedFlashMessage));
    }
    
    @Test(priority=3)
    public void testClickFlashMessage(){
    	
    	String actualUrl =videoAddPage.clickFlashMessage();
    	String expectedUrl ="http://qm-homework.wikia.com/wiki/File:The_Best_Classical_Music_In_The_World";
    	Assert.assertTrue(actualUrl.equalsIgnoreCase(expectedUrl));

    }

}
