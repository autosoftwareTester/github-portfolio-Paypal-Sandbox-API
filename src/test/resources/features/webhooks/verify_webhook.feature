@smoke
Feature: Webhooks
  Verify webhook signature

  Scenario: Valid webhook signature is accepted
    Given I have a sample webhook event
    When I POST the webhook to the verification endpoint
    Then the API should return 200 OK
