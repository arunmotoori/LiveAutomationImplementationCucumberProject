package stepdefinitions;

import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AccountSuccessPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import pages.NewsletterPage;
import pages.RegisterPage;
import pages.RightColumnOptions;
import utils.CommonUtils;

public class Register {

	WebDriver driver;
	Properties prop;
	HomePage homePage;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	MyAccountPage myAccountPage;
	NewsletterPage newsletterPage;
	LoginPage loginPage;
	RightColumnOptions rightColumnOptions;

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

}
