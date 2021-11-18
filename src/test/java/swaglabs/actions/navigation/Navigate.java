package swaglabs.actions.navigation;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Open;

public class Navigate {
    public static Performable toTheLoginPage() {
        return Open.url("https://www.saucedemo.com");
    }

    public static Performable toTheShoppingCartPage() {
        return Open.url("https://www.saucedemo.com/cart.html");
    }

    public static Performable toTheCheckoutPage() {
        return Open.url("https://www.saucedemo.com/checkout-step-one.html");
    }

    public static Performable toTheOrderReviewPage() {
        return Open.url("https://www.saucedemo.com/checkout-step-two.html");
    }

    public static Performable toTheCatalogPage() {
        return Open.url("https://www.saucedemo.com/inventory.html");
    }



}
