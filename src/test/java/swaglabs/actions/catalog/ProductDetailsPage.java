<<<<<<< HEAD
package swaglabs.actions.catalog;
=======
package starter.actions.catalog;
>>>>>>> d964fc93779a8cace3a51905fa5f2dcea9287c1a

import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.PageElement;

public class ProductDetailsPage {
    public static final Target NAME = PageElement.called("inventory_details_name");
    public static final Target PRICE = PageElement.called("inventory_details_price");
    public static final Target DESCRIPTION = PageElement.called("inventory_details_desc");
}