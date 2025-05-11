package stepdefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AccountSuccessPage;
import pages.HomePage;
import stepdefinitions.base.Base;
import utils.CommonUtils;

public class Register extends Base {

	WebDriver driver;
	Properties prop;
	String emailWithTimeStamp = "";
	
	@Given("User navigates to Register Account page")
	public void user_navigates_to_register_account_page() {
		driver = DriverFactory.getDriver();
		prop = CommonUtils.loadPropertiesFile();
		homePage = new HomePage(driver);
		homePage.clickOnMyAccountDropMenu();
		registerPage = homePage.selectRegisterOption();
	}

	@Given("User opens Application URL in the browser")
	public void user_opens_application_url_in_the_browser() {
		driver = DriverFactory.getDriver();
		prop = CommonUtils.loadPropertiesFile();
		homePage = new HomePage(driver);
	}

	@When("User clicks on 'My Account' dropmenu")
	public void user_clicks_on_my_account_dropmenu() {
		homePage.clickOnMyAccountDropMenu();
	}

	@When("User selects 'Register' option")
	public void user_selects_register_option() {
		registerPage = homePage.selectRegisterOption();
	}

	@Then("User should be navigated to 'Register Account' page")
	public void user_should_be_navigated_to_register_account_page() {
		Assert.assertTrue(registerPage.didWeNavigateToRegisterPage());
	}

	@When("User enters below fields")
	public void user_enters_below_fields(DataTable dataTable) {
		Map<String, String> map = dataTable.asMap();
		registerPage.enterFirstName(map.get("firstName"));
		registerPage.enterLastName(map.get("lastName"));
		registerPage.enterEmail(CommonUtils.generateEmailWithNanoTime());
		registerPage.enterTelephoneNumber(map.get("telephoneNumber"));
		registerPage.enterPassword(map.get("password"));
		registerPage.enterConfirmPassword(map.get("password"));
	}

	@And("User selects Yes option for Newsletter")
	public void User_selects_Yes_option_for_Newsletter() {
		registerPage.selectYesNewsletterOption();
	}

	@And("User selects Privacy Policy field")
	public void user_selects_privacy_policy_field() {
		registerPage.selectPrivacyPolicyField();
	}

	@And("User clicks on Continue button")
	public void user_clicks_on_continue_button() {
		accountSuccessPage = registerPage.clickOnContinueButton();
	}

	@Then("User should get logged in")
	public void user_should_get_logged_in() {
		Assert.assertTrue(accountSuccessPage.isUserLoggedIn());
	}

	@And("User should be navigated to Account Success page")
	public void user_should_be_navigated_to_account_success_page() {
		Assert.assertTrue(accountSuccessPage.didWeNavigateToAccountSuccessPage());
	}

	@And("Proper details should be displayed on the Account Success page")
	public void proper_details_should_be_displayed_on_the_account_success_page() {
		Assert.assertTrue(
				accountSuccessPage.getContentOnAccountSuccessPage().contains("Your Account Has Been Created!"));
		Assert.assertTrue(accountSuccessPage.getContentOnAccountSuccessPage()
				.contains("Congratulations! Your new account has been successfully created!"));
		Assert.assertTrue(accountSuccessPage.getContentOnAccountSuccessPage().contains(
				"You can now take advantage of member privileges to enhance your online shopping experience with us."));
		Assert.assertTrue(accountSuccessPage.getContentOnAccountSuccessPage().contains(
				"If you have ANY questions about the operation of this online shop, please e-mail the store owner."));
		Assert.assertTrue(accountSuccessPage.getContentOnAccountSuccessPage().contains(
				"A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please"));
		Assert.assertTrue(accountSuccessPage.getContentOnAccountSuccessPage().contains("contact us."));
	}

	@When("User clicks on Continue button on Account Success page")
	public void user_clicks_on_continue_button_on_account_success_page() {
		myAccountPage = accountSuccessPage.clickOnContinueButton();
	}

	@Then("User should be navigated to My Account page")
	public void user_should_be_navigated_to_my_account_page() {
		Assert.assertEquals("My Account", driver.getTitle());
	}

	@Then("Proper warning messages should be displayed on Register Account page")
	public void proper_warning_messages_should_be_displayed_on_Register_Account_page() {

		String expectedFirstNameWarning = "First Name must be between 1 and 32 characters!";
		String expectedLastNameWarning = "Last Name must be between 1 and 32 characters!";
		String expectedEmailWarning = "E-Mail Address does not appear to be valid!";
		String expectedTelephoneWarning = "Telephone must be between 3 and 32 characters!";
		String expectedPasswordWarning = "Password must be between 4 and 20 characters!";
		String expectedPrivacyPolicyWarning = "Warning: You must agree to the Privacy Policy!";

		Assert.assertEquals(registerPage.getFirstNameWarning(), expectedFirstNameWarning);
		Assert.assertEquals(registerPage.getLastNameWarning(), expectedLastNameWarning);
		Assert.assertEquals(registerPage.getEmailWarning(), expectedEmailWarning);
		Assert.assertEquals(registerPage.getTelephoneWarning(), expectedTelephoneWarning);
		Assert.assertEquals(registerPage.getPasswordWarning(), expectedPasswordWarning);
		Assert.assertEquals(registerPage.getPageLevelWarning(), expectedPrivacyPolicyWarning);

	}

	@When("User clicks on 'subscribe or unsubscribe to newsletter' option")
	public void user_clicks_on_subscribe_or_unsubscribe_to_newsletter_option() {
		newsletterPage = myAccountPage.clickOnSubscribeOrUnscriberToNewsletterOption();
	}

	@Then("Yes option in the newsletter page should be displayed as selected")
	public void yes_option_in_the_newsletter_page_should_be_displayed_as_selected() {
		Assert.assertTrue(newsletterPage.didWeNavigateToNewsletterPage());
		Assert.assertTrue(newsletterPage.isYesNewsletterOptionIsInSelectedState());
	}

	@When("User selects No option for Newsletter")
	public void user_selects_no_option_for_newsletter() {
		registerPage.selectNoNewletterOption();
	}

	@Then("No option in the newsletter page should be displayed as selected")
	public void no_option_in_the_newsletter_page_should_be_displayed_as_selected() {
		Assert.assertTrue(newsletterPage.didWeNavigateToNewsletterPage());
		Assert.assertTrue(newsletterPage.isNoNewsletterOptionIsInSelectedState());
	}

	@When("User selects 'Login' option")
	public void user_selects_login_option() {
		loginPage = homePage.selectLoginOption();
	}

	@When("Clicks on Continue button on Login page")
	public void clicks_on_continue_button_on_login_page() {
		registerPage = loginPage.clickOnContinueButton();
	}

	@When("Clicks on Register option from Right column options")
	public void clicks_on_register_option_from_right_column_options() {
		rightColumnOptions = loginPage.getRightColumnOptions();
		registerPage = rightColumnOptions.clickOnRegisterOption();
	}

	@When("User enters the below fields")
	public void user_enters_the_below_fields(DataTable dataTable) {
		Map<String, String> map = dataTable.asMap();
		registerPage.enterFirstName(map.get("firstName"));
		registerPage.enterLastName(map.get("lastName"));
		registerPage.enterEmail(CommonUtils.generateEmailWithNanoTime());
		registerPage.enterTelephoneNumber(map.get("telephoneNumber"));
		registerPage.enterPassword(map.get("password"));
		registerPage.enterConfirmPassword(map.get("confirmPassword"));
	}

	@Then("Proper warning messages regarding password mismatch should be displayed")
	public void proper_warning_messages_should_be_displayed() {
		String expectedWarning = "Password confirmation does not match password!";
		Assert.assertEquals(expectedWarning, registerPage.getPasswordConfirmationWarning());
	}

	@When("User enters below fields except email field")
	public void user_enters_below_fields_except_email_field(DataTable dataTable) {
		Map<String, String> map = dataTable.asMap();
		registerPage.enterFirstName(map.get("firstName"));
		registerPage.enterLastName(map.get("lastName"));
		registerPage.enterTelephoneNumber(map.get("telephoneNumber"));
		registerPage.enterPassword(map.get("password"));
		registerPage.enterConfirmPassword(map.get("password"));
	}

	@And("User enters an existing email address into the email field")
	public void user_enters_existing_email_address() {
		registerPage.enterEmail(prop.getProperty("existingEmail"));
	}

	@Then("Proper warning message regarding account exists with this email should be displayed")
	public void warning_message_for_existing_email_should_be_displayed() {
		String expectedWarning = "Warning: E-Mail Address is already registered!";
		Assert.assertEquals(registerPage.getPageLevelWarning(), expectedWarning);
	}

	@And("User enters an invalid email address into the email field")
	public void user_enters_invalid_email_address() {
		registerPage.enterEmail(prop.getProperty("invalidEmailOne"));
	}

	@Then("Proper warning message to provide valid email address should be displayed")
	public void warning_message_for_invalid_email_should_be_displayed() {
		String browserName = prop.getProperty("browserName");
		if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("edge")) {
			String expectedWarningMessageOne = "Please include an '@' in the email address. 'amotoori' is missing an '@'.";
			Assert.assertEquals(registerPage.getEmailValidationMessage(), expectedWarningMessageOne);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			String expectedWarningMessageOne = "Please enter an email address.";
			Assert.assertEquals(registerPage.getEmailValidationMessage(), expectedWarningMessageOne);
		}
	}

	@Then("Proper warning message to provide valid phone number should be displayed")
	public void warning_message_for_invalid_phone_should_be_displayed() {
		String expectedWarningMessage = "Telephone number entered by you is invalid!";
		boolean b = false;
		try {
			if (registerPage.getTelephoneWarning().equals(expectedWarningMessage)) {
				b = true;
			}
		} catch (NoSuchElementException e) {
			b = false;
		}
		Assert.assertTrue(b);
		Assert.assertFalse(accountSuccessPage.didWeNavigateToAccountSuccessPage());
	}

	@When("User fills all the below fields using keyboard keys")
	public void user_fills_all_fields_using_keyboard_keys(DataTable dataTable) {
		Map<String, String> map = dataTable.asMap(String.class, String.class);
		actions = clickKeyboradKeyMultipleTimes(getActions(driver), Keys.TAB, 23);
		actions = typeTextUsingActions(actions, map.get("firstName"));
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = typeTextUsingActions(actions, map.get("lastName"));
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = typeTextUsingActions(actions, CommonUtils.generateEmailWithNanoTime());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = typeTextUsingActions(actions, map.get("telephoneNumber"));
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = typeTextUsingActions(actions, map.get("password"));
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = typeTextUsingActions(actions, map.get("password"));
	}

	@And("User selects newsletter and privacy policy field using keyboard keys")
	public void user_selects_newsletter_and_privacy_policy_using_keyboard_keys() {
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ARROW_LEFT, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 2);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.SPACE, 1);
	}

	@And("User selects Continue button also using keyboard keys")
	public void user_selects_continue_using_keyboard_keys() {
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		accountSuccessPage = new AccountSuccessPage(driver);
	}

	@Then("Proper Placeholder texts should be displayed for all the text fields")
	public void verify_placeholder_texts() {
		Assert.assertEquals(registerPage.getFirstNameFieldPlaceholderText(), "First Name");
		Assert.assertEquals(registerPage.getLastNameFieldPlaceholderText(), "Last Name");
		Assert.assertEquals(registerPage.getEmailFieldPlaceholderText(), "E-Mail");
		Assert.assertEquals(registerPage.getTelephoneFieldPlaceholderText(), "Telephone");
		Assert.assertEquals(registerPage.getPasswordFieldPlaceholderText(), "Password");
		Assert.assertEquals(registerPage.getPasswordConfirmFieldPlaceholderText(), "Password Confirm");
	}

	@Then("All the mandatory fields in Register Account page should be marked with * symbol")
	public void verify_mandatory_fields_are_marked_with_asterisk() {
		String expectedContent = "\"* \"";
		String expectedColor = "rgb(255, 0, 0)";
		String fistNameLabelContent = (String) ((JavascriptExecutor) driver).executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registerPage.getFirstNameFieldLabelElement());
		Assert.assertEquals(fistNameLabelContent, expectedContent);
		String fistNameLabelColor = (String) ((JavascriptExecutor) driver).executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color')",
				registerPage.getFirstNameFieldLabelElement());
		Assert.assertEquals(fistNameLabelColor, expectedColor);
		String lastNameLabelContent = (String) ((JavascriptExecutor) driver).executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registerPage.getLastNameFieldLabelElement());
		Assert.assertEquals(lastNameLabelContent, expectedContent);
		String lastNameLabelColor = (String) ((JavascriptExecutor) driver).executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color')",
				registerPage.getLastNameFieldLabelElement());
		Assert.assertEquals(lastNameLabelColor, expectedColor);
		String emailLabelContent = (String) ((JavascriptExecutor) driver).executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registerPage.getEmailFieldLabelElement());
		Assert.assertEquals(emailLabelContent, expectedContent);
		String emailLabelColor = (String) ((JavascriptExecutor) driver).executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color')",
				registerPage.getEmailFieldLabelElement());
		Assert.assertEquals(emailLabelColor, expectedColor);
		String telephoneLabelContent = (String) ((JavascriptExecutor) driver).executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registerPage.getTelephoneFieldLabelElement());
		Assert.assertEquals(telephoneLabelContent, expectedContent);
		String telephoneLabelColor = (String) ((JavascriptExecutor) driver).executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color')",
				registerPage.getTelephoneFieldLabelElement());
		Assert.assertEquals(telephoneLabelColor, expectedColor);
		String passwordLabelContent = (String) ((JavascriptExecutor) driver).executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registerPage.getPasswordFieldLabelElement());
		Assert.assertEquals(passwordLabelContent, expectedContent);
		String passwordLabelColor = (String) ((JavascriptExecutor) driver).executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color')",
				registerPage.getPasswordFieldLabelElement());
		Assert.assertEquals(passwordLabelColor, expectedColor);
		String passwordConfirmLabelContent = (String) ((JavascriptExecutor) driver).executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registerPage.getPasswordConfirmFieldLabelElement());
		Assert.assertEquals(passwordConfirmLabelContent, expectedContent);
		String passwordConfirmLabelColor = (String) ((JavascriptExecutor) driver).executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color')",
				registerPage.getPasswordConfirmFieldLabelElement());
		Assert.assertEquals(passwordConfirmLabelColor, expectedColor);
		String privacyPolicyLabelContent = (String) ((JavascriptExecutor) driver).executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('content');",
				registerPage.getPrivacyPolicyFieldLabelElement());
		Assert.assertEquals(privacyPolicyLabelContent, expectedContent);
		String privacyPolicyLabelColor = (String) ((JavascriptExecutor) driver).executeScript(
				"return window.getComputedStyle(arguments[0],'::before').getPropertyValue('color')",
				registerPage.getPrivacyPolicyFieldLabelElement());
		Assert.assertEquals(privacyPolicyLabelColor, expectedColor);
	}

	@When("User enters only spaces into all the mandatory fields")
	public void user_enters_spaces_into_mandatory_fields() {
		registerPage.enterFirstName("     ");
		registerPage.enterLastName("     ");
		registerPage.enterEmail("     ");
		registerPage.enterTelephone("     ");
		registerPage.enterPassword("     ");
		registerPage.enterConfirmationPassword("     ");
	}

	@Then("Proper Warning messages should be displayed for these Mandatory fields")
	public void warning_messages_should_be_displayed_for_mandatory_fields() {
		String browserName = prop.getProperty("browserName");
		if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("edge")) {
			Assert.assertEquals(registerPage.getFirstNameWarning(), "First Name must be between 1 and 32 characters!");
			Assert.assertEquals(registerPage.getLastNameWarning(), "Last Name must be between 1 and 32 characters!");
			Assert.assertEquals(registerPage.getEmailWarning(), "E-Mail Address does not appear to be valid!");
			Assert.assertEquals(registerPage.getTelephoneWarning(), "Telephone does not appear to be valid!");
		} else if (browserName.equalsIgnoreCase("firefox")) {
			Assert.assertEquals(registerPage.getEmailValidationMessage(), "Please enter an email address.");
		}
	}

	@When("User enters below fields except password field")
	public void user_enters_below_fields_except_password_field(DataTable dataTable) {
		Map<String, String> map = dataTable.asMap(String.class, String.class);
		registerPage.enterFirstName(map.get("firstName"));
		registerPage.enterLastName(map.get("lastName"));
		registerPage.enterEmail(CommonUtils.generateEmailWithNanoTime());
		registerPage.enterTelephoneNumber(map.get("telephoneNumber"));
	}

	@And("^User enters (.+) which is not following password complexity standards$")
	public void user_enters_invalid_password(String password) {
		registerPage.enterPassword(password);
		registerPage.enterConfirmationPassword(password);
	}

	@Then("Proper Warning messages should be displayed about password complexity not being followed")
	public void proper_warning_messages_should_be_displayed_about_password_complexity_stardard() {
		String expectedWarning = "Enter password which follows Password Complexity Standard!";
		String actualWarning = registerPage.getPasswordWarning();
		;
		Assert.assertEquals(expectedWarning, actualWarning, "Password complexity standard is not being followed");
	}

	@Then("All the fields in the Register Account page are designed according to the Client Requirements")
	public void all_fields_are_according_to_client_requirements() {
		String expectedHeight = "34px";
		String expectedWidth = "701.25px";
		// First Name Field check
		Assert.assertEquals(registerPage.getFirstNameCSSValue("height"), expectedHeight);
		Assert.assertEquals(registerPage.getFirstNameCSSValue("width"), expectedWidth);
		String exptectedFirstNameWarning = "First Name must be between 1 and 32 characters!";
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getFirstNameWarning(), exptectedFirstNameWarning);
		registerPage.enterFirstName("a");
		registerPage.clickOnContinueButton();
		boolean firstNameWarningStatus = false;
		try {
			firstNameWarningStatus = registerPage.isFirstNameWarningMessageDisplayed();
		} catch (NoSuchElementException e) {
			firstNameWarningStatus = false;
		}
		Assert.assertFalse(firstNameWarningStatus);
		registerPage.clearFirstNameField();
		registerPage.enterFirstName("abcdeabcdeabcdeabcdeabcdeabcdeab");
		registerPage.clickOnContinueButton();
		firstNameWarningStatus = false;
		try {
			firstNameWarningStatus = registerPage.isFirstNameWarningMessageDisplayed();
		} catch (NoSuchElementException e) {
			firstNameWarningStatus = false;
		}
		Assert.assertFalse(firstNameWarningStatus);
		registerPage.clearFirstNameField();
		registerPage.enterFirstName("abcdeabcdeabcdeabcdeabcdeabcdeabc");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getFirstNameWarning(), exptectedFirstNameWarning);
		// Last Name Field check
		Assert.assertEquals(registerPage.getLastNameCSSValue("height"), expectedHeight);
		Assert.assertEquals(registerPage.getLastNameCSSValue("width"), expectedWidth);
		String exptectedLastNameWarning = "Last Name must be between 1 and 32 characters!";
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getLastNameWarning(), exptectedLastNameWarning);
		registerPage.enterLastName("a");
		registerPage.clickOnContinueButton();
		boolean lastNameWarningStatus = false;
		try {
			lastNameWarningStatus = registerPage.isLastNameWarningMessageDisplayed();
		} catch (NoSuchElementException e) {
			lastNameWarningStatus = false;
		}
		Assert.assertFalse(lastNameWarningStatus);
		registerPage.clearLastNameField();
		registerPage.enterLastName("abcdeabcdeabcdeabcdeabcdeabcdeab");
		registerPage.clickOnContinueButton();
		lastNameWarningStatus = false;
		try {
			lastNameWarningStatus = registerPage.isLastNameWarningMessageDisplayed();
		} catch (NoSuchElementException e) {
			lastNameWarningStatus = false;
		}
		Assert.assertFalse(lastNameWarningStatus);
		registerPage.clearLastNameField();
		registerPage.enterLastName("abcdeabcdeabcdeabcdeabcdeabcdeabc");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getLastNameWarning(), exptectedLastNameWarning);
		// Email Field check
		Assert.assertEquals(registerPage.getEmailCSSValue("height"), expectedHeight);
		Assert.assertEquals(registerPage.getEmailCSSValue("width"), expectedWidth);
		registerPage.enterEmail("adfdsfasdfadfdsssssafasdfasdfasdfadsfasdf@email.com");
		registerPage.clickOnContinueButton();
		boolean emailWarningStatus = false;
		try {
			emailWarningStatus = registerPage.isEmailWarningMessageDisplayed();
		} catch (NoSuchElementException e) {
			emailWarningStatus = false;
		}
		Assert.assertFalse(emailWarningStatus);
		// Telephone Field check
		Assert.assertEquals(registerPage.getTelephoneCSSValue("height"), expectedHeight);
		Assert.assertEquals(registerPage.getTelephoneCSSValue("width"), expectedWidth);
		String expectedTelephoneWarning = "Telephone must be between 3 and 32 characters!";
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getTelephoneWarning(), expectedTelephoneWarning);
		registerPage.enterTelephone("1");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getTelephoneWarning(), expectedTelephoneWarning);
		registerPage.clearTelephoneField();
		registerPage.enterTelephone("12");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getTelephoneWarning(), expectedTelephoneWarning);
		registerPage.clearTelephoneField();
		registerPage.enterTelephone("123");
		registerPage.clickOnContinueButton();
		boolean telephoneWarningStatus = false;
		try {
			telephoneWarningStatus = registerPage.isTelephoneWarningMessageDisplayed();
		} catch (NoSuchElementException e) {
			telephoneWarningStatus = false;
		}
		Assert.assertFalse(telephoneWarningStatus);
		registerPage.clearTelephoneField();
		registerPage.enterTelephone("12345678901234567890123456789012");
		registerPage.clickOnContinueButton();
		telephoneWarningStatus = false;
		try {
			telephoneWarningStatus = registerPage.isTelephoneWarningMessageDisplayed();
		} catch (NoSuchElementException e) {
			telephoneWarningStatus = false;
		}
		Assert.assertFalse(telephoneWarningStatus);
		registerPage.clearTelephoneField();
		registerPage.enterTelephone("123456789012345678901234567890123");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getTelephoneWarning(), expectedTelephoneWarning);
		// Password Field check
		Assert.assertEquals(registerPage.getPasswordCSSValue("height"), expectedHeight);
		Assert.assertEquals(registerPage.getPasswordCSSValue("width"), expectedWidth);
		String expectedPasswordWarning = "Password must be between 4 and 20 characters!";
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getPasswordWarning(), expectedPasswordWarning);
		registerPage.clearPasswordField();
		registerPage.enterPassword("1");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getPasswordWarning(), expectedPasswordWarning);
		registerPage.clearPasswordField();
		registerPage.enterPassword("12");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getPasswordWarning(), expectedPasswordWarning);
		registerPage.clearPasswordField();
		registerPage.enterPassword("123");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getPasswordWarning(), expectedPasswordWarning);
		registerPage.clearPasswordField();
		registerPage.enterPassword("1234");
		registerPage.clickOnContinueButton();
		boolean passwordWarningStatus = false;
		try {
			passwordWarningStatus = registerPage.isPasswordWarningMessageDisplayed();
		} catch (NoSuchElementException e) {
			passwordWarningStatus = false;
		}
		Assert.assertFalse(passwordWarningStatus);
		registerPage.clearPasswordField();
		registerPage.enterPassword("12345678901234567890");
		registerPage.clickOnContinueButton();
		passwordWarningStatus = false;
		try {
			passwordWarningStatus = registerPage.isPasswordWarningMessageDisplayed();
		} catch (NoSuchElementException e) {
			passwordWarningStatus = false;
		}
		Assert.assertFalse(passwordWarningStatus);
		registerPage.clearPasswordField();
		registerPage.enterPassword("123456789012345678901");
		registerPage.clickOnContinueButton();
		passwordWarningStatus = false;
		try {
			passwordWarningStatus = registerPage.isPasswordWarningMessageDisplayed();
		} catch (NoSuchElementException e) {
			passwordWarningStatus = false;
		}
		Assert.assertTrue(passwordWarningStatus);
		// Password Confirm Field check
		Assert.assertEquals(registerPage.getPasswordConfirmCSSValue("height"), expectedHeight);
		Assert.assertEquals(registerPage.getPasswordConfirmCSSValue("width"), expectedWidth);
		// Continue Button
		Assert.assertEquals(registerPage.getContinueButtonCSSValue("color"), "rgba(255, 255, 255, 1)");
		Assert.assertEquals(registerPage.getContinueButtonCSSValue("background-color"), "rgba(34, 154, 200, 1)");
		Assert.assertEquals(registerPage.getContinueButtonCSSValue("font-size"), "12px");
		headerOptions = registerPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
		headerOptions.selectRegisterOption();
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(srcScreenshot,
					new File(System.getProperty("user.dir") + "\\Screenshots\\AcutalRAPageAligment.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assert.assertFalse(CommonUtils.compareTwoScreenshots(
				System.getProperty("user.dir") + "\\Screenshots\\AcutalRAPageAligment.png",
				System.getProperty("user.dir") + "\\Screenshots\\ExpectedRAPageAligment.png"));
	}

	@When("User enters below fields with leading and trailing spaces except password fields")
	public void user_enters_fields_with_leading_and_trailing_spaces(DataTable dataTable) {
		Map<String, String> map = dataTable.asMap();
		registerPage.enterFirstName("    " + map.get("firstName") + "    ");
		registerPage.enterLastName("    " + map.get("lastName") + "    ");
		emailWithTimeStamp = CommonUtils.generateEmailWithNanoTime();
		registerPage.enterEmail("    " + emailWithTimeStamp + "    ");
		registerPage.enterTelephoneNumber("    " + map.get("telephoneNumber") + "    ");
		registerPage.enterPassword(map.get("password"));
		registerPage.enterConfirmPassword(map.get("password"));
	}

	@Then("Leading and trailing spaces entered into the fields should get trimmed")
	public void leading_and_trailing_spaces_should_get_trimmed() {
		SoftAssertions softly = new SoftAssertions();
		String browserName = prop.getProperty("browserName");
		if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("edge")) {

			myAccountInformationPage = myAccountPage.clickOnEditYourAccountInformationOption();

			softly.assertThat(myAccountInformationPage.getFirstNameDomAttribute("value"))
					.isEqualTo(prop.getProperty("firstName"));

			softly.assertThat(myAccountInformationPage.getLastNameDomAttribute("value"))
					.isEqualTo(prop.getProperty("lastName"));

			softly.assertThat(myAccountInformationPage.getEmailDomAttribute("value")).isEqualTo(emailWithTimeStamp);

			softly.assertThat(myAccountInformationPage.getTelephoneDomAttribute("value"))
					.isEqualTo(prop.getProperty("telephoneNumber"));

			softly.assertAll();

		} else if (browserName.equalsIgnoreCase("firefox")) {
			String expectedWarningMessageOne = "Please enter an email address.";
		    // Hard assertion for a single value check
		    assertThat(myAccountInformationPage.getEmailDomProperty("validationMessage"))
		          .isEqualTo(expectedWarningMessageOne);
		}
	}
	
	@Then("Privacy Policy field should not be selected by default")
	public void privacy_policy_field_should_not_be_selected_by_default() {
		Assert.assertFalse(registerPage.isPrivacyPolicyFieldSelected());
	}
	
	@Then("Proper warning messages to select the Privacy Policy field should be displayed")
	public void warning_message_for_privacy_policy_should_be_displayed() {
		Assert.assertEquals(registerPage.getPageLevelWarning(), "Warning: You must agree to the Privacy Policy!");
	}
	
	@Then("Password text in password fields is toggled to hide its visibility")
	public void password_fields_should_hide_text() {
		Assert.assertEquals(registerPage.getPasswordFieldDomAttribute("type"), "password");
		Assert.assertEquals(registerPage.getPasswordConfirmFieldDomAttribute("type"), "password");
	}
	
	@Then("User should be able to navigate to other pages from Register Account page")
	public void user_can_navigate_to_other_pages_from_register_account_page() {
		headerOptions = registerPage.getHeaderOptions();
		contactUsPage = headerOptions.selectPhoneIconOption();
		Assert.assertTrue(getPageTitle(contactUsPage.getDriver()).equals("Contact Us"));
		navigateBackInBrowser(contactUsPage.getDriver());

		loginPage = headerOptions.selectHeartIconOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = headerOptions.selectWishListOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		shoppingCartPage = headerOptions.selectShoppingCartIcon();
		Assert.assertEquals(getPageTitle(shoppingCartPage.getDriver()), "Shopping Cart");
		navigateBackInBrowser(shoppingCartPage.getDriver());

		shoppingCartPage = headerOptions.selectShoppingCartOption();
		Assert.assertEquals(getPageTitle(shoppingCartPage.getDriver()), "Shopping Cart");
		navigateBackInBrowser(shoppingCartPage.getDriver());

		shoppingCartPage = headerOptions.selectCheckoutIcon();
		Assert.assertEquals(getPageTitle(shoppingCartPage.getDriver()), "Shopping Cart");
		navigateBackInBrowser(shoppingCartPage.getDriver());

		shoppingCartPage = headerOptions.selectCheckoutOption();
		Assert.assertEquals(getPageTitle(shoppingCartPage.getDriver()), "Shopping Cart");
		navigateBackInBrowser(shoppingCartPage.getDriver());

		homePage = headerOptions.selectLogo();
		Assert.assertEquals(getPageTitle(homePage.getDriver()), "Your Store");
		navigateBackInBrowser(homePage.getDriver());

		searchPage = headerOptions.clickOnSearchButton();
		Assert.assertEquals(getPageTitle(searchPage.getDriver()), "Search");
		navigateBackInBrowser(searchPage.getDriver());

		homePage = headerOptions.selectHomeBreadcrumbOption();
		Assert.assertEquals(getPageTitle(homePage.getDriver()), "Your Store");
		navigateBackInBrowser(homePage.getDriver());

		loginPage = headerOptions.selectAccountBreadcrumbOptionWithoutLogin();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		registerPage = registerPage.selectRegisterBreadcrumbOption();
		Assert.assertEquals(getPageTitle(registerPage.getDriver()), "Register Account");

		loginPage = registerPage.selectLoginPageOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		rightColumnOptions = loginPage.getRightColumnOptions();
		loginPage = rightColumnOptions.clickOnLoginOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		registerPage = rightColumnOptions.clickOnRegisterOption();
		Assert.assertEquals(getPageTitle(registerPage.getDriver()), "Register Account");

		forgottenPasswordPage = rightColumnOptions.clickOnForgottenPasswordOption();
		Assert.assertEquals(getPageTitle(forgottenPasswordPage.getDriver()), "Forgot Your Password?");
		navigateBackInBrowser(forgottenPasswordPage.getDriver());

		loginPage = rightColumnOptions.clickOnMyAccountOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightColumnOptions.clickOnAddressBookOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightColumnOptions.clickOnWishListOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightColumnOptions.clickOnOrderHistoryOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightColumnOptions.clickOnDownloadsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightColumnOptions.clickOnRecurringPaymentsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightColumnOptions.clickOnRewarPointsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightColumnOptions.clickOnReturnsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightColumnOptions.clickOnTransactionsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = rightColumnOptions.clickOnNewsletterOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		footerOptions = loginPage.getFoooterOptions();
		aboutUsPage = footerOptions.selectAboutUsOption();
		Assert.assertEquals(getPageTitle(aboutUsPage.getDriver()), "About Us");
		navigateBackInBrowser(aboutUsPage.getDriver());

		deliveryInformationPage = footerOptions.selectDeliveryInformationOption();
		Assert.assertEquals(getPageTitle(deliveryInformationPage.getDriver()), "Delivery Information");
		navigateBackInBrowser(deliveryInformationPage.getDriver());

		privacyPolicyPage = footerOptions.selectPrivacyPolicyOption();
		Assert.assertEquals(getPageTitle(privacyPolicyPage.getDriver()), "Privacy Policy");
		navigateBackInBrowser(privacyPolicyPage.getDriver());

		termsAndConditionsPage = footerOptions.selectTermsAndConditionsOption();
		Assert.assertEquals(getPageTitle(termsAndConditionsPage.getDriver()), "Terms & Conditions");
		navigateBackInBrowser(termsAndConditionsPage.getDriver());

		contactUsPage = footerOptions.selectContactUsOption();
		Assert.assertEquals(getPageTitle(contactUsPage.getDriver()), "Contact Us");
		navigateBackInBrowser(contactUsPage.getDriver());

		productReturnsPage = footerOptions.selectReturnsOption();
		Assert.assertEquals(getPageTitle(productReturnsPage.getDriver()), "Product Returns");
		navigateBackInBrowser(productReturnsPage.getDriver());

		siteMapPage = footerOptions.selectSiteMapOption();
		Assert.assertEquals(getPageTitle(siteMapPage.getDriver()), "Site Map");
		navigateBackInBrowser(siteMapPage.getDriver());

		brandPage = footerOptions.selectBrandsOption();
		Assert.assertEquals(getPageTitle(brandPage.getDriver()), "Find Your Favorite Brand");
		navigateBackInBrowser(brandPage.getDriver());

		giftCertificatePage = footerOptions.selectGiftCertificatesOption();
		Assert.assertEquals(getPageTitle(giftCertificatePage.getDriver()), "Purchase a Gift Certificate");
		navigateBackInBrowser(giftCertificatePage.getDriver());

		loginPage = footerOptions.selectAffiliateOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Affiliate Program");
		navigateBackInBrowser(loginPage.getDriver());

		specialOffersPage = footerOptions.selectSpecialsOption();
		Assert.assertEquals(getPageTitle(specialOffersPage.getDriver()), "Special Offers");
		navigateBackInBrowser(specialOffersPage.getDriver());

		loginPage = footerOptions.selectMyAccountOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = footerOptions.selectOrderHistoryOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = footerOptions.selectWishListOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());

		loginPage = footerOptions.selectNewsletterOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		navigateBackInBrowser(loginPage.getDriver());
	}
	
	@And("User enters password only into the Password but not into the password confirm field")
	public void user_enters_password_only() {
		registerPage.enterPassword(prop.getProperty("validPassword"));
	}
	
	@Then("Proper warning messages about password mismatch should be displayed")
	public void proper_warning_message_about_password_mismatch_should_be_displayed() {
		Assert.assertEquals(registerPage.getPasswordConfirmationWarning(),
				"Password confirmation does not match password!");
	}
	
	@Then("Proper breadcrumb, heading, url and title of register page should be displayed")
	public void proper_breadcrumb_heading_url_and_title_should_be_displayed() {
		Assert.assertEquals("Register Account",getPageTitle(registerPage.getDriver()));
		Assert.assertEquals(getBaseURL() + prop.getProperty("registerPageURL"),getPageURL(registerPage.getDriver()));
		Assert.assertTrue(registerPage.didWeNavigateToRegisterPage());
		Assert.assertEquals("Register Account",registerPage.getPageHeading());
	}
	
	@Then("Proper UI for Register Account page should be displayed")
	public void proper_ui_for_register_account_page_should_be_displayed() {
		String browserName = prop.getProperty("browserName");
		if (browserName.equalsIgnoreCase("chrome")) {
			CommonUtils.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualRAPageUI.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualRAPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedRAPageUI.png"));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			CommonUtils.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxRAPageUI.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxRAPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedFirefoxRAPageUI.png"));
		} else if (browserName.equalsIgnoreCase("edge")) {
			CommonUtils.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeRAPageUI.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeRAPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedEdgeRAPageUI.png"));
		}
	}

}
