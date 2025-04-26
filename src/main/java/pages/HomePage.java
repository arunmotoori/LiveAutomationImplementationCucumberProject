package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class HomePage extends RootPage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//span[text()='My Account']")
	WebElement myAccountDropMenu;
	
	@FindBy(linkText="Register")
	WebElement registerOption;
	
	public void clickOnMyAccountDropMenu() {
		elementUtilities.clickOnElement(myAccountDropMenu);
	}
	
	public RegisterPage selectRegisterOption() {
		elementUtilities.clickOnElement(registerOption);
		return new RegisterPage(driver);
	}

}
