@singlebrowser @resetappstate
Feature: Calculating sales tax

  Background:
    Given Colin has logged onto the application
    And Colin is browsing the product catalog

  #
  # NOTE: In a real application this would typically done via API or backend testing, not via the UI
  #
  Rule: Sales tax is calculated as 8% of the purchase price
    Scenario Outline: Colin purchases two items and sees them both appear in the purchase summary
      Given Colin has added the following item to the cart:
        | <Item> |
      When he checks out his cart providing his personal details
      Then the total price should be:
        | Item total | Tax   | Total   |
        | <Price>    | <Tax> | <Total> |
      Examples:
        | Item                  | Price  | Tax   | Total  |
        | Sauce Labs Backpack   | $29.99 | $2.40 | $32.39 |
        | Sauce Labs Bike Light | $9.99  | $0.80 | $10.79 |

  Rule: Sales tax is calculated on the total of all purchased items
    Scenario: Colin purchases two items so the total tax calculated includes tax for both items
      When Colin checks out the following items:
        | Qty | Description           | Price  |
        | 1   | Sauce Labs Backpack   | $29.99 |
        | 1   | Sauce Labs Bike Light | $9.99  |
      Then the total price should be:
        | Item total | Tax   | Total  |
        | $39.98     | $3.20 | $43.18 |

