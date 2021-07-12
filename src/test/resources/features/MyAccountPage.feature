Feature: My Accounts page feature

  Background:
    Given User is able to load the application
    And User login with email "bipinmnair89@gmail.com" password "demo-opencart" and navigate to My Accounts page

  @accounts
  Scenario: Verify links in My Accounts section
    Given User lands in My Accounts page having title "My Account"
    When User verifies the label section header "My Account"
    Then User verifies the links in the section
    |Links|
    |Edit your account information   |
    |Change your password            |
    |Modify your address book entries|
    |Modify your wish list           |

  @accounts
  Scenario: Verify links in My Orders section
    Given User lands in My Accounts page having title "My Account"
    When User verifies the label section header "My Orders"
    Then User verifies the links in the section
    |Links|
    |View your order history        |
    |Downloads                      |
    |Your Reward Points             |
    |View your return requests      |
    |Your Transactions              |
    |Recurring payments             |

  @accounts
  Scenario: Dummy failure scenario test
    Then Dummy failure step

#  Commented below scenario outline to reduce the unnecessary login attempts during test execution
#  Scenario Outline: Verify dropdown menus in list group section
#    Given User lands in My Accounts page having title "My Account"
#    Then User verfies the dropdown menu '<Dropdown Menu Label>'
#    Examples:
#    |Dropdown Menu Label|
#    |Desktops           |
#    |Laptops & Notebooks|
#    |Components         |
#    |Tablets            |
#    |Software           |
#    |Phones & PDAs      |
#    |Cameras            |
#    |MP3 Players        |






