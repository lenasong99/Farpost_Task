# new feature
# Tags: optional

Feature: Login and register tests
    Background:
    Given : The user is on the home page

  Scenario: Negative Login
    Given : The user clicks on login and registration link
    When : The user inputs invalid login "abcd1234" ang password "12345678"
    And : The user should see red messages "Данные для входа неверны" under the login and "Данные для входа неверны" under the password
    When : The user enters valid login and password
    Then The user sees profile page