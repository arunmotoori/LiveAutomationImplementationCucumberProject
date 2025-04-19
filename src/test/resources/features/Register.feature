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

