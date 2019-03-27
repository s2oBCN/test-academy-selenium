 
# Gherkin - best practices & tips

PLEASE Read THeSE LINKS:

* https://automationpanda.com/2018/01/31/good-gherkin-scenario-titles/
* https://automationpanda.com/2017/01/30/bdd-101-writing-good-gherkin/
* http://itsadeliverything.com/declarative-vs-imperative-gherkin-scenarios-for-cucumber
* http://aslakhellesoy.com/post/11055981222/the-training-wheels-came-off

The intent of the Gherkin syntax is to express business requirements in the language of the business.

## Scenario definition
* Write a header with "Narrative:" and the TRQ that we are trying to test
* Review the User Story validate that it meets the criteria and best practices:
  * INVEST criteria
  * Uses Ubiquitous Language (UL) consistent with other stories
* Define steps from a functional point of view that have a good level of re-use. Gherkin is meant to be expressive.
* Use datatables and examples when possible. 
  * If needed a transposed table. 
  * Keep in mind the readability of the scenario
* In a scenario, remove the data that is not strictly necessary for the definition of the test.
  * ie. we can say "Given an existing lot" instead of "Given a lot with id <lot_id> and...." 
  * This makes the stories cleaner and more readable
* Use the pattern "Data builder" whenever possible.
* Look out for User Story smells:
  * CRUD operations or anemic action - is the business case is not being captured, or are we modelling the US after the API?
  * Undefined Feature User or value statement - Is it clear who needs the feature and what benefit they get from the feature?
  * Dependencies on specific Ids - Is this something intrinsic to the story or an artifact of how the test case is implemented?
  * Other smells - link to external sites
* Use markup conventions to write generic steps
* All Gherking scenarios for a project should use one point of view: preferably, use third person perspective.

## Declarative vs Imprerative
Prefer declarative vs imprerative. Short, declarative scenarios serve as easily readable documentation. Some samples:

### Sample1:

* Imperative

```gherkin
 Given I pass the header information for SSN
    When client request POST "<ServiceURL>" with json data "<RequestFile>"
    Then the response code should be 200
    And the SSNcached result should be same as valid transaction response "<ResponseFile>"
```

* Declarative (better!!!!):

```gherkin
 Given I pass the header information for SSN
    When the client request the URL with data
    Then the response code should be 200
```

## Sample2:
* Imperative:

```gherkin
 Scenario: Successful login
    Given a user "Aslak" with password "xyz"
    And I am on the login page
    And I fill in "User name" with "Aslak"
    And I fill in "Password" with "xyz"
    When I press "Log in"
    Then I should see "Welcome, Aslak"
```

* Declarative (better!!!!):

```gherkin
 Scenario: User is greeted upon login
    Given the user "Aslak" has an account
    When he logs in
    Then he should see "Welcome, Aslak"
```

## Sample3:

* With step definitions Scenario: Complete all incomplete todos

```gherkin
 Given the following todos exist:
      | title | author | complete |
 | Pick up milk | email: person@example.com | false |
 | Pick up eggs | email: person@example.com | false |
 And I have signed in as "person@example.com"
    When I complete the todo "Pick up milk"
    And I complete the todo "Pick up eggs"
    Then I should have no incomplete todos
```

* Without step definitions, but with added clarity Scenario: Complete all incomplete todos (better!!!)

```gherkin
 Given I have signed in 
 And I have 2 incomplete todos
    When I complete all my incomplete todos
    Then I should have no incomplete todos
```