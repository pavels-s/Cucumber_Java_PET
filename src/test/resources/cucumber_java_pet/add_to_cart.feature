Feature: Add to the cart

  Scenario: Add one price from Store to the cart
    Given I am on the Store page
    When I add a "Blue shoes" to the cart
    Then I should see 1 "Blue Shoes" int the cart