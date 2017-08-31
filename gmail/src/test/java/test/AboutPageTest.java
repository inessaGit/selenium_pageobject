package test;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.AboutPage;
import pages.BasePage;
import runner.BaseTestSuite;
import util.CommonMethods;
import util.Constants;

public class AboutPageTest extends BaseTestSuite{

	private WebDriver driver;
	private static final Logger LOGGER = Logger.getLogger(AboutPageTest.class);
	private AboutPage aboutPage ;

	@Test
	public void testTopNavLogoDisplayed(){
		this.driver = super.getFirefoxDriver();
		aboutPage = new AboutPage(this.driver);
		aboutPage.load();
		boolean isForWorkLinkDisplayed = aboutPage.isTopNavLogoDisplayed();
		Assert.assertTrue(isForWorkLinkDisplayed==true);
	}

	@Test
	public void testclickTopNavCreateAccount(){
		this.driver = super.getFirefoxDriver();
		aboutPage = new AboutPage(this.driver);

		aboutPage.load();

		String url = aboutPage.clickTopNavCreateAccount();
		System.out.println(url);
		String expectedURL = "https://accounts.google.com/SignUp?service=mail";
		Assert.assertTrue(url.contains(expectedURL));
	}

	//clickTopNavSignIn
	@Test
	public void testclickTopNavSignIn(){
		this.driver = super.getFirefoxDriver();
		aboutPage = new AboutPage(this.driver);
		aboutPage.load();

		String url = aboutPage.clickTopNavSignIn();
		String expectedURL = "https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin";
		Assert.assertTrue(url.contains(expectedURL));
	}

	//clickForWorkLink
	@Test
	public void testclickForWorkLink(){
		this.driver = super.getFirefoxDriver();
		aboutPage = new AboutPage(this.driver);
		aboutPage.load();

		String url = aboutPage.clickForWorkLink();
		String expectedURL = "https://www.google.com/gmail/about/for-work/";
		Assert.assertTrue(url.contains(expectedURL));
	}
}
