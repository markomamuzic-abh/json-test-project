
Feature: Smoke test

  Verify that JsonPlaceholder is working properly using the http methods.

  @Smoke
  Scenario: End to end flow for JsonPlaceholder application
    Given user has access to JsonPlaceholder
    When user successfully created album
    And user is able to get album by id
    And user is able to update album
    Then verify the album belongs to the appropriate user
    And delete album