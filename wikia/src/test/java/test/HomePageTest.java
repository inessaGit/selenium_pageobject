package test;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import pages.BasePage;
import pages.HomePage;
import runner.BaseTestSuite;

public class HomePageTest extends BaseTestSuite{

	private static final Logger LOGGER = Logger.getLogger(HomePageTest.class);
	private WebDriver driver;
	HomePage homePage;
	
	@Test
	public void openHomePage(){
		this.driver = super.getFirefoxDriver();
		homePage = new HomePage(this.driver);
		homePage.get(); //returns BasePage and ensures that redirect to home page happened
	}


}
