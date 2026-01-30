@smoke
Feature: Payments
  Create Payment

  Scenario: Create a simple payment (happy path)
    Given I have a valid payment payload
    When I submit the payment
    Then the API should return a 201 Created response
