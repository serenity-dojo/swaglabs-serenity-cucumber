<<<<<<< HEAD
package swaglabs.actions.catalog;
=======
package starter.actions.catalog;
>>>>>>> d964fc93779a8cace3a51905fa5f2dcea9287c1a

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.ui.PageElement;

import java.util.Collection;

public class ListedItems {
    public static Question<Collection<InventoryItem>> currentlyDisplayed() {
        return new Question<Collection<InventoryItem>>() {
            @Override
            public Collection<InventoryItem> answeredBy(Actor actor) {


                PageElement.called("inventory_item");

                return null;
            }
        };
    }
}
