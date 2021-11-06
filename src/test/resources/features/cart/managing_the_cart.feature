@singlebrowser @resetappstate
Feature: Managing the cart

  Background:
    Given Colin has logged onto the application

  Rule: Customer can add or remove items from the catalog

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

    Example: Colin adds several items to the cart
      Given Colin is browsing the product catalog
      When Colin adds "Sauce Labs Backpack" to the cart
      And Colin adds "Sauce Labs Bike Light" to the cart
      Then the cart item count should be 2

  Rule: Customer can remove items from their cart

    Example: Colin removes an item from the cart
      Given Colin has the following item in his cart:
        | Sauce Labs Backpack   |
        | Sauce Labs Bike Light |
      And Colin is browsing the product catalog
      When he removes "Sauce Labs Backpack" from the cart
      Then the cart item count should be 1

  Rule: Users can add a product to the cart from the product details
    Example: Colin views the details of a product and adds it to his cart
      Given Colin has opened the product details for "Sauce Labs Bike Light"
      When he adds this item to the cart
      Then the cart item count should be 1

  Rule: Users can view the contents of their cart
    Example: Colin adds some items to the cart and reviews his cart contents
      Given Colin has the following item in his cart:
        | Sauce Labs Backpack   |
        | Sauce Labs Bike Light |
      And he is browsing the product catalog
      When he opens the shopping cart
      Then he should see the following items:
        | Sauce Labs Backpack   |
        | Sauce Labs Bike Light |
