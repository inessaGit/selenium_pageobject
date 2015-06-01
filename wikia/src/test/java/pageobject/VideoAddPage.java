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

public class VideoAddPage extends LoadableComponent<VideoAddPage>{

	private static final Logger LOGGER = Logger.getLogger(VideoAddPage.class);
	private static final String videoUrl="http://qm-homework.wikia.com/wiki/Special:WikiaVideoAdd";
	private static final String videoPageTitle = "WikiaVideoAdd - QM HomeWork Wikia";

	private WebDriver driver; 
	private WebDriverWait wait ;
	
	//==================Locators ==============================
	@FindBy(id="wpWikiaVideoAddUrl")
	WebElement videoInputField;

	@FindBy(css="div.submits > input[type='submit']")
	WebElement addVideoBtn;
	
	@FindBy(css="div.msg")
	WebElement flashMsg;
	
	@FindBy(css="div.msg > a")
	WebElement flashMsgLink;
	//=========================================================
	
	public VideoAddPage(WebDriver driver){
		
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		System.out.println ("Video Add page url: "+videoUrl);
		LOGGER.info("Video Add page url: "+ videoUrl);
	}
	@Override
	protected void load() {
		this.driver.get(videoUrl);
		
	}

	@Override
	protected void isLoaded() throws Error {
		Assert.assertTrue(this.driver.getCurrentUrl().equalsIgnoreCase(videoUrl));
	}

	
	public void addVideo(String videoUrl){
		wait = new WebDriverWait(this.driver,10);

		wait.until(ExpectedConditions.visibilityOf(videoInputField));
		videoInputField.clear();
		videoInputField.sendKeys(videoUrl);
		
		wait.until(ExpectedConditions.elementToBeClickable(addVideoBtn));
		addVideoBtn.click();
	}
	
	public String getFlashMessage(){
		wait = new WebDriverWait(this.driver,15);
		wait.until(ExpectedConditions.visibilityOf(flashMsg));
		String actualFlashMsg=flashMsg.getText();
		System.out.println("Flash message actual: "+actualFlashMsg);
		return actualFlashMsg;
	}
	
	public String clickFlashMessage(){
		
		wait = new WebDriverWait(this.driver,15);
		wait.until(ExpectedConditions.visibilityOf(flashMsg));
		flashMsgLink.click();
		
		String actualUrl=this.driver.getCurrentUrl();
		System.out.println("After click on flash message actual URL:"+actualUrl);
		return actualUrl;
	}
}
