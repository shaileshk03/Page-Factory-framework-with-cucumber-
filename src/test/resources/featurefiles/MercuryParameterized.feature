Feature: User Registration in MecuryFlight site

  Background: 
    Given I've a valid set of data and access to Registration Page

  #Using POJO in DataTable
  @Registration3
  Scenario: Multiple User Registration using POJO
    When Registration page Displayed
    Then I enter valid data on registration page and check if registration is successfull
      | firstname | lastname | phone      | email         | username | password | confirmpassword |
      | Aditya    | kumar    | 7501451188 | ak@text.com   | aditya01 | test123  | test123         |
      | Rk        | Sinha    | 7589866698 | bk@rars.com   | rakesh91 | test123  | test123         |
      | Preety1   | Sharma   | 8598745805 | preety1@t.com | preety19 | test123  | test123         |
    And Close the Browser
