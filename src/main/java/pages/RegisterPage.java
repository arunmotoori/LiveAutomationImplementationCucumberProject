package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pages.root.RootPage;

public class RegisterPage extends RootPage {
	
	WebDriver driver;

	public RegisterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="input-firstname")
	WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	WebElement lastNameField;
	
	@FindBy(id="input-email")
	WebElement emailField;
	
	@FindBy(id="input-telephone")
	WebElement telephoneField;
	
	@FindBy(id="input-password")
	WebElement passwordField;
	
	@FindBy(id="input-confirm")
	WebElement confirmPasswordField;
	
	@FindBy(css="input[name='newsletter'][value='1']")
	WebElement yesNewsletterOption;
	
	@FindBy(name="agree")
	WebElement privacyPolicyField;
	
	@FindBy(css="input[value='Continue']")
	WebElement continueButton;
	
	public AccountSuccessPage clickOnContinueButton() {
		elementUtilities.clickOnElement(continueButton);
		return new AccountSuccessPage(driver);
	}
	
	public void selectPrivacyPolicyField() {
		elementUtilities.clickOnElement(privacyPolicyField);
	}
	
	public void selectYesNewsletterOption() {
		elementUtilities.clickOnElement(yesNewsletterOption);
	}

	public void enterConfirmPassword(String passwordText) {
		elementUtilities.enterTextIntoElement(confirmPasswordField, passwordText);
	}
	
	public void enterPassword(String passwordText) {
		elementUtilities.enterTextIntoElement(passwordField, passwordText);
	}
	
	public void enterTelephoneNumber(String telephoneText) {
		elementUtilities.enterTextIntoElement(telephoneField, telephoneText);
	}
	
	public void enterFirstName(String firstNameText) {
		elementUtilities.enterTextIntoElement(firstNameField, firstNameText);
	}
	
	public void enterLastName(String lastNameText) {
		elementUtilities.enterTextIntoElement(lastNameField, lastNameText);
	}
	
	public void enterEmail(String emailText) {
		elementUtilities.enterTextIntoElement(emailField, emailText);
	}

}
