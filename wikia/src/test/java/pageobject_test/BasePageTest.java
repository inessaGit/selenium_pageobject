package pageobject_test;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageobject.BasePage;
import pageobject.BaseSuite;

public class BasePageTest extends BaseSuite{

	private static final Logger LOGGER = Logger.getLogger(BasePageTest.class);
	BasePage basePage;
	
	@Test
	public void openBasePage(){
		basePage = new BasePage(BaseSuite.getDriver());
		basePage.get(); //returns BasePage and ensures that redirect to home page happened
	}

}
