#@singlebrowser @resetappstate
@cart
Feature: Managing the cart

  Background:
    Given Colin has logged onto the application

  Rule: Customer can add or remove items directly from the catalog

    Scenario Outline: Colin adds items to the cart
      Given Colin is browsing the product catalog
      When Colin adds the following items to the cart: <Items>
      Then the cart item count should be <Item Count>
      Examples:
        | Items                                      | Item Count |
        | Sauce Labs Backpack                        | 1          |
        | Sauce Labs Backpack, Sauce Labs Bike Light | 2          |
    #
    # Alternatively...
    #
    Example: Colin adds an item to the cart
      Given Colin is browsing the product catalog
      When Colin adds "Sauce Labs Backpack" to the cart
      Then the cart item count should be 1

  Rule: Customer can remove items from their cart

    Example: Colin removes an item from the cart overview
      Given Colin has the following item in his cart:
        | Sauce Labs Backpack   |
        | Sauce Labs Bike Light |
      And Colin has opened the shopping cart
      When he removes "Sauce Labs Backpack" from the cart
      Then he should see the following items:
        | Sauce Labs Bike Light |

  Rule: Users can view the contents of their cart
    Example: Colin sees every item he has added in the cart
      Given Colin has the following item in his cart:
        | Sauce Labs Backpack   |
        | Sauce Labs Bike Light |
      And he is browsing the product catalog
      When he opens the shopping cart
      Then he should see the items he selected in the cart
