package swaglabs.actions.checkout;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.ui.Button;
import swaglabs.actions.catalog.CatalogPage;

public class Checkout {
    public static Performable theCurrentItemsInTheCart() {
        return Task.where("{0} initiates a checkout",
                Click.on(CatalogPage.SHOPPING_CART),
                Click.on(Button.withText("Checkout"))
        );
    }
}
