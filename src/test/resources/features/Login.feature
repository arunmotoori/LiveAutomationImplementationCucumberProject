Feature: Login functionality
  
  Scenario: Verify Login with valid credentials
    Given User navigates to Login page
    When User enters valid email and valid password into the fields
    And User clicks on Login button
    Then User should get logged in successfully
    And User should be taken to My Account page
  
  Scenario: Verify Login with invalid credentials
    Given User navigates to Login page
    When User enters invalid email and invalid password into the fields
    And User clicks on Login button
    Then User should not get loggedin
    And User should get a proper warning message
   
  Scenario: Verify Login with invalid email and valid password
  	Given User navigates to Login page
  	When User enters invalid email and valid password into the fields
  	And User clicks on Login button
  	Then User should not get loggedin
    And User should get a proper warning message
  
  Scenario: Verify Login with valid email and invalid password
  	Given User navigates to Login page
  	When User enters valid email and invalid password into the fields
  	And User clicks on Login button
  	Then User should not get loggedin
    And User should get a proper warning message 

  Scenario: Verify Login without providing any credentials
  	Given User navigates to Login page
  	When User dont enter any credentials into the email and password fields
  	And User clicks on Login button
  	Then User should not get loggedin
    And User should get a proper warning message
    
 	Scenario: Verify Forgort Password functional on Login page
 		Given User opens Home page
		When User clicks on 'My Account' dropmenu from home page
		And User selects 'Login' option from my account drop menu
		Then Forgot Password option should be available on the page
	
	Scenario: Verify Loginning into the Application using keyboard keys
		Given User navigates to Login page
		When User logs into the Application using keyboard keys
		Then User should get logged in successfully
    And User should be taken to My Account page
	
	Scenario: Verify text fields in Login page have placeholder text
	 	Given User opens Home page
		When User clicks on 'My Account' dropmenu from home page
		And User selects 'Login' option from my account drop menu
		Then Email and Password fields should have placeholder text
		
	
	Scenario: Verify Logging into the Application and browser back
	  Given User navigates to Login page
	  When User Logins to the Application
	  And User clicks on browser back option
	  Then User should not get logout 
	
	Scenario: Verify the number of unsuccesfull login attempts
		Given User navigates to Login page
		When User enters invalid email and invalid password into the fields 6 times
		Then Proper warning message about number of unsuccessfull login attemps should be displayed
	
	Scenario: Verify Text entered into password field is toggled to hide its visibility
		Given User opens Home page
		When User clicks on 'My Account' dropmenu from home page
		And User selects 'Login' option from my account drop menu	
		Then Password field should be toggled to hide its visibility
	
	Scenario: Verify password entered into password field is not copyable 
		Given User navigates to Login page
		When User enters text into Password field
		And User copies the text from the Password field
		Then Password text should not copyable
	
	Scenario: Verify Password is not visible in Page Source
		Given User navigates to Login page
		When User enters random text into Password field
		Then Password text should not visible in Page Source
	
	Scenario: Verify Logging into the Application after changing password
		Given User navigates to Login page
		When User Logsinto the Application
		And User changes the Password
		And User Logout from the Application
		Then User should not be able to login with old password
		And User should be able to login with new password
		
	Scenario: Verify navigating to different pages from Login page
		Given User opens Home page
		When User clicks on 'My Account' dropmenu from home page
		And User selects 'Login' option from my account drop menu	
		Then User should be able to navigate to different pages from Login page
	
	Scenario: Verify different ways of navigating to Login page
		Given User opens Home page
		When User clicks on 'My Account' dropmenu from home page
		And User selects 'Login' option from my account drop menu	
		Then User should be able to navigate to Login page in different ways	
	
	Scenario: Verify Breadcrumb, Page Heading, Page Title and Page URL of Login page
		Given User opens Home page
		When User clicks on 'My Account' dropmenu from home page
		And User selects 'Login' option from my account drop menu
		Then Proper Breadcrumb,heading,title and url of login page should be displayed 	

	Scenario: Verify UI of login page
	 	Given User opens Home page
		When User clicks on 'My Account' dropmenu from home page
		And User selects 'Login' option from my account drop menu
		Then Proper UI of the login page should be displayed
		
		
		
		
		
		
		