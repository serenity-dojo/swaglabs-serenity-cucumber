package swaglabs.actions.cart;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import swaglabs.actions.catalog.InventoryItemPageComponent;

/**
 * Removes an item from the cart using the Remove button.
 * This can be on the catalog or the cart page.
 */
public class RemoveItemFromCart {
    public static Performable called(String item) {
        return Click.on(InventoryItemPageComponent.removeFromCartButtonFor(item));
    }
}
