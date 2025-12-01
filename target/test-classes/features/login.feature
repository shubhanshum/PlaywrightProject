Feature: Login into Para bank

  Scenario: Login into para bank with valid credentials
    Given user is on landing page
    When user enters username
    And user enters password
    And user clicks on login button
    Then user can see total balance
    
