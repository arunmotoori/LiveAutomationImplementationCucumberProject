package factory;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
	
	static WebDriver driver;
	
	public static WebDriver openBrowserAndApplicationURL(Properties prop) {
		String browser = prop.getProperty("browserName");
		if(browser.equals("chrome")) {
			driver = new ChromeDriver();
		}else if(browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}else if(browser.equals("edge")) {
			driver = new EdgeDriver();
		}else if(browser.equals("safari")) {
			driver = new SafariDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get(prop.getProperty("appURL"));
		
		return driver;
		
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	

}
