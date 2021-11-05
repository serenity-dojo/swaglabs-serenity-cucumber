package swaglabs.actions.cart;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import swaglabs.actions.catalog.CatalogPage;

public class RemoveItemFromCart {
    public static Performable called(String item) {
        return Click.on(CatalogPage.removeFromCartButtonFor(item));
    }
}
