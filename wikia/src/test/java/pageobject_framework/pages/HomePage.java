package pageobject_framework.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import pageobject_common.util.Constants;

public class HomePage extends LoadableComponent<HomePage> {

	private static final Logger LOGGER = Logger.getLogger(HomePage.class);
	private final Constants CONSTANTS = Constants.getInstance();
	private String homepage_url= CONSTANTS.getTest_env(); 

	private String title ="QM HomeWork Wikia";
	private WebDriver driver;

	public HomePage(WebDriver driver){

		this.driver = driver;
		PageFactory.initElements(this.driver, this);

		System.out.println ("Home page url: "+homepage_url);
		LOGGER.info("Home page url: "+ homepage_url);
	}

	@Override
	public void load(){
		this.driver.get(homepage_url);
	}

	@Override
	public void isLoaded(){
		Assert.assertTrue(this.driver.getTitle().equalsIgnoreCase(title));
		Assert.assertTrue(this.driver.getCurrentUrl().equalsIgnoreCase(homepage_url));

	}
	
	public Login login(){
		
		return new Login(this.driver);
	}
	
	public Contribute contribute(){
		return new Contribute(this.driver);
	}
}