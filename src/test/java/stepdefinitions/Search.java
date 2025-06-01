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
import pages.FooterOptions;
import pages.HeaderOptions;
import pages.HomePage;
import pages.ProductComparisonPage;
import pages.ProductDisplayPage;
import pages.SearchPage;
import stepdefinitions.base.Base;
import utils.CommonUtils;

public class Search extends Base {

	WebDriver driver;
	Properties prop;
	String productLimitOne;

	@Given("User is on the Home page")
	public void user_is_on_the_home_page() {
		driver = DriverFactory.getDriver();
		prop = CommonUtils.loadPropertiesFile();
		homePage = new HomePage(driver);
	}

	@When("User enters existing product into the Search box field")
	public void user_enters_existing_product_into_the_search_box_field() {
		headerOptions = new HeaderOptions(homePage.getDriver());
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("existingProduct"));
	}

	@And("User clicks on Search button")
	public void user_clicks_on_search_button() {
		searchPage = headerOptions.clickOnSearchButton();
	}

	@Then("Product should be displayed in the search results")
	public void product_should_be_displayed_in_the_search_results() {
		Assert.assertTrue(searchPage.didWeNavigateToSearchResultsPage());
		Assert.assertTrue(searchPage.isProductDisplayedInSearchResults());
	}

	@When("User enters non existing product into the Search box field")
	public void user_enters_non_existing_product_into_the_search_box_field() {
		headerOptions = new HeaderOptions(homePage.getDriver());
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("nonExistingProduct"));
	}

	@Then("Proper message should be displayed in the search results")
	public void proper_message_should_be_displayed_in_the_search_results() {
		Assert.assertTrue(searchPage.didWeNavigateToSearchResultsPage());
		Assert.assertEquals(searchPage.getNoProductMessage(), "There is no product that matches the search criteria.");
	}

	@When("User doesnt enter any product into the Search box field")
	public void userDoesntEnterAnyProductIntoTheSearchBoxField() {
		headerOptions = new HeaderOptions(homePage.getDriver());
		headerOptions.enterProductIntoSearchBoxField("");
	}

	@And("User is loggedin to the Application")
	public void userIsLoggedInToTheApplication() {
		headerOptions = new HeaderOptions(homePage.getDriver());
		loginPage = headerOptions.navigateToLoginPage();
		myAccountPage = loginPage.loginInToApplication(prop.getProperty("existingEmail"),
				prop.getProperty("validPassword"));
		headerOptions = myAccountPage.getHeaderOptions();
	}

	@When("User enters search criteria into the Search box field")
	public void user_enters_search_criteria_into_the_search_box_field() {
		headerOptions = new HeaderOptions(homePage.getDriver());
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("existingProductTwo"));
	}

	@Then("Multiple products should be displayed in the search results")
	public void multiple_products_should_be_displayed_in_the_search_results() {
		Assert.assertTrue(searchPage.didWeNavigateToSearchResultsPage());
		Assert.assertTrue(searchPage.getProductsCount() > 0);
	}

	@Then("Search box field should have placeholder text")
	public void search_box_field_should_have_placeholder_text() {
		headerOptions = new HeaderOptions(homePage.getDriver());
		Assert.assertEquals(headerOptions.getSearchBoxFieldPlaceHolderText(), "Search");
	}

	@Then("Fields on the Search results page have placeholder text")
	public void fields_on_the_search_results_page_have_placeholder_text() {
		Assert.assertEquals(searchPage.getSearchCriteriaFieldPlaceHolderText(), "Keywords");
	}

	@And("User enters existing product into the Search Criteria field on Search results page")
	public void user_enters_existing_product_into_the_search_criteria_field_on_search_results_page() {
		searchPage.enterProductIntoSearchCriteriaField(prop.getProperty("existingProduct"));
	}

	@And("User clicks on Search button on Search Results page")
	public void user_clicks_on_search_button_on_search_results_page() {
		searchPage.clickOnSearchButton();
	}

	@And("User enters product description text into the Search Criteria field on Search results page")
	public void user_enters_product_description_text_into_the_search_criteria_field() {
		searchPage.enterTextInProductDescriptionIntoSearchCriteriaField(prop.getProperty("textInProductDescription"));
	}

	@And("User selects Search in Product Descriptions field")
	public void user_selects_search_in_product_descriptions_field() {
		searchPage.selectSearchInProductDescriptionField();
	}

	@Then("Product with searched description text should be displayed in the search results")
	public void product_with_searched_description_text_should_be_displayed_in_the_search_results() {
		Assert.assertTrue(searchPage.isProductHavingTextInItsDescriptionDisplayedInSearchResults());
	}

	@And("User enters a Product from a category into the Search criteria field")
	public void user_enters_a_product_from_a_category_into_the_search_criteria_field() {
		searchPage.enterProductIntoSearchCriteriaField(prop.getProperty("existingProductThree"));
	}

	@And("User selects correct Product category")
	public void user_selects_correct_product_category() {
		searchPage.selectOptionFromCategoryDropdownField(
				CommonUtils.convertToInteger(prop.getProperty("correctCategoryIndex")));
	}

	@Then("Product from the selected category should be displayed in the search results")
	public void product_from_the_selected_category_should_be_displayed_in_the_search_results() {
		Assert.assertTrue(searchPage.isProductFromCorrectCategoryDisplayedInSearchResults());
	}

	@And("User selects wrong Product category")
	public void user_selects_wrong_product_category() {
		searchPage.selectOptionFromCategoryDropdownField(
				CommonUtils.convertToInteger(prop.getProperty("wrongCategoryIndex")));
	}

	@And("User selects Parent category")
	public void user_selects_parent_category() {
		searchPage.selectOptionFromCategoryDropdownField(prop.getProperty("superCategory"));
	}

	@And("User select to search in sub categories")
	public void user_select_to_search_in_sub_categories() {
		searchPage.selectToSearchInSubCategories();
	}

	@And("User clicks on List option")
	public void user_clicks_on_list_option() {
		searchPage.selectListOption();
	}

	@Then("Single Product should be displayed in the List view")
	public void single_product_should_be_displayed_in_the_List_view() {
		Assert.assertTrue(searchPage.getProductsCount() == 1);
	}

	@And("All the Product options are working fine for the List View")
	public void all_the_product_options_are_working_fine() throws InterruptedException {
		searchPage.clickOnAddToCartOption();
		String expectedMessage = "Success: You have added " + prop.getProperty("existingProductThree")
				+ " to your shopping cart!";
		Assert.assertTrue(searchPage.getPageLevelSuccessMessage().contains(expectedMessage));
		refreshPage(searchPage.getDriver());
		searchPage.clickOnAddToWishListOption();
		expectedMessage = "You must login or create an account to save " + prop.getProperty("existingProductThree")
				+ " to your wish list!";
		Assert.assertTrue(searchPage.getPageLevelSuccessMessage().contains(expectedMessage));
		refreshPage(searchPage.getDriver());
		searchPage.clickOnCompareThisProductOption();
		expectedMessage = "Success: You have added " + prop.getProperty("existingProductThree")
				+ " to your product comparison!";
		Assert.assertTrue(searchPage.getPageLevelSuccessMessage().contains(expectedMessage));
		productDisplayPage = searchPage.clickOnProductOneImage();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
		productDisplayPage = searchPage.clickOnProductOneName();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
	}

	@When("User enters product into the Search box field which results in single product")
	public void user_enters_product_into_the_search_box_field() {
		headerOptions = new HeaderOptions(homePage.getDriver());
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("existingProductThree"));
	}

	@And("User clicks on Grid option")
	public void user_clicks_on_grid_option() {
		searchPage.selectGridOption();
	}

	@Then("Single Product should be displayed in the Grid view")
	public void single_product_should_be_displayed_in_the_grid_view() {
		Assert.assertTrue(searchPage.getProductsCount() == 1);
	}

	@And("All the Product options are working fine for the Grid View")
	public void all_the_product_options_are_working_fine_for_the_grid_view() {
		searchPage.clickOnAddToCartOption();
		String expectedMessage = "Success: You have added " + prop.getProperty("existingProductThree")
				+ " to your shopping cart!";
		Assert.assertTrue(searchPage.getPageLevelSuccessMessage().contains(expectedMessage));
		refreshPage(searchPage.getDriver());
		searchPage.clickOnAddToWishListOption();
		expectedMessage = "You must login or create an account to save " + prop.getProperty("existingProductThree")
				+ " to your wish list!";
		Assert.assertTrue(searchPage.getPageLevelSuccessMessage().contains(expectedMessage));
		refreshPage(searchPage.getDriver());
		searchPage.clickOnCompareThisProductOption();
		expectedMessage = "Success: You have added " + prop.getProperty("existingProductThree")
				+ " to your product comparison!";
		Assert.assertTrue(searchPage.getPageLevelSuccessMessage().contains(expectedMessage));
		productDisplayPage = searchPage.clickOnProductOneImage();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
		productDisplayPage = searchPage.clickOnProductOneName();
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
	}

	@When("User enters product into the Search box field which results in multiple products")
	public void user_enters_product_into_the_search_box_field_which_results_in_multiple_products() {
		headerOptions = new HeaderOptions(homePage.getDriver());
		headerOptions.enterProductIntoSearchBoxField(prop.getProperty("existingProductTwo"));
	}

	@Then("Multiple Products should be displayed in the List view")
	public void multiple_products_should_be_displayed_in_the_list_view() {
		searchPage.selectListOption();
		Assert.assertTrue(searchPage.getProductsCount() > 1);
	}

	@Then("Multiple Products should be displayed in the Grid view")
	public void multiple_produts_should_be_displayed_in_the_grid_view() {
		searchPage.selectGridOption();
		Assert.assertTrue(searchPage.getProductsCount() > 1);
	}

	@And("User clicks on Product Compare link")
	public void user_clicks_on_product_compare_link() {
		productComparisonPage = searchPage.selectProductCompareOption();
	}

	@Then("User should be navigated to Product Compare Page")
	public void user_should_be_navigated_to_product_compare_page() {
		Assert.assertTrue(productComparisonPage.didWeNavigateToProductComparisonPage());
	}

	@Then("Produts should get sorted properly on selecting different available sorting options")
	public void products_should_get_sorted_properly_on_selecting_different_available_sorting_options() {
		searchPage.selectSortOptionInDropdownField("Default");
		Assert.assertTrue(searchPage.didProductsGotDisplayedInAscendingOrder());
	}

	@And("User select the number of products to be displayed in search results")
	public void user_selects_the_number_of_products_to_be_displayed_in_search_results() {
		productLimitOne = "20";
		searchPage.selectOptionInShowDropdownField(productLimitOne);
	}

	@Then("Selected number of products should be displayed in search results page")
	public void selected_number_of_products_should_be_displayed_in_search_results_page() {
		Assert.assertTrue(searchPage.getProductsCount() <= Integer.parseInt(productLimitOne));
		String productLimitTwo = "25";
		searchPage.selectOptionInShowDropdownField(productLimitTwo);
		Assert.assertTrue(searchPage.getProductsCount() <= Integer.parseInt(productLimitTwo));
		String productLimitThree = "50";
		searchPage.selectOptionInShowDropdownField(productLimitThree);
		Assert.assertTrue(searchPage.getProductsCount() <= Integer.parseInt(productLimitThree));
		String productLimitFour = "75";
		searchPage.selectOptionInShowDropdownField(productLimitFour);
		Assert.assertTrue(searchPage.getProductsCount() <= Integer.parseInt(productLimitFour));
		String productLimitFive = "100";
		searchPage.selectOptionInShowDropdownField(productLimitFive);
		Assert.assertTrue(searchPage.getProductsCount() <= Integer.parseInt(productLimitFive));
	}

	@When("User navigates to different pages of the application")
	public void user_navigates_to_different_pages_of_the_application() {
		headerOptions = new HeaderOptions(homePage.getDriver());
	}

	@Then("Search box field and search button should be displayed on all pages")
	public void search_box_field_and_search_button_should_be_displayed_on_all_pages() {
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("contactUsPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("registerPageURL"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("loginPageURL"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("forgottenPasswordPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		registerPage = headerOptions.navigateToRegisterPage();
		String emailAddress = CommonUtils.generateEmailWithNanoTime();
		accountSuccessPage = registerPage.registeringAnAccount(prop.getProperty("firstName"),
				prop.getProperty("lastName"), emailAddress, prop.getProperty("telephoneNumber"),
				prop.getProperty("validPassword"));
		rightColumnOptions = accountSuccessPage.getRightColumnOptions();
		myAccountPage = rightColumnOptions.clickOnMyAccountOptionAfterLogin();
		myAccountPage.clickOnEditYourAccountInformationOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		myAccountPage.clickOnChangeYourPasswordOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		addressBookPage = myAccountPage.clickOnModifyYourAddressBoxEntriesOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		addAddressPage = addressBookPage.clickNewAddressButton();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		addressBookPage = addAddressPage.enterMandatoryFieldsAndAddAddress(prop.getProperty("firstName"),
				prop.getProperty("lastName"), prop.getProperty("textInProductDescription"), prop.getProperty("city"),
				prop.getProperty("postCode"));
		editAddressPage = addressBookPage.clickOnEditButton();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		myAccountPage = editAddressPage.clickOnAccountBreadcrumb();
		myAccountPage.clickOnModifyYourWishListOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		searchPage = headerOptions.enterProductAndClickOnSearchButton(prop.getProperty("existingProduct"));
		productDisplayPage = searchPage.clickOnProductOneName();
		shoppingCartPage = productDisplayPage.clickOnAddToCartButtonAndSelectShoppingCartOption();
		checkoutPage = shoppingCartPage.clickOnCheckoutButton();
		checkoutSuccessPage = checkoutPage.placeOrder();
		refreshAndNavigateToPage(checkoutSuccessPage.getDriver(), getBaseURL() + prop.getProperty("myAccountPage"));
		orderHistoryPage = myAccountPage.clickOnViewYourOrderHistoryOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		orderInformationPage = orderHistoryPage.selectViewOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		productReturnsPage = orderInformationPage.selectReturnOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		productReturnsPage.selectFirstReasonForReturn();
		productReturnsPage.clickOnSubmitButton();
		rightColumnOptions = productReturnsPage.getRightColumnOptions();
		rightColumnOptions.clickOnMyAccountOption();
		myAccountPage.clickOnDownloadsOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		myAccountPage.clickOnRewardPointsOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		productReturnsPage = myAccountPage.clickOnViewYourReturnRequestsOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		// Continue from here
		returnInformationPage = productReturnsPage.clickOnViewOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		returnInformationPage.clickOnAccountBreadCrumb();
		myAccountPage.clickOnYourTransactionOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		myAccountPage.clickOnRecurringPaymentsOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		affiliatePage = myAccountPage.clickOnEditYourAffiliateInformationOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		myAccountPage = affiliatePage.updateAffiliateAccount(prop.getProperty("firstName"));
		myAccountPage.clickOnCustomAffiliateTrackingCodeOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		myAccountPage.clickOnSubscribeOrUnscriberToNewsletterOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateBackInBrowser(headerOptions.getDriver());
		rightColumnOptions = myAccountPage.getRightColumnOptions();
		accountLogoutPage = rightColumnOptions.clickOnLogoutOption();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("aboutUsPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("deliveryInformationPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("privacyPolicyPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("termsAndConditionsPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("brandsPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("siteMapPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("desktopsCategoryPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("pcSubCategoryPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("macSubCategoryPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("laptopsAndNotebooksCategoryPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("macsSubCategoryPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("windowsSubCategoryPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("componentsCategoryPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("mikeAndTrackballsSubCategoryPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("monitorsSubCategoryPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("subSubCategoryPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("specialOffersPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("brandsPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("giftCertificatesPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		navigateToPage(getBaseURL() + prop.getProperty("affiliateLoginPage"), headerOptions.getDriver());
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
		searchPage = headerOptions.enterProductAndClickOnSearchButton(prop.getProperty("existingProduct"));
		productDisplayPage = searchPage.clickOnProductOneName();
		shoppingCartPage = productDisplayPage.clickOnAddToCartButtonAndSelectShoppingCartOption();
		guestCheckoutPage = shoppingCartPage.clickOnCheckoutButtonWithoutLogin();
		Assert.assertTrue(headerOptions.areSearchBoxFieldAndSearchButtonDisplayed());
	}

	@When("User navigates to sitemap page")
	public void user_navigates_to_sitemap_page() {

		footerOptions = new FooterOptions(homePage.getDriver());
		siteMapPage = footerOptions.selectSiteMapOption();

	}

	@And("User clicks on search option")
	public void user_clicks_on_search_option() {
		searchPage = siteMapPage.clickOnSearchOption();
	}

	@Then("User should get navigated to search page")
	public void user_should_be_navigated_to_search_page() {
		Assert.assertTrue(searchPage.didWeNavigateToSearchResultsPage());
	}

	@Then("Breacrumb option on the search page should work correctly")
	public void breacrumb_option_on_the_search_page_should_work_correctly() {
		searchPage = searchPage.clickOnBreadcrumb();
		Assert.assertTrue(searchPage.didWeNavigateToSearchResultsPage());
	}

	@Then("User should be able to perform all operations using keyboard keys on Search page")
	public void user_should_be_able_to_perform_all_operations_using_keyboard_keys_on_search_page() {
		actions = clickKeyboradKeyMultipleTimes(headerOptions.getDriver(), Keys.TAB, 21);
		actions = typeTextUsingActions(actions, prop.getProperty("existingProduct"));
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ARROW_DOWN, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.SPACE, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 2);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		searchPage = new SearchPage(headerOptions.getDriver());
		Assert.assertTrue(searchPage.isProductDisplayedInSearchResults());
		actions = clickKeyboradKeyMultipleTimes(getActions(driver), Keys.TAB, 21);
		actions = typeTextUsingActions(actions, prop.getProperty("textInProductDescription"));
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 3);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.SPACE, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		Assert.assertTrue(searchPage.isProductFromCorrectCategoryDisplayedInSearchResults());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 26);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		Assert.assertTrue(searchPage.isProductFromCorrectCategoryDisplayedInSearchResults());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		Assert.assertTrue(searchPage.isProductFromCorrectCategoryDisplayedInSearchResults());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		productComparisonPage = new ProductComparisonPage(searchPage.getDriver());
		Assert.assertTrue(productComparisonPage.didWeNavigateToProductComparisonPage());
		navigateBackInBrowser(productComparisonPage.getDriver());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ARROW_DOWN, 1);
		Assert.assertTrue(searchPage.isProductFromCorrectCategoryDisplayedInSearchResults());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 30);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ARROW_DOWN, 1);
		Assert.assertTrue(searchPage.isProductFromCorrectCategoryDisplayedInSearchResults());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 31);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		productDisplayPage = new ProductDisplayPage(searchPage.getDriver());
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		Assert.assertTrue(productDisplayPage.didWeNavigateToProductDisplayPage());
		navigateBackInBrowser(productDisplayPage.getDriver());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		Assert.assertTrue(productDisplayPage.IsShoppingCartOptionDisplayedOnTheSuccessMessage());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		Assert.assertTrue(productDisplayPage.IsWishListOptionDisplayedOnTheSuccessMessage());
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.TAB, 1);
		actions = clickKeyboradKeyMultipleTimes(actions, Keys.ENTER, 1);
		Assert.assertTrue(productDisplayPage.IsProductComparisonOptionDisplayedOnTheSuccessMessage());
	}

	@Then("Proper page heading, page url and page title should be displayed on the search page")
	public void proper_page_heading_page_url_and_page_title_should_be_displayed_on_the_search_page() {
		Assert.assertEquals(searchPage.getPageHeading(), "Search");
		Assert.assertEquals(getPageURL(searchPage.getDriver()), getBaseURL() + prop.getProperty("searchPage"));
		Assert.assertEquals(getPageTitle(searchPage.getDriver()), "Search");
	}

	@Then("Proper UI should be displayed for the search functionality in search page")
	public void proper_ui_should_be_displayed_for_the_search_functionality_in_search_page() {
		String browserName = prop.getProperty("browserName");
		if (browserName.equalsIgnoreCase("chrome")) {
			CommonUtils.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualSearchPageUI.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualSearchPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedSearchPageUI.png"));
		} else if (browserName.equalsIgnoreCase("firefox")) {
			CommonUtils.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxSearchPageUI.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualFirefoxSearchPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedFirefoxSearchPageUI.png"));
		} else if (browserName.equalsIgnoreCase("edge")) {
			CommonUtils.takeScreenshot(driver,
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeSearchPageUI.png");
			Assert.assertFalse(CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualEdgeSearchPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedEdgeSearchPageUI.png"));
		}
	}

}
