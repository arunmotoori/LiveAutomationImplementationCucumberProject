package hooks;

import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class MyHooks {
	
	WebDriver driver;
	
	@Before
	public void setup() {
		driver = DriverFactory.openBrowserAndApplicationURL();
	}
	
	@After
	public void teardown() {
		driver.quit();
	}

}
