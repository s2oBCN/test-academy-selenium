Feature: login functionality.

  Narrative:
  In order to enter into the app
  As a user
  I want to be able to login

  Scenario: login ok
    Given a default user
    When he does the login
    Then he is logged ok
