package pageobject_test;

import pageobject.util.CommonMethods;
import pageobject.util.Constants;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageobject_framework.runner.BaseTestSuite;

public class BasePageTest extends BaseTestSuite{
	
	private WebDriver driver;
	private static final Logger LOGGER = Logger.getLogger(BasePageTest.class);
	
	@Test
	public void openBasePage(){
		this.driver = super.getFirefoxDriver();
	    CommonMethods.loadUrl(driver, Constants.getInstance().getTest_env());
	}

}
