package swaglabs.actions.cart;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.facts.Fact;
import swaglabs.model.InventoryItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class uses Screenplay Facts to manage the customer cart which is stored in the browser local storage.
 * This allows us to add items to the cart for a customer behind the scene, and remove them automatically at the
 * end of the test.
 */
public class AShoppingCart implements Fact {

    private final List<InventoryItem> items;

    public AShoppingCart(List<InventoryItem> inventoryItems) {
        this.items = new ArrayList<>(inventoryItems);
    }

    public static Fact containing(List<String> items) {
        List<InventoryItem> inventoryItems = items.stream()
                .map(InventoryItem::called)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        return new AShoppingCart(inventoryItems);
    }

    public static Fact thatIsEmpty() {
        return new AShoppingCart(new ArrayList<>());
    }

    @Override
    public void setup(Actor actor) {
        BrowseTheWeb.as(actor)
                    .evaluateJavascript("window.localStorage.setItem('cart-contents','[" + commaSeparatedItemIds() + "]')");
    }

    @Override
    public void teardown(Actor actor) {
        BrowseTheWeb.as(actor).evaluateJavascript("window.localStorage.clear()");
    }

    private String commaSeparatedItemIds() {
        return items.stream().map(item -> Integer.toString(item.id)).collect(Collectors.joining(","));
    }
}
