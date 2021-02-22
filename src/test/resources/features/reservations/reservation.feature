Feature: Registry of the users

  Narrative:
  In order to record reservations
  As the host of the restaurant
  I want to be able to storage the customers reservations

  Scenario: Register customers reservations
    Given I'm in the reservations page
    When I register the following reservations:
      | name   | phone | email            | date       | number | time  | color   |
      | Aslak  | 11    | e@aslakhelle.oy  | 2017-02-17 | 1      | 12:00 | #4e2727 |
    Then I get the reservation in the reservations list
