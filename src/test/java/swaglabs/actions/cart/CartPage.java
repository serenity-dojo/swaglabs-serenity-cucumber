package swaglabs.actions.cart;

import net.serenitybdd.screenplay.Question;
import swaglabs.actions.cartitems.CartItemList;
import swaglabs.model.CheckoutItem;

import java.util.Collection;

public class CartPage {
    public static Question<Collection<CheckoutItem>> items() {
        return CartItemList.items();
    }
}
