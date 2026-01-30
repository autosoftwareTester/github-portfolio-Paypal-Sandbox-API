@smoke
Feature: Payouts
  Create Payout

  Scenario: Create a simple payout (happy path)
    Given I have a valid payout payload
    When I create the payout
    Then the API should return a 201 Created response
