package swaglabs.actions.catalog;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.ui.PageElement;

import java.util.Collection;

public class ListedItems {
    public static Question<Collection<InventoryItem>> currentlyDisplayed() {
        return new Question<Collection<InventoryItem>>() {
            @Override
            public Collection<InventoryItem> answeredBy(Actor actor) {


                PageElement.withNameOrId("inventory_item");

                return null;
            }
        };
    }
}
