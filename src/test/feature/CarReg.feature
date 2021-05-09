Feature: Validate the carCheck Results.
  As a user I should be able to compare data from input file to the output file.

  @cartaxchk
  Scenario: Read Data and compare.

    Given I am on Home Page
    When I read data from the input txt file and compare to the output file.
    Then I should display appropriate results