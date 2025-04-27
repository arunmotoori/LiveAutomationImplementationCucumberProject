Feature: Register Account Functionality

Scenario: Verify Registering Account with Mandatory fields
Given User navigates to Register Account page
When User enters below fields
|firstName			|Arun				|
|lastName				|Motoori		|
|telephoneNumber|1234567890	|
|password				|12345			|
And User selects Privacy Policy field
And User clicks on Continue button
Then User should get logged in
And User should be navigated to Account Success page
And Proper details should be displayed on the Account Success page
When User clicks on Continue button on Account Success page
Then User should be navigated to My Account page

Scenario: Verify Registering Account by filling all the fields
Given User navigates to Register Account page
When User enters below fields
|firstName			|Arun				|
|lastName				|Motoori		|
|telephoneNumber|1234567890	|
|password				|12345			|
And User selects Yes option for Newsletter
And User selects Privacy Policy field
And User clicks on Continue button
Then User should get logged in
And User should be navigated to Account Success page

Scenario: Verify warning messsages on Registering Account without filling mandatory fields
Given User navigates to Register Account page
When User clicks on Continue button
Then Proper warning messages should be displayed on Register Account page

Scenario: Verify Registering Account by selecting Yes Newsletter field
Given User navigates to Register Account page
When User enters below fields
|firstName			|Arun				|
|lastName				|Motoori		|
|telephoneNumber|1234567890	|
|password				|12345			|
And User selects Yes option for Newsletter
And User selects Privacy Policy field
And User clicks on Continue button
And User clicks on Continue button on Account Success page
And User clicks on 'subscribe or unsubscribe to newsletter' option
Then Yes option in the newsletter page should be displayed as selected

Scenario: Verify Registering Account by selecting No Newsletter field
Given User navigates to Register Account page
When User enters below fields
|firstName			|Arun				|
|lastName				|Motoori		|
|telephoneNumber|1234567890	|
|password				|12345			|
And User selects No option for Newsletter
And User selects Privacy Policy field
And User clicks on Continue button
And User clicks on Continue button on Account Success page
And User clicks on 'subscribe or unsubscribe to newsletter' option
Then No option in the newsletter page should be displayed as selected

Scenario: Verify navigating to 'Register Account' page from My Account drop menu
Given User opens Application URL in the browser
When User clicks on 'My Account' dropmenu
And User selects 'Register' option
Then User should be navigated to 'Register Account' page

@arun
Scenario: Verify navigating to 'Register Account' page from Login page using button
Given User opens Application URL in the browser
When User clicks on 'My Account' dropmenu
And User selects 'Login' option
And Clicks on Continue button on Login page
Then User should be navigated to 'Register Account' page

@arun
Scenario: Verify navigating to 'Register Account' page using Right column options
Given User opens Application URL in the browser
When User clicks on 'My Account' dropmenu
And User selects 'Login' option
And Clicks on Register option from Right column options
Then User should be navigated to 'Register Account' page