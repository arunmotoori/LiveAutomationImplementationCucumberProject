package stepdefinitions.base;

import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import pages.AboutUsPage;
import pages.AccountLogoutPage;
import pages.AccountSuccessPage;
import pages.BrandPage;
import pages.ChangePasswordPage;
import pages.ContactUsPage;
import pages.DeliveryInformationPage;
import pages.FooterOptions;
import pages.ForgottenPasswordPage;
import pages.GiftCertificatePage;
import pages.HeaderOptions;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountInformationPage;
import pages.MyAccountPage;
import pages.NewsletterPage;
import pages.PrivacyPolicyPage;
import pages.ProductReturnsPage;
import pages.RegisterPage;
import pages.RightColumnOptions;
import pages.SearchPage;
import pages.ShoppingCartPage;
import pages.SiteMapPage;
import pages.SpecialOffersPage;
import pages.TermsAndConditionsPage;
import utils.CommonUtils;

public class Base {

	public Properties prop;
	public HomePage homePage;
	public RegisterPage registerPage;
	public AccountSuccessPage accountSuccessPage;
	public MyAccountPage myAccountPage;
	public NewsletterPage newsletterPage;
	public LoginPage loginPage;
	public RightColumnOptions rightColumnOptions;
	public Actions actions;
	public HeaderOptions headerOptions;
	public MyAccountInformationPage myAccountInformationPage;
	public ContactUsPage contactUsPage;
	public ShoppingCartPage shoppingCartPage;
	public SearchPage searchPage;
	public ForgottenPasswordPage forgottenPasswordPage;
	public FooterOptions footerOptions;
	public AboutUsPage aboutUsPage;
	public DeliveryInformationPage deliveryInformationPage;
	public PrivacyPolicyPage privacyPolicyPage;
	public TermsAndConditionsPage termsAndConditionsPage;
	public ProductReturnsPage productReturnsPage;
	public SiteMapPage siteMapPage;
	public BrandPage brandPage;
	public GiftCertificatePage giftCertificatePage;
	public SpecialOffersPage specialOffersPage;
	public ChangePasswordPage changePasswordPage;
	public AccountLogoutPage accountLogoutPage;

	public Actions getActions(WebDriver driver) {
		Actions actions = new Actions(driver);
		return actions;
	}

	public Actions clickKeyboradKeyMultipleTimes(Actions actions, Keys keyName, int noOfTimes) {
		for (int i = 1; i <= noOfTimes; i++) {
			actions.sendKeys(keyName).perform();
		}
		return actions;
	}

	public Actions typeTextUsingActions(Actions actions, String text) {
		actions.sendKeys(text).perform();
		return actions;
	}

	public void navigateBackInBrowser(WebDriver driver) {
		driver.navigate().back();
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getBaseURL() {
		prop = CommonUtils.loadPropertiesFile();
		return prop.getProperty("appURL");
	}

	public String getPageURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public Properties swapPasswords(Properties prop) {
		String oldPassword = prop.getProperty("validPasswordTwo");
		String newPassword = prop.getProperty("validPasswordThree");
		prop.setProperty("validPasswordTwo", newPassword);
		prop.setProperty("validPasswordThree", oldPassword);
		prop = CommonUtils.storePropertiesFile(prop);
		return prop;
	}

}
