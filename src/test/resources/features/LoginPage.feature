Feature: Login page feature

  @login
  Scenario: Verify forgot password page
    Given User is able to load the application
    And User lands in the application landing page having title "GspTechnologies Opencart Demo Store"
    When User navigates to the Login page having title "Account Login"
    Then User clicks on "Forgotten Password" link and verify page elements

  @login
  Scenario: Verify register new user page
    Given User is able to load the application
    And User lands in the application landing page having title "GspTechnologies Opencart Demo Store"
    When User navigates to the Register page having title "Register Account"
    Then User verifies the fields in Register Account page
    |Customer Group|
    |First Name    |
    |Last Name     |
    |E-Mail        |
    |Telephone     |
    |Password      |
    |Password Confirm|

  @login
  Scenario: Login into application
    Given User is able to load the application
    And User lands in the application landing page having title "GspTechnologies Opencart Demo Store"
    When User navigates to the Login page having title "Account Login"
    And User enters email as "bipinmnair89@gmail.com" and password as "demo-opencart"
    Then User clicks Login button and navigates to My account page with title "My Account"

