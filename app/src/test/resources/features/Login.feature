Feature: User Login
  Scenario: Successful login
    Given the API base URL is "http://localhost:8080/api/v1/"
    When the user sends a login request with username "johndoe" and password "hash_johndoe"
    Then the login response code should be 200
    And the response body should match:
      | id          | 1                                 |
      | username    | johndoe                           |
      | email       | johndoe@example.com               |
      | fullName    | John Doe                          |
      | bio         | Love to explore the world         |
      | profilePic  | https://example.com/profiles/johndoe.jpg |
      | birthday    | 1990-05-10                        |
      | followers   | 0                                 |
