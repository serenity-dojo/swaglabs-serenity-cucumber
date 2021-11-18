package swaglabs.actions.cart;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import swaglabs.actions.cartitems.CartItemList;
import swaglabs.actions.checkout.PriceLabel;
import swaglabs.model.CheckoutItem;

import java.util.Collection;

public class CartPage {
    public static Question<Collection<CheckoutItem>> items() {
        return CartItemList.items();
    }
}
