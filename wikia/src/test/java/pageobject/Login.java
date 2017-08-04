package pageobject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageobject.util.CommonMethods;
import pageobject.util.Constants;

public class Login extends LoadableComponent<Login> {

	private static final Logger LOGGER = Logger.getLogger(Login.class);

	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(css="span.sign-in-label")
	WebElement signInTopNav;

	@FindBy(id="UserLoginDropdown")
	WebElement loginDropDown;

	@FindBy(id="usernameInput")
	WebElement usernameDropDown;

	@FindBy(id="passwordInput")
	WebElement passwordDropDown;
	
	@FindBy(css="input.login-button")
	WebElement loginBtnDropDown;
	
	//-----------------------Post Login -----------------------
	@FindBy(css="a.ajaxLogin.table-cell")
	WebElement loginTooltip;//username + page name
	
	@FindBy(css="div.avatar-container.logged-avatar-placeholder")
	WebElement avatar;
	
	public Login(WebDriver driver){

        this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@Override
	protected void load() {
	//	this.driver.get(homepage_url);
	}

	@Override
	protected void isLoaded() throws Error {
		//Assert.assertTrue(this.driver.getCurrentUrl().equalsIgnoreCase(homepage_url));

	}
	public boolean isLoginDropDownDisplayed(){
		Actions action = new Actions(this.driver);
		wait = new WebDriverWait(this.driver,10);
		wait.until(ExpectedConditions.visibilityOf(signInTopNav));
		action.moveToElement(signInTopNav).build().perform();
		
		CommonMethods.changeElementDisplayStyle(this.driver,loginDropDown);
		wait.until(ExpectedConditions.visibilityOf(loginDropDown));
		return loginDropDown.isDisplayed();
		
	}
	public void login(String uname, String pswd){
		Actions action = new Actions(this.driver);
		wait = new WebDriverWait(this.driver,10);
		wait.until(ExpectedConditions.visibilityOf(signInTopNav));

		action.moveToElement(signInTopNav).build().perform();
		CommonMethods.changeElementDisplayStyle(this.driver,loginDropDown);

		wait.until(ExpectedConditions.visibilityOf(loginDropDown));

		usernameDropDown.clear();
		usernameDropDown.sendKeys(uname);
		
		passwordDropDown.clear();
		passwordDropDown.sendKeys(pswd);
		
		wait.until(ExpectedConditions.elementToBeClickable(loginBtnDropDown));
		loginBtnDropDown.click();		
	}
	
	public String getTooltipText(){
		
		Actions action = new Actions(this.driver);
		wait = new WebDriverWait(this.driver,10);
		wait.until(ExpectedConditions.visibilityOf(avatar));
		action.moveToElement(avatar).build().perform();
		
		String tooltipInfo =loginTooltip.getAttribute("title");
		
		return tooltipInfo;

	}
	

}
