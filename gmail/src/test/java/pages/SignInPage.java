package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SignInPage extends BasePage {

	private static final Logger LOGGER = Logger.getLogger(SignInPage.class);
	private WebDriver driver;
	private WebDriverWait wait;
	
	private final String url ="";
	
	public SignInPage(WebDriver driver) {
		super(driver);
        this.driver = driver;
		PageFactory.initElements(driver, this);	
		}
	
	@Override
	public void load(){
	    driver.get(url);
	}
	
	@Override
	public void isLoaded(){
		Assert.assertTrue(this.driver.getCurrentUrl().equalsIgnoreCase(url));
	}

}
