package swaglabs.actions.catalog;

import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.PageElement;

public class InventoryItemPageComponent {

    private static final String INVENTORY_OR_CART_ITEM = "[class$='cart_item'],[class$='inventory_item']";

    public static Target addToCartButtonFor(String item) {
        return Button.withText("Add to cart")
                .inside(PageElement.withNameOrId("inventory_item").containingText(item));
    }

    public static Target addToCartButton() {
        return Button.withText("Add to cart");
    }

    /**
     * Remove button for an item in the cart or the inventory
     */
    public static Target removeFromCartButtonFor(String item) {
        return Button.withText("Remove")
                .inside(PageElement.locatedBy(INVENTORY_OR_CART_ITEM).containingText(item));
    }
}
