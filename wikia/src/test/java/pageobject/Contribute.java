package pageobject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageobject.util.CommonMethods;

public class Contribute extends LoadableComponent<Contribute> {

	private static final Logger LOGGER = Logger.getLogger(Login.class);
	private WebDriver driver;
	private WebDriverWait wait;
	
	public static final String videoUrl="http://qm-homework.wikia.com/wiki/Special:WikiaVideoAdd";
	
	//================Locators=======================
	@FindBy(css="nav.wikia-menu-button.contribute.secondary.combined")
	WebElement contribute;

	@FindBy(css="ul.WikiaMenuElement")
    WebElement contributeDropdown;
	
	@FindBy(partialLinkText="Edit this Page")
	WebElement editThisPage;

	@FindBy(partialLinkText="Add a Video")
	WebElement addVideo;
	
	public Contribute(WebDriver driver){
        this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickContribute(){
		wait = new WebDriverWait(this.driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(contribute));
		contribute.click();
	}
	
	public boolean isContributeDropdownDisplayed(){
		
		boolean isContributeDisplayed = false;
		wait = new WebDriverWait(this.driver,10);
		CommonMethods.changeElementDisplayStyle(this.driver,contributeDropdown);

		wait.until(ExpectedConditions.visibilityOf(contributeDropdown));
		isContributeDisplayed=contributeDropdown.isDisplayed();
		return isContributeDisplayed;
		
	}
	
	public String clickAddVideo(){
		wait = new WebDriverWait(this.driver,15);
		wait.until(ExpectedConditions.elementToBeClickable(addVideo));
		addVideo.click();
		String actualUrl =this.driver.getCurrentUrl();
		System.out.println("Add Video button clicked:"+actualUrl);
		//this.driver.getTitle(); optional WikiaVideoAdd - QM HomeWork Wikia
		return actualUrl;
	}
	
	public VideoAddPage VideoAddPage(){
		return new VideoAddPage(this.driver);
	}
	//https://www.youtube.com/watch?v=h9tRIZyTXTI
	public void addVideoUrl(){
		
	}
	@Override
	protected void load() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void isLoaded() throws Error {
		Assert.assertTrue(contribute.isDisplayed());
	}
	
}
