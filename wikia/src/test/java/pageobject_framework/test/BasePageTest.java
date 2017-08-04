package pageobject_framework.test;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageobject_common.util.CommonMethods;
import pageobject_common.util.Constants;
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
