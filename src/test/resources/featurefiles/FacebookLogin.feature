Feature: Login to facebook
  Keywords Summary : This test will verify login related scenarios to FACAEBOOK.COM

  Scenario: Login with valid credentials
    Given User navigated to FACEBOOK.COM
    When user enter Username as "USER1" and Password "PASSWORD"
    And user click on login button
    Then login should be successful
    And user want to close the browser

  Scenario: Login with invalid credentials
    Given User navigated to FACEBOOK.COM
    When user enter Username as "USER2" and Password "PASSWORD"
    And user click on login button
    Then login should not be successful
    And user want to close the browser
