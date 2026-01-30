@smoke
Feature: Orders
  Create Order

  Scenario: Create a basic order (happy path)
    Given I have a valid order payload
    When I create the order
    Then the API should return a 201 Created response
