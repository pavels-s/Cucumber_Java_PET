Feature:

  Scenario: using default payment option
    Given I am a guest customer
    And I have a product in the cart
    And I am on the checkout page
    When I provide billing details
    | firstname | lastname | country            | address_line1     | city  | state | zip   | email             |
    | some      | user     | United States (US) | 6300 Spring Creek | Plano | Texas | 75024 | example@gmail.com |
    And I place an order
    Then order should be placed successfully