Feature: Dictionary Extension

  Scenario: Open the browser extension
    Given Colin is on the login page
    When he opens the browser extension
    Then the head word should be displayed

  Scenario: Use the browser extension
    Given Colin is on the login page
    When he displays the definition of a term
    Then the dictionary definition should be displayed
