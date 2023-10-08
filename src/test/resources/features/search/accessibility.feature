Feature: Accessibility

  Scenario: Login Page must meet accessible criteria
    Given Sergey navigates to the OrangeHRM website
    Then the Login page should meet accessible criteria

  Scenario: Dashboard Page must meet accessible criteria
    Given Sergey navigates to the OrangeHRM website
    When he enters Username as "Admin" and Password as "admin123"
    And Click on Login
    Then he should see OrangeHRM banner on Dashboard page
    Then the Dashboard page should meet accessible criteria