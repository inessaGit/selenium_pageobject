package pageobject_test;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobject.BaseSuite;
import pageobject.Contribute;
import pageobject.HomePage;
import pageobject.Login;
import pageobject.VideoAddPage;
import util.Constants;

public class ContributeTest extends BaseSuite{

	HomePage homePage;
	Login login;
	Contribute contribute;
	VideoAddPage videoAddPage;
	
	String pswd = Constants.getInstance().getRp().readConfigProperties("testuser.password");
    String username = Constants.getInstance().getRp().readConfigProperties("testuser.username");
    
    @Test(priority=1)
    public  void getLoginPage(){
    	homePage = new HomePage(BaseSuite.getDriver());
    	homePage.get();
    	login = homePage.Login();
    	login.get();
    	contribute=homePage.Contribute();
    	contribute.get();
    }
    
    @Test(priority=2)
    public void testContributeDropdownDisplayed(){
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
