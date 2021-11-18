package swaglabs.actions.checkout;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import swaglabs.actions.cartitems.CartItemList;
import swaglabs.model.CheckoutItem;

import java.util.Collection;

public class CheckoutPage {
    public static Question<Collection<CheckoutItem>> items() {
        return CartItemList.items();
    }

    public static final Question<String> ITEM_TOTAL = Text.of(".summary_subtotal_label").map(PriceLabel::extractPrice);
    public static final Question<String> TAX = Text.of(".summary_tax_label").map(PriceLabel::extractPrice);
    public static final Question<String> TOTAL = Text.of(".summary_total_label").map(PriceLabel::extractPrice);
}
