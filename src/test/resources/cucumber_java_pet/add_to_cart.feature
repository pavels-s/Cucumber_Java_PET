Feature: Add to the cart

  Scenario: Add one price from Store to the cart
    Given I am on the Store page
    When I add a "Blue Shoes" to the cart
    Then I should see 1 "Blue Shoes" int the cart

  Scenario Outline: Add one price from Store to the cart
    Given I am on the Store page
    When I add a "<product_name>" to the cart
    Then I should see 1 "<product_name>" int the cart
    Examples:
    | product_name |
    | Anchor Bracelet |
    | Black Over-the-shoulder Handbag |