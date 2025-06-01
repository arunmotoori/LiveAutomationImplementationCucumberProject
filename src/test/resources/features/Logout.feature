Feature: Logout functionality

Scenario: Verify Logging out using Logout option from My Account
	Given User is logged in
	When User clicks on My Account drop menu
	And User clicks on Logout option
	Then User should get logged out

Scenario: Verify Logging out by selecting Logout from Right column
	Given User is logged in
	When User clicks on Logout option from Right Column options
	Then User should get logged out
	
Scenario: Verify browsing back after logging out
	Given User is logged in
	When User clicks on Logout option from Right Column options
	And User browses back in the browser
	Then User should not get logged in
	
Scenario: Verify Logout option is not displayed under My Account and Right column before logging in
	Given User opens the application URL in the browser
	When User clicks on My Account drop menu from home page
	Then Logout option should not be displayed under the dropmenu
	And Logout option should not be displayed in Right column

Scenario: Verify logging in immedeatly after logging out
	Given User is logged in
	When User clicks on Logout option from Right Column options
	And User logsin again
	Then User should be able to login successfully 

Scenario: Verify Heading, Title, URL and Breadcrumb of Account Logout page
	Given User is logged in
	When User clicks on Logout option from Right Column options
	Then Proper Heading, Title, URL and Breadcrumb should be displayed for Account Logout page

@arun
Scenario: Verify UI of Logout option
	Given User is logged in
	When User clicks on My Account drop menu
	Then Proper UI for Logout option should be displayed
		
	
	
	