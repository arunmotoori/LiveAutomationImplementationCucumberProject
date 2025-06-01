Feature: Search functionality

Scenario: Verify searching with existing product
	Given User is on the Home page
	When User enters existing product into the Search box field
	And User clicks on Search button
	Then Product should be displayed in the search results

Scenario: Verify searching with a non existing product
	Given User is on the Home page
	When User enters non existing product into the Search box field
	And User clicks on Search button
	Then Proper message should be displayed in the search results

Scenario: Verify searching without providing any product
	Given User is on the Home page
	When User doesnt enter any product into the Search box field
	And User clicks on Search button
	Then Proper message should be displayed in the search results

Scenario: Verify searching for a product after logging in
  Given User is on the Home page
  And User is loggedin to the Application
  When User enters existing product into the Search box field
  And User clicks on Search button
	Then Product should be displayed in the search results

Scenario: Verify searching using search criteria which results in multiple products
	Given User is on the Home page
  When User enters search criteria into the Search box field
  And User clicks on Search button
  Then Multiple products should be displayed in the search results

Scenario: Verify search box field has placeholder text
	Given User is on the Home page
	When User doesnt enter any product into the Search box field
	Then Search box field should have placeholder text
	
Scenario: Verify fields on the Search results page have placeholder text
	Given User is on the Home page
	When User doesnt enter any product into the Search box field
	And User clicks on Search button
	Then Fields on the Search results page have placeholder text
	
Scenario: Verify searching using Search Criteria field in Search results page
	Given User is on the Home page
	When User doesnt enter any product into the Search box field
	And User clicks on Search button
	And User enters existing product into the Search Criteria field on Search results page
	And User clicks on Search button on Search Results page
	Then Product should be displayed in the search results
	
Scenario: Verify searching using product description text
	Given User is on the Home page
	When User doesnt enter any product into the Search box field
	And User clicks on Search button
	And User enters product description text into the Search Criteria field on Search results page
	And User selects Search in Product Descriptions field
	And User clicks on Search button on Search Results page
	Then Product with searched description text should be displayed in the search results

Scenario: Verify searching by selecting Product Category
	Given User is on the Home page
	When User doesnt enter any product into the Search box field
	And User clicks on Search button
	And User enters a Product from a category into the Search criteria field
	And User selects correct Product category
	And User clicks on Search button on Search Results page
	Then Product from the selected category should be displayed in the search results
	
Scenario: Verify searching by selecting Wrong Product Category
	Given User is on the Home page
	When User doesnt enter any product into the Search box field
	And User clicks on Search button
	And User enters a Product from a category into the Search criteria field
	And User selects wrong Product category
	And User clicks on Search button on Search Results page
	Then Proper message should be displayed in the search results
	
Scenario: Verify searching by selecting to search using Parent Category without subcategory selection
	Given User is on the Home page
	When User doesnt enter any product into the Search box field
	And User clicks on Search button
	And User enters a Product from a category into the Search criteria field
	And User selects Parent category
	And User clicks on Search button on Search Results page
	Then Proper message should be displayed in the search results

Scenario: Verify searching by selecting to search in sub categories
	Given User is on the Home page
	When User doesnt enter any product into the Search box field
	And User clicks on Search button
	And User enters a Product from a category into the Search criteria field
	And User selects Parent category
	And User select to search in sub categories
	And User clicks on Search button on Search Results page
	Then Product from the selected category should be displayed in the search results
	
Scenario: Verify List View when only one Product is displayed in search results
	Given User is on the Home page
	When User enters product into the Search box field which results in single product
	And User clicks on Search button
	And User clicks on List option
	Then Single Product should be displayed in the List view
	And All the Product options are working fine for the List View
	
Scenario: Verify Grid View when only one Product is displayed in search results
	Given User is on the Home page
	When User enters product into the Search box field which results in single product
	And User clicks on Search button
	And User clicks on Grid option
	Then Single Product should be displayed in the Grid view
	And All the Product options are working fine for the Grid View	

Scenario: Verify List View when multiple Product are displayed in search results
	Given User is on the Home page
	When User enters product into the Search box field which results in multiple products
	And User clicks on Search button
	And User clicks on List option
	Then Multiple Products should be displayed in the List view
	
Scenario: Verify Grid View when multiple Product are displayed in search results
	Given User is on the Home page
	When User enters product into the Search box field which results in multiple products
	And User clicks on Search button
	And User clicks on Grid option
	Then Multiple Products should be displayed in the Grid view
	
Scenario: Verify navigating to Product Compare Page from Search Results Page
	Given User is on the Home page
	When User enters product into the Search box field which results in single product
	And User clicks on Search button
	And User clicks on Product Compare link
	Then User should be navigated to Product Compare Page
	
Scenario: Verify user is able to sort products in Search Results page
	Given User is on the Home page
	When User enters product into the Search box field which results in multiple products
	And User clicks on Search button
	Then Produts should get sorted properly on selecting different available sorting options
	
Scenario: Verify user is able to limit the number of produts to be displayed in search results
	Given User is on the Home page
	When User enters product into the Search box field which results in multiple products
	And User clicks on Search button
	And User select the number of products to be displayed in search results
	Then Selected number of products should be displayed in search results page
	
Scenario: Verify search box field and search button are displayed on all pages of application
	Given User is on the Home page
	When User navigates to different pages of the application
	Then Search box field and search button should be displayed on all pages

Scenario: Verify navigating to search page from sitemap page
	Given User is on the Home page
	When User navigates to sitemap page
	And User clicks on search option
	Then User should get navigated to search page
	
Scenario: Verify breadcrumb option of Search page
	Given User is on the Home page
	When User enters existing product into the Search box field
	And User clicks on Search button
	Then Breacrumb option on the search page should work correctly
	
Scenario: Verify we can use all options of Search functionality using keyboard keys
	Given User is on the Home page
	When User doesnt enter any product into the Search box field
	And User clicks on Search button
	Then User should be able to perform all operations using keyboard keys on Search page

Scenario: Verify page heading, page url and page title of the search page
	Given User is on the Home page
	When User doesnt enter any product into the Search box field
	And User clicks on Search button
	Then Proper page heading, page url and page title should be displayed on the search page

Scenario: Verify the UI of the Search page
	Given User is on the Home page
	When User doesnt enter any product into the Search box field
	And User clicks on Search button
	Then Proper UI should be displayed for the search functionality in search page






	