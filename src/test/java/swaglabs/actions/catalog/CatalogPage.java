package swaglabs.actions.catalog;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.Dropdown;
import net.serenitybdd.screenplay.ui.Link;
import net.serenitybdd.screenplay.ui.PageElement;

import java.util.Collection;

public class CatalogPage {
    public static final Question<Collection<InventoryItem>> INVENTORY_ITEMS = PageElement.withNameOrId("inventory_item")
            .mapAll(element
                    -> new InventoryItem(
                    element.findBy(".inventory_item_name").getText(),
                    element.findBy(".inventory_item_desc").getText(),
                    element.findBy(".inventory_item_price").getText(),
                    element.findBy(".inventory_item_img img").getAttribute("src")
            ));

    public static final Target INVENTORY_ITEM_NAME = PageElement.withCSSClass("inventory_item_name");

    public static final Target PRODUCT_SORT = Dropdown.withNameOrId("product_sort_container");

    private static final String INVENTORY_OR_CART_ITEM = ".cart_item,.inventory_item";

    public static Target addToCartButtonFor(String item) {
        return Button.withText("Add to cart").called("'the 'Add to cart' button for '" + item + "'")
                .inside(PageElement.withCSSClass("inventory_item").containingText(item));
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

    public static Question<Integer> cartCount() {
        return Text.of(PageElement.withCSSClass("shopping_cart_badge")).asInteger();
    }

    public static final Target SHOPPING_CART = Link.locatedBy(".shopping_cart_link");
}