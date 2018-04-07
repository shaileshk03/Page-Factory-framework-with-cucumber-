@tag
Feature: Loginfeature
  This feature deals with login functionality of application

  @tag1
  Scenario: Login application with correct username and password scenario
    Given I Navigate to login page
    When I click on singin Link
    And I enter username as "test902@gmail.com"
    And I enter password as "password"
    And I click on singin button
    Then I should see the login successful
    And I click on logout button
