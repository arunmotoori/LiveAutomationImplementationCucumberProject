Feature: Verify Login functionality

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
    
   

 
