package stepdefinitions;

import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HeaderOptions;
import pages.HomePage;
import pages.MyAccountPage;
import pages.RightColumnOptions;
import stepdefinitions.base.Base;
import utils.CommonUtils;

public class Login extends Base {

	Properties prop;
	WebDriver driver;

	@Given("User navigates to Login page")
	public void user_navigates_to_login_page() {
		driver = DriverFactory.getDriver();
		prop = CommonUtils.loadPropertiesFile();
		homePage = new HomePage(driver);
		homePage.clickOnMyAccountDropMenu();
		loginPage = homePage.selectLoginOption();
	}

	@Given("User opens Home page")
	public void user_opens_Home_page() {
		driver = DriverFactory.getDriver();
		prop = CommonUtils.loadPropertiesFile();
		homePage = new HomePage(driver);
	}

	@When("User clicks on 'My Account' dropmenu from home page")
	public void user_clicks_on_my_account_dropmenu_from_home_page() {
		homePage.clickOnMyAccountDropMenu();
	}

	@When("User enters valid email and valid password into the fields")
	public void user_enters_valid_email_and_valid_password_into_the_fields() {
		loginPage.enterEmail(prop.getProperty("validEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
	}

	@When("User clicks on Login button")
	public void user_clicks_on_login_button() {
		myAccountPage = loginPage.clickOnLoginButton();
	}

	@Then("User should get logged in successfully")
	public void user_should_get_logged_in_successfully() {
		rightColumnOptions = new RightColumnOptions(myAccountPage.getDriver());
		rightColumnOptions.isLogoutOptionDisplayed();
	}

	@And("User should be taken to My Account page")
	public void User_should_be_taken_to_My_Account_page() {
		Assert.assertTrue(myAccountPage.didWeNavigateToMyAccountPage());
	}

	@When("User enters invalid email and invalid password into the fields")
	public void user_enters_invalid_email_and_invalid_password_into_the_fields() {
		loginPage.enterEmail(CommonUtils.generateEmailWithNanoTime());
		loginPage.enterPassword(prop.getProperty("invalidPassword"));
	}

	@Then("User should not get loggedin")
	public void user_should_not_get_loggedin() {
		Assert.assertTrue(loginPage.areWeOnLoginPage());
	}

	@Then("User should get a proper warning message")
	public void user_should_get_a_proper_warning_message() {
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getPageLevelWarning(), expectedWarning);
	}

	@When("User enters invalid email and valid password into the fields")
	public void user_enters_invalid_email_and_valid_password_into_the_fields() {
		loginPage.enterEmail(CommonUtils.generateEmailWithNanoTime());
		loginPage.enterPassword(prop.getProperty("validPassword"));
	}

	@When("User enters valid email and invalid password into the fields")
	public void user_enters_valid_email_and_invalid_password_into_the_fields() {
		loginPage.enterEmail(prop.getProperty("validEmailTwo"));
		loginPage.enterPassword(prop.getProperty("invalidPassword"));
	}

	@When("User dont enter any credentials into the email and password fields")
	public void user_does_not_enter_any_credentials_into_the_email_and_password_fields() {
		loginPage.enterEmail("");
		loginPage.enterPassword("");
	}

	@Then("Forgot Password option should be available on the page")
	public void forgot_password_option_should_be_available_on_the_page() {
		Assert.assertTrue(loginPage.IsForgottenPasswordLinkDisplayed());
	}

	@When("User selects 'Login' option from my account drop menu")
	public void user_selects_login_option_from_my_account_drop_menu() {
		loginPage = homePage.selectLoginOption();
	}

	@When("User logs into the Application using keyboard keys")
	public void user_logs_into_the_application_using_keyboard_keys() {
		actions = clickKeyboradKeyMultipleTimes(getActions(driver), Keys.TAB, 23);
		actions = typeTextUsingActions(actions, prop.getProperty("validEmail"));
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = typeTextUsingActions(actions, prop.getProperty("validPassword"));
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 2);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		myAccountPage = new MyAccountPage(homePage.getDriver());
	}

	@Then("Email and Password fields should have placeholder text")
	public void email_and_password_fields_should_have_placeholder_text() {
		Assert.assertEquals(loginPage.getEmailFieldPlaceholderText(), "E-Mail Address");
		Assert.assertEquals(loginPage.getPasswordFieldPlaceholderText(), "Password");
	}

	@When("User Logins to the Application")
	public void user_logins_to_the_application() {
		myAccountPage = loginPage.loginInToApplication(prop.getProperty("validEmail"),
				prop.getProperty("validPassword"));
	}

	@And("User clicks on browser back option")
	public void user_clicks_on_browser_back_option() {
		navigateBackInBrowser(myAccountPage.getDriver());
	}

	@Then("User should not get logout")
	public void user_should_not_get_logout() {
		refreshPage(myAccountPage.getDriver());
		Assert.assertTrue(myAccountPage.didWeNavigateToMyAccountPage());
	}

	@When("User enters invalid email and invalid password into the fields 6 times")
	public void user_enters_invalid_email_and_invalid_password_6_times() {
		String invalidEmail = CommonUtils.generateEmailWithNanoTime();
		loginPage.loginInToApplication(invalidEmail, prop.getProperty("invalidPassword"));
		loginPage.loginInToApplication(invalidEmail, prop.getProperty("invalidPassword"));
		loginPage.loginInToApplication(invalidEmail, prop.getProperty("invalidPassword"));
		loginPage.loginInToApplication(invalidEmail, prop.getProperty("invalidPassword"));
		loginPage.loginInToApplication(invalidEmail, prop.getProperty("invalidPassword"));
		loginPage.loginInToApplication(invalidEmail, prop.getProperty("invalidPassword"));
	}

	@Then("Proper warning message about number of unsuccessfull login attemps should be displayed")
	public void warning_message_for_unsuccessful_login_attempts_should_be_displayed() {
		String expectedWarning = "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
		Assert.assertEquals(loginPage.getPageLevelWarning(), expectedWarning);
	}

	@Then("Password field should be toggled to hide its visibility")
	public void password_field_should_be_hidden() {
		Assert.assertEquals(loginPage.getPasswordFieldDomAttribute("type"), "password");
	}

	@When("User enters text into Password field")
	public void user_enters_text_into_password_field() {
		loginPage.enterPassword(prop.getProperty("validPassword"));
	}

	@And("User copies the text from the Password field")
	public void user_copies_text_from_password_field() {
		loginPage.copyPasswordFromPasswordField();
	}

	@Then("Password text should not copyable")
	public void password_text_should_not_be_copyable() {
		loginPage.pasteCopiedTextIntoEmailField();
		loginPage.clickOnLoginButton();
		Assert.assertNotEquals(loginPage.getPastedTextFromEmailField(), prop.getProperty("validPassword"));
	}

	@Then("Password text should not visible in Page Source")
	public void password_text_should_not_be_visible_in_page_source() {
		Assert.assertFalse(getPageSourceCode(loginPage.getDriver()).contains(prop.getProperty("randomPassword")));
		loginPage.clickOnLoginButton();
		Assert.assertFalse(getPageSourceCode(loginPage.getDriver()).contains(prop.getProperty("randomPassword")));
	}

	@When("User enters random text into Password field")
	public void user_enters_random_text_into_password_field() {
		loginPage.enterPassword(prop.getProperty("randomPassword"));
	}

	@And("User changes the Password")
	public void user_changes_the_password() {
		changePasswordPage = myAccountPage.clickOnChangeYourPasswordOption();
		changePasswordPage.enterNewPasswordIntoPasswordField(prop.getProperty("validPasswordThree"));
		changePasswordPage.enterNewPasswordIntoPasswordConfirmField(prop.getProperty("validPasswordThree"));
		myAccountPage = changePasswordPage.clickOnContinueButton();
	}

	@And("User Logout from the Application")
	public void user_logout_from_the_application() {
		rightColumnOptions = myAccountPage.getRightColumnOptions();
		accountLogoutPage = rightColumnOptions.clickOnLogoutOption();
		homePage = accountLogoutPage.clickOnContinueButton();
	}

	@Then("User should not be able to login with old password")
	public void user_should_not_be_able_to_login_with_old_password() {
		headerOptions = new HeaderOptions(homePage.getDriver());
		loginPage = headerOptions.navigateToLoginPage();
		loginPage.loginInToApplication(prop.getProperty("existingEmailTwo"), prop.getProperty("validPasswordTwo"));
		String expectedMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getPageLevelWarning(), expectedMessage);
	}

	@And("User should be able to login with new password")
	public void user_should_be_able_to_login_with_new_password() {
		myAccountPage = loginPage.loginInToApplication(prop.getProperty("existingEmailTwo"),
				prop.getProperty("validPasswordThree"));
		Assert.assertTrue(myAccountPage.didWeNavigateToMyAccountPage());
		swapPasswords(prop);
	}

	@When("User Logsinto the Application")
	public void user_logs_into_the_application() {
		myAccountPage = loginPage.loginInToApplication(prop.getProperty("existingEmailTwo"),
				prop.getProperty("validPasswordTwo"));
	}

	@Then("User should be able to navigate to different pages from Login page")
	public void user_should_be_able_to_navigate_to_different_pages_from_login_page() {
		headerOptions = loginPage.getHeaderOptions();
		contactUsPage = headerOptions.selectPhoneIconOption();
		Assert.assertTrue(getPageTitle(contactUsPage.getDriver()).equals("Contact Us"));
		navigateBackInBrowser(contactUsPage.getDriver());

		loginPage = headerOptions.selectHeartIconOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = headerOptions.selectWishListOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

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

		loginPage = loginPage.selectLoginBreadcrumbOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		registerPage = loginPage.clickOnContinueButton();
		Assert.assertEquals(getPageTitle(registerPage.getDriver()), "Register Account");
		navigateBackInBrowser(registerPage.getDriver());

		forgottenPasswordPage = loginPage.clickOnForgottenPasswordLink();
		Assert.assertTrue(forgottenPasswordPage.didWeNavigateToForgottenPasswordPage());
		navigateBackInBrowser(forgottenPasswordPage.getDriver());

		loginPage.clickOnLoginButton();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		rightColumnOptions = loginPage.getRightColumnOptions();
		loginPage = rightColumnOptions.clickOnLoginOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		registerPage = rightColumnOptions.clickOnRegisterOption();
		Assert.assertEquals(getPageTitle(registerPage.getDriver()), "Register Account");
		navigateBackInBrowser(registerPage.getDriver());

		forgottenPasswordPage = rightColumnOptions.clickOnForgottenPasswordOption();
		Assert.assertEquals(getPageTitle(forgottenPasswordPage.getDriver()), "Forgot Your Password?");
		navigateBackInBrowser(forgottenPasswordPage.getDriver());

		loginPage = rightColumnOptions.clickOnMyAccountOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnAddressBookOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnWishListOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnOrderHistoryOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnDownloadsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnRecurringPaymentsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnRewarPointsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnReturnsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnTransactionsOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = rightColumnOptions.clickOnNewsletterOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

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

		loginPage = footerOptions.selectOrderHistoryOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = footerOptions.selectWishListOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");

		loginPage = footerOptions.selectNewsletterOption();
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
	}

	@Then("User should be able to navigate to Login page in different ways")
	public void user_should_be_able_to_navigate_to_login_page_in_different_ways() {
		Assert.assertTrue(loginPage.didWeNavigateToLogin());
		rightColumnOptions = loginPage.getRightColumnOptions();
		rightColumnOptions.clickOnLoginOption();
		Assert.assertTrue(loginPage.didWeNavigateToLogin());
		registerPage = loginPage.clickOnContinueButton();
		loginPage = registerPage.selectLoginPageOption();
		Assert.assertTrue(loginPage.didWeNavigateToLogin());
	}

	@Then("Proper Breadcrumb,heading,title and url of login page should be displayed")
	public void verify_login_page_structure_and_meta() {
		Assert.assertEquals(getPageTitle(loginPage.getDriver()), "Account Login");
		Assert.assertEquals(getPageURL(loginPage.getDriver()), getBaseURL() + prop.getProperty("loginPageURL"));
		Assert.assertTrue(loginPage.didWeNavigateToLogin());
		Assert.assertEquals(loginPage.getFirstHeading(), "New Customer");
		Assert.assertEquals(loginPage.getSecondHeading(), "Returning Customer");
	}

	@Then("Proper UI of the login page should be displayed")
	public void proper_ui_of_the_login_page_should_be_displayed() {
		String browserName = prop.getProperty("browserName");
		if (browserName.equalsIgnoreCase("chrome")) {
			CommonUtils.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualLoginPageUI.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualLoginPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedLoginPageUI.png"));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			CommonUtils.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxLogoutOptions.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxLogoutOptions.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedFirefoxLogoutOptions.png"));
		} else if (browserName.equalsIgnoreCase("edge")) {
			CommonUtils.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeLoginPageUI.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeLoginPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedEdgeLoginPageUI.png"));
		}
	}

}
