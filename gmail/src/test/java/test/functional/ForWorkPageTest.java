package test.functional;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.AboutPage;
import pages.ForWorkPage;
import runner.BaseTestSuite;
import util.CommonMethods;
import util.Constants;

@Listeners({util.DefaultTestListener.class})
public class ForWorkPageTest extends BaseTestSuite{

	private WebDriver driver;
	private static final Logger LOGGER = Logger.getLogger(ForWorkPageTest.class);
	private ForWorkPage forWorkPage ;


	@BeforeClass
	public  void initPages(){
		this.driver = super.getFirefoxDriver();
		forWorkPage = new ForWorkPage(this.driver);
		forWorkPage.load();
	}

	@Test
	public void testPageTitle(){

		String expected = "Gmail - Free Storage and Email from Google";
		Assert.assertTrue(driver.getTitle().equalsIgnoreCase(expected));
	}


}
