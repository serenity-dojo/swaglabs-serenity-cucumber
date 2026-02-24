package swaglabs.stepdefinitions;

import net.serenitybdd.annotations.Step;

import java.util.List;

public class CartSteps {

    @Step("Add items {0} to the cart")
    public void addItemsToCart(List<String> itemNames) {
        itemNames.forEach(this::addItemToCart);
    }

    @Step("Add item {0} to the cart" )
    public void addItemToCart(String itemName) {
        // Implementation to add item to cart
    }

    @Step("Remove item {0} from the cart" )
    public void removeItemFromCart(String itemName) {
        // Implementation to remove item from cart
    }
}
