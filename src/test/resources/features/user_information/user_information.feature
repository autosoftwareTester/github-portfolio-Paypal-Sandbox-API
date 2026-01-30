@userInfo @api
Feature: User Information
  As an API client
  I want to obtain user information and access tokens

  @profile
  Scenario: Get user profile using access token
    Given I have a valid access token
    When I request the user profile
    Then the response status should be 200
    And the response should contain "user_id"


