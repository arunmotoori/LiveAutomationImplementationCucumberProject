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

Scenario: Verify navigating to 'Register Account' page from Login page using button
Given User opens Application URL in the browser
When User clicks on 'My Account' dropmenu
And User selects 'Login' option
And Clicks on Continue button on Login page
Then User should be navigated to 'Register Account' page

Scenario: Verify navigating to 'Register Account' page using Right column options
Given User opens Application URL in the browser
When User clicks on 'My Account' dropmenu
And User selects 'Login' option
And Clicks on Register option from Right column options
Then User should be navigated to 'Register Account' page

Scenario: Verify registering account by providing mismatching passwords
Given User opens Application URL in the browser
And User navigates to Register Account page
When User enters the below fields
|firstName			|Arun				|
|lastName				|Motoori		|
|telephoneNumber|1234567890	|
|password				|12345			|
|confirmPassword|67890			|
And User selects Privacy Policy field
And User clicks on Continue button
Then Proper warning messages regarding password mismatch should be displayed

Scenario: Verify registering account by providing existing account details
Given User navigates to Register Account page
When User enters below fields except email field
|firstName			|Arun				|
|lastName				|Motoori		|
|telephoneNumber|1234567890	|
|password				|12345			|
And User enters an existing email address into the email field
And User selects Privacy Policy field
And User clicks on Continue button
Then Proper warning message regarding account exists with this email should be displayed

Scenario: Verify registering account using invalid email address
Given User navigates to Register Account page
When User enters below fields except email field
|firstName			|Arun				|
|lastName				|Motoori		|
|telephoneNumber|1234567890	|
|password				|12345			|
And User enters an invalid email address into the email field
And User selects Privacy Policy field
And User clicks on Continue button
Then Proper warning message to provide valid email address should be displayed


Scenario: Verify registering account using invalid phone number
Given User navigates to Register Account page
When User enters below fields
|firstName			|Arun				|
|lastName				|Motoori		|
|telephoneNumber|abcde			|
|password				|12345			|
And User selects Privacy Policy field
And User clicks on Continue button
Then Proper warning message to provide valid phone number should be displayed

Scenario: Verify registerig account using keyboard keys
Given User navigates to Register Account page
When User fills all the below fields using keyboard keys
|firstName			|Arun				|
|lastName				|Motoori		|
|telephoneNumber|1234567890	|
|password				|12345			|
And User selects newsletter and privacy policy field using keyboard keys
And User selects Continue button also using keyboard keys
Then User should get logged in
And User should be navigated to Account Success page 

Scenario: Verify all the fields in the Register Account page have proper placeholders
Given User opens Application URL in the browser
When User navigates to Register Account page
Then Proper Placeholder texts should be displayed for all the text fields

Scenario: Verify mandatory fields on Regiter Account page are marked with * symbol
Given User opens Application URL in the browser
When User navigates to Register Account page
Then All the mandatory fields in Register Account page should be marked with * symbol


Scenario: Verify mandatory fields in Register Account page are not accepting only spaces
Given User navigates to Register Account page
When User enters only spaces into all the mandatory fields
And User selects Privacy Policy field
And User clicks on Continue button
Then Proper Warning messages should be displayed for these Mandatory fields

Scenario Outline: Verify Registering Account using passwords not following complexity standards
Given User navigates to Register Account page
When User enters below fields except password field
|firstName			|Arun				|
|lastName				|Motoori		|
|telephoneNumber|1234567890	|
And User enters <password> which is not following password complexity standards
And User selects Privacy Policy field
And User clicks on Continue button
Then Proper Warning messages should be displayed about password complexity not being followed
Examples: 
|password	|
|12345	 	|
|abcdefghi|
|abcd1234	|
|abcd123$	|
|ABCD456!	|

Scenario: Verify fields in the Register Account page are according to the Client Requirements
Given User opens Application URL in the browser
And User navigates to Register Account page
Then All the fields in the Register Account page are designed according to the Client Requirements

Scenario: Verify leading and trailing spaces entered while registering account are trimmed
Given User navigates to Register Account page
When User enters below fields with leading and trailing spaces except password fields
|firstName			|Arun				|
|lastName				|Motoori		|
|telephoneNumber|1234567890	|
|password				|12345			|
And User selects Privacy Policy field
And User clicks on Continue button
And User clicks on Continue button on Account Success page
Then Leading and trailing spaces entered into the fields should get trimmed

Scenario: Verify Privacy Policy field is not selected by default
Given User opens Application URL in the browser
When User navigates to Register Account page
Then Privacy Policy field should not be selected by default

Scenario: Verify registering account without selecting Privacy Policy field
Given User navigates to Register Account page
When User enters below fields
|firstName			|Arun				|
|lastName				|Motoori		|
|telephoneNumber|1234567890	|
|password				|12345			|
And User clicks on Continue button
Then Proper warning messages to select the Privacy Policy field should be displayed

Scenario: Verify Password entered into the passwords fields is toggled to hide its visibility
Given User opens Application URL in the browser
When User navigates to Register Account page
Then Password text in password fields is toggled to hide its visibility

Scenario: Verify navigating to other pages from Register Account page
Given User opens Application URL in the browser
When User navigates to Register Account page
Then User should be able to navigate to other pages from Register Account page

Scenario: Verify registering account by filling password field and not filling password confirm field
Given User navigates to Register Account page
When User enters below fields except password field
|firstName			|Arun				|
|lastName				|Motoori		|
|telephoneNumber|1234567890	|
And User enters password only into the Password but not into the password confirm field
And User selects Privacy Policy field
And User clicks on Continue button
Then Proper warning messages about password mismatch should be displayed

Scenario: Verify Breadcrumb, Page Heading, Page URL and Page Title of Register Account page
Given User opens Application URL in the browser
When User navigates to Register Account page
Then Proper breadcrumb, heading, url and title of register page should be displayed

Scenario: Verify UI of Register Account page
Given User opens Application URL in the browser
When User navigates to Register Account page
Then Proper UI for Register Account page should be displayed

@arun
Scenario: Verify Register Account functionality in all supported environments
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


