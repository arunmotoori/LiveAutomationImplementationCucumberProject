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