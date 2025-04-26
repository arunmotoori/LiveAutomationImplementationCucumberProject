package stepdefinitions;

import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AccountSuccessPage;
import pages.HomePage;
import pages.RegisterPage;
import utils.CommonUtils;

public class Register {
	
	WebDriver driver;
	Properties prop;
	HomePage homePage;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	
	@Given("User navigates to Register Account page")
	public void user_navigates_to_register_account_page() {
		driver = DriverFactory.getDriver();
		prop = CommonUtils.loadPropertiesFile();
		homePage = new HomePage(driver);
		homePage.clickOnMyAccountDropMenu();
	    registerPage = homePage.selectRegisterOption();
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
		Assert.assertTrue(accountSuccessPage.getContentOnAccountSuccessPage().contains("Your Account Has Been Created!"));
		Assert.assertTrue(accountSuccessPage.getContentOnAccountSuccessPage().contains("Congratulations! Your new account has been successfully created!"));
		Assert.assertTrue(accountSuccessPage.getContentOnAccountSuccessPage().contains("You can now take advantage of member privileges to enhance your online shopping experience with us."));
		Assert.assertTrue(accountSuccessPage.getContentOnAccountSuccessPage().contains("If you have ANY questions about the operation of this online shop, please e-mail the store owner."));
		Assert.assertTrue(accountSuccessPage.getContentOnAccountSuccessPage().contains("A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please"));
		Assert.assertTrue(accountSuccessPage.getContentOnAccountSuccessPage().contains("contact us."));
	}

	@When("User clicks on Continue button on Account Success page")
	public void user_clicks_on_continue_button_on_account_success_page() {
	    driver.findElement(By.xpath("//a[text()='Continue']")).click();
	}

	@Then("User should be navigated to My Account page")
	public void user_should_be_navigated_to_my_account_page() {
	    Assert.assertEquals("My Account",driver.getTitle());
	}

}
