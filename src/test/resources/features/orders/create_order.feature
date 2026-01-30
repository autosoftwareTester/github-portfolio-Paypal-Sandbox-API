@smoke
Feature: Orders
  Create Order

  Scenario Outline: Create a basic order (happy path)
    Given I create the order with intent "<intent>"
    Then I validate the order response

    Examples:
    | intent|
    |AUTHORIZE|
