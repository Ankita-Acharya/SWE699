Feature: all users can be fetched
  Scenario: client makes a call to /getAll
    When client calls /getAll
    Then response is given

  Scenario: get user details
    When api call to /getInfo
    Then return user detail