package util;

import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;

import runner.BaseTestSuite;

public class SuiteListener implements ISuiteListener {

    public WebDriver driver;

    /**
     * This method is invoked after the SuiteRunner (parent suite) has finished to run all the child suites.
     */
    public void onFinish(ISuite suite) {
        driver = (WebDriver) suite.getAttribute("driver");
        BaseTestSuite.destroyWebDrivers();
    }

	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}
}
