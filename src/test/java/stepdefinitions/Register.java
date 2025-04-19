package stepdefinitions;

import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Register {
	
	WebDriver driver;
	
	@And("User selects Yes option for Newsletter")
	public void User_selects_Yes_option_for_Newsletter() {
		driver.findElement(By.cssSelector("input[name='newsletter'][value='1']")).click();
	}
	
	@Given("User navigates to Register Account page")
	public void user_navigates_to_register_account_page() {
		driver = DriverFactory.getDriver();
	    driver.findElement(By.xpath("//span[text()='My Account']")).click();
	    driver.findElement(By.linkText("Register")).click();
	}

	@When("User enters below fields")
	public void user_enters_below_fields(DataTable dataTable) {
	   Map<String, String> map = dataTable.asMap();
	   driver.findElement(By.id("input-firstname")).sendKeys(map.get("firstName"));
	   driver.findElement(By.id("input-lastname")).sendKeys(map.get("lastName"));
	   driver.findElement(By.id("input-email")).sendKeys(generateEmailWithNanoTime());
	   driver.findElement(By.id("input-telephone")).sendKeys(map.get("telephoneNumber"));
	   driver.findElement(By.id("input-password")).sendKeys(map.get("password"));
	   driver.findElement(By.id("input-confirm")).sendKeys(map.get("password"));
	}

	@And("User selects Privacy Policy field")
	public void user_selects_privacy_policy_field() {
	    driver.findElement(By.name("agree")).click();
	}

	@And("User clicks on Continue button")
	public void user_clicks_on_continue_button() {
	    driver.findElement(By.xpath("//input[@value='Continue']")).click();
	}

	@Then("User should get logged in")
	public void user_should_get_logged_in() {
	    Assert.assertTrue(driver.findElement(By.xpath("//a[@class='list-group-item'][text()='Logout']")).isDisplayed());
	}

	@And("User should be navigated to Account Success page")
	public void user_should_be_navigated_to_account_success_page() {
	    Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Success']")).isDisplayed());
	}

	@And("Proper details should be displayed on the Account Success page")
	public void proper_details_should_be_displayed_on_the_account_success_page() {
	    //I will implement this method in next session
	}

	@When("User clicks on Continue button on Account Success page")
	public void user_clicks_on_continue_button_on_account_success_page() {
	    driver.findElement(By.xpath("//a[text()='Continue']")).click();
	}

	@Then("User should be navigated to My Account page")
	public void user_should_be_navigated_to_my_account_page() {
	    Assert.assertEquals("My Account",driver.getTitle());
	}
	
	public static String generateEmailWithNanoTime() {
        long nanoTime = System.nanoTime();
        return "user_" + nanoTime + "@example.com";
    }

}
