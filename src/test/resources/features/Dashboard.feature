Feature: Dashboard functionality
  @sprint2 @regression
  Scenario: Verify dashboard
    #Given user is navigated to HRMS application
    When user enters valid username and valid password
    And user clicks on login button
    Then user is successfully logged in
    Then user verifies the dashboard
    Then user verifies all the dashboard Tabs
    |Admin|PIM|Leave|Time|Recruitment|Performance|Dashboard|Directory|