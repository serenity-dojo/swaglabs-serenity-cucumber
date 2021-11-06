package swaglabs.actions.cart;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.facts.Fact;
import swaglabs.model.InventoryItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public void setup(Actor actor) {
        String renderedIds = items.stream().map(item -> Integer.toString(item.id)).collect(Collectors.joining(","));
        BrowseTheWeb.as(actor).evaluateJavascript("window.localStorage.setItem('cart-contents','[" + renderedIds + "]')");
    }

    @Override
    public void teardown(Actor actor) {
        BrowseTheWeb.as(actor).evaluateJavascript("window.localStorage.clear()");
    }
}
