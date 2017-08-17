package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForWorkPage extends BasePage{

	private static final Logger LOGGER = Logger.getLogger(ForWorkPage.class);
	private WebDriver driver;
	private WebDriverWait wait;
	
	private final String url ="";
	public ForWorkPage(WebDriver driver) {
		super(driver);
        this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
