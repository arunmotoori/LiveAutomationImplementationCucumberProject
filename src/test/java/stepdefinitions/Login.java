package stepdefinitions;

import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonUtils;

public class Login {
	
	WebDriver driver;
	Properties prop;
	
	@Given("User navigates to Login page")
	public void user_navigates_to_login_page() {
		driver = DriverFactory.getDriver();
		prop = CommonUtils.loadPropertiesFile();
	    driver.findElement(By.xpath("//span[text()='My Account']")).click();
	    driver.findElement(By.linkText("Login")).click();
	}

	@When("User enters valid email and valid password into the fields")
	public void user_enters_valid_email_and_valid_password_into_the_fields() {
	    driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
	    driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
	}

	@When("User clicks on Login button")
	public void user_clicks_on_login_button() {
	    driver.findElement(By.cssSelector("input[value='Login']")).click();
	}

	@Then("User should get logged in successfully")
	public void user_should_get_logged_in_successfully() {
	    Assert.assertTrue(driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Logout']")).isDisplayed());
	}
	
	@And("User should be taken to My Account page")
	public void User_should_be_taken_to_My_Account_page() {
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Account']")).isDisplayed());
	}
	
	
	@When("User enters invalid email and invalid password into the fields")
	public void user_enters_invalid_email_and_invalid_password_into_the_fields() {
		driver.findElement(By.id("input-email")).sendKeys(CommonUtils.generateEmailWithNanoTime());
	    driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("invalidPassword"));
	}

	@Then("User should not get loggedin")
	public void user_should_not_get_loggedin() {
	    Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
	}

	@Then("User should get a proper warning message")
	public void user_should_get_a_proper_warning_message() {
	    String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
	    Assert.assertEquals(expectedWarning,driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText());
	}
	
	
}
