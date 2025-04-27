package pages.root;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.RightColumnOptions;
import utils.ElementUtilities;

public class RootPage {
	
	WebDriver driver;
	public ElementUtilities elementUtilities;
	
	public RootPage(WebDriver driver) {
		this.driver = driver;
		elementUtilities = new ElementUtilities(driver);
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement pageLevelWaring;
	
	public String getPageLevelWarning() {
		return elementUtilities.getElementText(pageLevelWaring);
	}
	
	public RightColumnOptions getRightColumnOptions() {
		return new RightColumnOptions(driver);
	}
	
	

}
