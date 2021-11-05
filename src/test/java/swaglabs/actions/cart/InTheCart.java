package swaglabs.actions.cart;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import swaglabs.actions.catalog.CatalogPage;

public class InTheCart {
    public static Performable addItemCalled(String item) {
        return Click.on(CatalogPage.addToCartButtonFor(item));
    }

    public static Performable removeItemCalled(String item) {
        return Click.on(CatalogPage.removeFromCartButtonFor(item));
    }
}
