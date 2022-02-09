package swaglabs.actions.catalog;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.ui.Link;

public class ViewInventoryItem {
    public static Performable called(String productName) {
        return Click.on(Link.withText(productName));
    }
}
