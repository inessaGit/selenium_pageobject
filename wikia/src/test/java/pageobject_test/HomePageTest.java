package pageobject_test;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import pageobject.BasePage;
import pageobject.BaseSuite;
import pageobject.HomePage;

public class HomePageTest extends BaseSuite{

	private static final Logger LOGGER = Logger.getLogger(BasePageTest.class);
	HomePage homePage;
	
	@Test
	public void openBasePage(){
		homePage = new HomePage(BaseSuite.getDriver());
		homePage.get(); //returns BasePage and ensures that redirect to home page happened
	}


}
