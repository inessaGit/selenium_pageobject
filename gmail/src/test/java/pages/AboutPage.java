package pages;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import util.CommonMethods;

public class AboutPage extends BasePage {

	private static final Logger LOGGER = Logger.getLogger(AboutPage.class);
	private WebDriver driver;
	private WebDriverWait wait;
	//handle windows change
   String baseWindowHandle ;
	private final String url ="https://www.google.com/gmail/about/";
	
	//================Locators=======================
	@FindBy(css="a.gmail-nav__nav-link.gmail-nav__nav-link__for-work")
	WebElement top_nav_for_work_link;

	@FindBy(css="a.gmail-nav__nav-link.gmail-nav__nav-link__sign-in")
    WebElement top_nav_sign_in;
	
	//@FindBy(css="gmail-nav__nav-link.gmail-nav__nav-link__create-account")
	
	@FindBy(xpath="//a[contains(text(), 'Create an account')]")
	WebElement top_nav_create_account;

	@FindBy(css ="div.gmail-nav__logo.gmail-logo")
	WebElement top_nav_logo;
	
	@FindBy(css="a.hero_home__link__desktop")
	WebElement page_body_create_account;
	
	public AboutPage(WebDriver driver){
		super(driver);
        this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String  clickForWorkLink(){
		wait = new WebDriverWait(this.driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(top_nav_for_work_link));
		top_nav_for_work_link.click();
		String actualUrl =this.driver.getCurrentUrl();
		return actualUrl;
	}
	
	public boolean isTopNavLogoDisplayed(){
		
		boolean isTopNavLogoDisplayed = false;
		wait = new WebDriverWait(this.driver,10);

		wait.until(ExpectedConditions.visibilityOf(top_nav_logo));
		isTopNavLogoDisplayed=top_nav_logo.isDisplayed();
		return isTopNavLogoDisplayed;
		
	}
	
	public String clickTopNavCreateAccount(){
		
		baseWindowHandle = driver.getWindowHandle();

		wait = new WebDriverWait(this.driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(top_nav_create_account));
		top_nav_create_account.click();
		
		wait.until(ExpectedConditions.numberOfwindowsToBe(2));
		Set<String> set = driver.getWindowHandles();
		
		System.out.println(set.size());
		set.remove(baseWindowHandle);
		System.out.println(set.size());

		String windowHandle2 = set.iterator().next();
		System.out.println("BASE Window handle:"+baseWindowHandle);
		System.out.println("Window handle 2:"+windowHandle2);
		
		driver.switchTo().window(windowHandle2);
		
		String  actualUrl =driver.getCurrentUrl();
		driver.close();
		driver.switchTo().window(baseWindowHandle);
		return actualUrl;
		
	}
	
	public String clickTopNavSignIn(){
		wait = new WebDriverWait(this.driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(top_nav_sign_in));
		top_nav_sign_in.click();
		String actualUrl =this.driver.getCurrentUrl();
		return actualUrl;
	}

    public SignInPage getSignInPage(){
		
		return new SignInPage(this.driver);
	}
	
	public ForWorkPage getForWorkPage(){
		return new ForWorkPage(this.driver);
	}
	@Override
	public void load() {
		driver.get(url);
	}

	@Override
	public void isLoaded() throws Error {
		String actualUrl =this.driver.getCurrentUrl();

		Assert.assertTrue(actualUrl.equalsIgnoreCase(url));
	}
	
}
