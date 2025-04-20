package hooks;

import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.CommonUtils;

public class MyHooks {
	
	WebDriver driver;
	
	@Before
	public void setup() {
		driver = DriverFactory.openBrowserAndApplicationURL(CommonUtils.loadPropertiesFile());
	}
	
	@After
	public void teardown() {
		driver.quit();
	}

}
