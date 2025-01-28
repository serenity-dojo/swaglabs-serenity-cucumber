Feature: Some sample Gherkin scenarios

  # A user journey scenario

  Scenario: Adding an item to the shopping cart
    Given Sharon is a registered customer
    And she is browsing the product catalog
    When she views the product details for "Bluetooth Headphones"
    And she adds the product to the card
    Then the shopping cart should contain:
      | Item                 | Quanity | Price |
      | Bluetooth Headphones | 1       | $50   |
    And the total price should be shown as:
      | Net total | GST | Total |
      | $50       | $10 | $60   |

  # Business rule scenario
  Rule: Shopping cart should display the correct GST values for each item
    Scenario Outline: Should show the correct GST values based on the product type
      Given the following GST rates:
        | Category | Rate |
        | A        | 5%   |
        | B        | 10%  |
        | C        | 20%  |
      When a product <product> is of tax category <category>
      Then the item line in the shopping cart should be shown as:
        | Product   | Net Price   | GST   | Total Price   |
        | <product> | <net price> | <gst> | <total price> |
      Examples:
        | product              | category | net price | gst | total price |
        | Basic Groceries      | A        | 100       | 5   | 105         |
        | Kids' Story Book     | A        | 40        | 2   | 42          |
        | Laptop               | B        | 1000      | 100 | 1100        |
        | Office Desk Chair    | B        | 200       | 20  | 220         |
        | Bluetooth Headphones | C        | 50        | 10  | 60          |

