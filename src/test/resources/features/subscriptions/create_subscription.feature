@regression
Feature: Subscriptions
  Create Subscription

  Scenario: Create a basic subscription (happy path)
    Given I have a valid subscription payload
    When I create the subscription
    Then the API should return a 201 Created response
