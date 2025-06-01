package stepdefinitions;

import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import stepdefinitions.base.Base;
import utils.CommonUtils;

public class Logout extends Base {

	Properties prop;
	WebDriver driver;

	@Given("User is logged in")
	public void user_is_logged_in() {
		driver = DriverFactory.getDriver();
		prop = CommonUtils.loadPropertiesFile();
		homePage = new HomePage(driver);
		homePage.clickOnMyAccountDropMenu();
		loginPage = homePage.selectLoginOption();
		myAccountPage = loginPage.loginInToApplication(prop.getProperty("validEmail"),
				prop.getProperty("validPassword"));
	}

	@Given("User opens the application URL in the browser")
	public void user_opens_the_application_url_in_the_browser() {
		driver = DriverFactory.getDriver();
		prop = CommonUtils.loadPropertiesFile();
		homePage = new HomePage(driver);
	}

	@When("User clicks on My Account drop menu from home page")
	public void user_clicks_on_my_account_drop_menu_from_home_page() {
		headerOptions = homePage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
	}

	@When("User clicks on My Account drop menu")
	public void user_clicks_on_my_account_drop_menu() {
		headerOptions = myAccountPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
	}

	@And("User clicks on Logout option")
	public void user_clicks_on_logout_option() {
		accountLogoutPage = headerOptions.selectLogoutOption();
	}

	@Then("User should get logged out")
	public void user_should_get_logged_out() {
		Assert.assertTrue(accountLogoutPage.didWeNavigateToAccountLogoutPage());
		headerOptions = accountLogoutPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
		Assert.assertTrue(headerOptions.isLoginOptionUnderMyAccountDropMenuDisplayed());
		accountLogoutPage = headerOptions.getAccountLogoutPage();
		homePage = accountLogoutPage.clickOnContinueButton();
		Assert.assertEquals(getPageTitle(accountLogoutPage.getDriver()), "Your Store");
	}

	@When("User clicks on Logout option from Right Column options")
	public void user_clicks_on_logout_option_from_right_column_options() {
		rightColumnOptions = myAccountPage.getRightColumnOptions();
		accountLogoutPage = rightColumnOptions.clickOnLogoutOption();
	}

	@And("User browses back in the browser")
	public void user_browses_back_in_the_browser() {
		navigateBackInBrowser(driver);
		refreshPage(driver);
	}

	@Then("User should not get logged in")
	public void user_should_not_get_logged_in() {
		headerOptions = accountLogoutPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
		Assert.assertTrue(headerOptions.isLoginOptionUnderMyAccountDropMenuDisplayed());
	}

	@Then("Logout option should not be displayed under the dropmenu")
	public void logout_option_should_not_be_displayed_under_the_dropmenu() {
		Assert.assertFalse(headerOptions.isLogoutOptionUnderMyAccountDropMenuDisplayed());
	}

	@And("Logout option should not be displayed in Right column")
	public void logout_option_should_not_be_displayed_in_right_column() {
		registerPage = headerOptions.selectRegisterOption();
		rightColumnOptions = registerPage.getRightColumnOptions();
		Assert.assertFalse(rightColumnOptions.isLogoutOptionDisplayed());
	}

	@And("User logsin again")
	public void user_logsin_again() {
		headerOptions = accountLogoutPage.getHeaderOptions();
		headerOptions.clickOnMyAccountDropMenu();
		loginPage = headerOptions.navigateToLoginPage();
		myAccountPage = loginPage.loginInToApplication(prop.getProperty("validEmail"),
				prop.getProperty("validPassword"));
	}

	@Then("User should be able to login successfully")
	public void user_should_be_able_to_login_successfully() {
		Assert.assertTrue(myAccountPage.didWeNavigateToMyAccountPage());
	}

	@Then("Proper Heading, Title, URL and Breadcrumb should be displayed for Account Logout page")
	public void proper_heading_title_url_and_breadcrumb_should_be_displayed_for_account_logout_page() {
		Assert.assertEquals(getPageTitle(accountLogoutPage.getDriver()), "Account Logout");
		Assert.assertEquals(getPageURL(accountLogoutPage.getDriver()),
				getBaseURL() + prop.getProperty("logoutPageURL"));
		Assert.assertTrue(accountLogoutPage.didWeNavigateToAccountLogoutPage());
		Assert.assertEquals(accountLogoutPage.getPageHeading(), "Account Logout");
	}

	@Then("Proper UI for Logout option should be displayed")
	public void proper_ui_for_logout_option_should_be_displayed() {
		String browserName = prop.getProperty("browserName");
		if (browserName.equalsIgnoreCase("chrome")) {
			CommonUtils.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualLogoutOptions.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualLogoutOptions.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedLogoutOptions.png"));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			CommonUtils.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxLogoutOptions.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxLogoutOptions.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedFirefoxLogoutOptions.png"));
		} else if (browserName.equalsIgnoreCase("edge")) {
			CommonUtils.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeLogoutOptions.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeLogoutOptions.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedEdgeLogoutOptions.png"));
		}
	}

}
