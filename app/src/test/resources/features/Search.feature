Feature: Search Users by Name
  Scenario: Search returns results
    Given the API base URL is "http://localhost:8080/api/v1/"
    When the user searches for users with name "John"
    Then the search response code should be 200
    And the response body should contain:
      | id | name             | profilePic                                       |
      | 1  | John Doe         | https://example.com/profiles/johndoe.jpg         |
      | 5  | Michael Johnson  | https://example.com/profiles/michaeljohnson.jpg |
