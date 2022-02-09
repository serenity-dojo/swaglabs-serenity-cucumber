package swaglabs.actions.catalog;

import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.PageElement;

public class ProductDetailsPage {
    public static final Target NAME = PageElement.withCSSClass("inventory_details_name");
    public static final Target PRICE = PageElement.withCSSClass("inventory_details_price");
    public static final Target DESCRIPTION = PageElement.withCSSClass("inventory_details_desc");
}