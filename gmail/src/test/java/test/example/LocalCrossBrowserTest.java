package test.example;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class LocalCrossBrowserTest {

	@Test
	public void testFF() {
		
		final String firefoxPath=System.getProperty("user.dir")+ "/src/test/java/config/geckodriver";

		System.setProperty("webdriver.gecko.driver", firefoxPath);	

		String urlDev ="https://www.sf-dev1.com/de-de/";
		String urlLive = "https://www.smartfrog.com/en-gb/";
		
		
		  WebDriver driver = new FirefoxDriver();
		  
		  
		    driver.get(urlLive);
		    WebElement email = driver.findElement(By.name("user"));

		    email.sendKeys("inessa.de@yopmail.com");
		    
		    WebElement password = driver.findElement(By.name("pass"));
		    password.sendKeys("nautica1");

		    WebElement loginBtn = driver.findElement(By.cssSelector("input.submit"));
		    loginBtn.click();
		     
		    WebDriverWait wait = new WebDriverWait(driver, 10);
		    //WebElement logout = driver.findElement(By.xpath("//span[contains(text(),'Logout')]"));
		    
		    wait.until(ExpectedConditions.urlContains("https://app.smartfrog.com/en-de/"));
		    //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(text(),'Logout')]"))));
		    
		    System.out.println(driver.getTitle());
		    driver.quit();
	}
	

	@Test
	public void testChrome() {
		
		final String chromePath=System.getProperty("user.dir")+ "/src/test/java/config/chromedriver2.38";

		System.setProperty("webdriver.chrome.driver", chromePath);	

		String urlDev ="https://www.sf-dev1.com/de-de/";
		String urlLive = "https://www.smartfrog.com/en-gb/";
		
		  
	     WebDriver driver = new ChromeDriver(); 
		  
		    driver.get(urlLive);
		    WebElement email = driver.findElement(By.name("user"));

		    email.sendKeys("inessa.de@yopmail.com");
		    
		    WebElement password = driver.findElement(By.name("pass"));
		    password.sendKeys("nautica1");

		    WebElement loginBtn = driver.findElement(By.cssSelector("input.submit"));
		    loginBtn.click();
		     
		    WebDriverWait wait = new WebDriverWait(driver, 10);
		    //WebElement logout = driver.findElement(By.xpath("//span[contains(text(),'Logout')]"));
		    
		    wait.until(ExpectedConditions.urlContains("https://app.smartfrog.com/en-de/"));
		    //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(text(),'Logout')]"))));
		    
		    System.out.println(driver.getTitle());
		    driver.quit();
	}
}
