Feature: Login Scenarios

#Scenario 1
  Scenario: Successful Login with Valid Credentials
    Given Sergey navigates to the OrangeHRM website
    When he enters Username as "Admin" and Password as "admin123"
    And Click on Login
    Then he should see OrangeHRM banner on Dashboard page

#Scenario 2
  Scenario Outline: Using scenario outline for successful Login
    Given Mandy navigates to the OrangeHRM website
    When she enters Username as "<username>" and Password as "<password>"
    And Click on Login
    Then she should see OrangeHRM banner on Dashboard page

    Examples:
      | username | password |
      | Admin    | admin123 |

#Scenario 3
  Scenario: Using Datatable for successful Login
    Given Jenny navigates to the OrangeHRM website
    When user enters credentials using Datatable
      | Admin    | admin123 |
    And Click on Login
    Then she should see OrangeHRM banner on Dashboard page

#Scenario 4
  Scenario Outline: Login with invalid credentials using data table
    Given Mark navigates to the OrangeHRM website
    When user enters credentials using Datatable
      | user  | admin123    |
      | Admin | user123 |
    And Click on Login
    Then user should be presented with the error message "<message>"
    Examples:
      | message             |
      | Invalid credentials |
      | Invalid credentials |
