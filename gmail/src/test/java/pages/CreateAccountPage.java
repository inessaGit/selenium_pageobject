package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAccountPage extends BasePage {
	
	private static final Logger LOGGER = Logger.getLogger(CreateAccountPage.class);
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	private final String url ="https://accounts.google.com/SignUp";

	
	public CreateAccountPage(WebDriver driver){
		super(driver);
        this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="FirstName")
	WebElement firstNameInputField ;
	
	@FindBy(id="LastName")
	WebElement lastNameInputField ;
	
	@FindBy(id="GmailAddress")
	WebElement chooseUserName ;
	
	
}
