package pageobject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import util.Constants;
import util.WebDriverManager;


/*
 * this class represents a LoadableComponent that loads the BasePage page
 */
public class BasePage extends LoadableComponent<BasePage> {

	private static final Logger LOGGER = Logger.getLogger(BasePage.class);
	
	private final Constants CONSTANTS = Constants.getInstance();
	private String base_url= CONSTANTS.getBase_url(); 
	private String homepage_url= CONSTANTS.getHomepage_url(); 

	private String title ="QM HomeWork Wikia";
	private WebDriver driver;

	
	public BasePage(WebDriver driver){
		
        this.driver = driver;
		 PageFactory.initElements(this.driver, this);

		System.out.println ("Base page url:"+base_url);
		LOGGER.info("Base page  url:"+base_url);
	}
	
	@Override
	public void load(){
		this.driver.get(base_url);
	}
	
	@Override
	public void isLoaded(){
		Assert.assertTrue(this.driver.getCurrentUrl().equalsIgnoreCase(homepage_url));
	}
	

}
