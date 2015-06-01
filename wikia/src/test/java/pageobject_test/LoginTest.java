package pageobject_test;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobject.BaseSuite;
import pageobject.HomePage;
import pageobject.Login;
import util.Constants;

/*
 * 1. WebDriver instance from BaseSuite created by TestNG @BeforeClass before any test methods in test class have been run
 */
public class LoginTest extends BaseSuite {

	HomePage homePage;
	Login login;
	
	String pswd = Constants.getInstance().getRp().readConfigProperties("testuser.password");
    String username = Constants.getInstance().getRp().readConfigProperties("testuser.username");
    
    @Test(priority=1)
    public  void getLoginPage(){
    	homePage = new HomePage(BaseSuite.getDriver());
    	homePage.get();
    	login = homePage.Login();
    	login.get();
    }
  
	@Test (priority=2)
	public void testLoginDispayed(){

		boolean isLoginDisplayed =login.isLoginDropDownDisplayed();
		Assert.assertTrue(isLoginDisplayed==true);
	}
	
	@Test(priority=3)
	public void testLogin(){
		
		login.login(username, pswd);
	}
	
	@Test (priority=4)
	public void testTooltipInfo(){
		String actualTooltipInfo =login.getTooltipText();
		String expectedTooltipInfo =username.replace("_", " ")+" - My Page";
		Assert.assertTrue(actualTooltipInfo.equalsIgnoreCase(expectedTooltipInfo));
	}
}
