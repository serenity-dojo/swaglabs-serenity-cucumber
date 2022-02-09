package swaglabs.stepdefinitions;

import com.google.common.base.Splitter;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.exceptions.TestCompromisedException;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.PageElement;
import swaglabs.actions.cart.AddToCart;
import swaglabs.actions.cart.CartPage;
import swaglabs.actions.cart.RemoveItemFromCart;
import swaglabs.actions.cart.AShoppingCart;
import swaglabs.actions.catalog.CatalogPage;
import swaglabs.actions.checkout.CheckoutPage;
import swaglabs.actions.checkout.ProvidePersonalDetails;
import swaglabs.actions.navigation.Navigate;
import swaglabs.model.CheckoutItem;
import swaglabs.model.CustomerDetails;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.assertj.core.api.Assertions.assertThat;

public class CartStepDefinitions {

    /**
     * Add an item on the catalog page to the cart
     */
    @When("{actor} adds {string} to the cart")
    public void colinAddsToTheCart(Actor actor, String item) {
        if (item.equals("Compromised Product")) {
            throw new TestCompromisedException("Compromised Product exception");
        }
        actor.attemptsTo(AddToCart.item(item));
    }

    @ParameterType(".*")
    public List<String> items(String itemList) {
        return Splitter.on(",").trimResults().splitToList(itemList);
    }

    @When("{actor} adds the following items to the cart: {items}")
    public void addsItemsToTheCart(Actor actor, List<String> items) {
        items.forEach(
                item -> actor.attemptsTo(AddToCart.item(item))
        );
    }

    @When("{actor} adds this item to the cart")
    public void colinAddsTheCurrentItemTheCart(Actor actor) {
        actor.attemptsTo(Click.on(CatalogPage.addToCartButton()));
    }

    @Then("the cart item count should be {int}")
    public void theCartItemCountShouldBe(int itemCount) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(CatalogPage.cartCount()).isEqualTo(itemCount)
        );
    }

    @And("{actor} has the following item(s) in his/her cart:")
    public void addedTheFollowingItemsToTheCart(Actor actor, List<String> items) {
        actor.remember("ITEMS", items);
        actor.has(AShoppingCart.containing(items));
    }

    @And("{actor} has no items in his/her cart")
    public void addedTheFollowingItemsToTheCart(Actor actor) {
        actor.has(AShoppingCart.thatIsEmpty());
    }

    @Then("{actor} should see the item/items he/she selected in the cart")
    public void shouldSeeItemsHeSelected(Actor actor) {
        List<String> expectedItems = actor.recall("ITEMS");
        actor.attemptsTo(
                Ensure.that(Text.ofEach(PageElement.withCSSClass("inventory_item_name")))
                        .containsElementsFrom(expectedItems));
    }


    @When("{actor} removes {string} from the cart")
    public void heRemovesFromTheCart(Actor actor, String item) {
        actor.attemptsTo(RemoveItemFromCart.called(item));
    }

    /**
     * Open the shopping cart page directly
     */
    @Given("{actor} has opened the shopping cart")
    public void opensCartPage(Actor actor) {
        actor.attemptsTo(Navigate.toTheShoppingCartPage());
    }

    /**
     * Open the shopping cart page directly (as a WHEN step)
     */
    @Given("{actor} views his shopping cart")
    public void viewShoppingCart(Actor actor) {
        opensCartPage(actor);
    }

    /**
     * Navigate to the shopping cart via the shopping cart badge
     */
    @When("{actor} opens the shopping cart")
    public void opensCart(Actor actor) {
        actor.attemptsTo(Click.on(PageElement.withCSSClass("shopping_cart_badge")));
    }

    @When("{actor} continues shopping")
    public void continuesShopping(Actor actor) {
        actor.attemptsTo(Click.on(Button.withText("CONTINUE SHOPPING")));
    }


    @Then("{actor} should see the following items:")
    public void shouldSeeTheFollowingItems(Actor actor, List<String> expectedItems) {
        actor.attemptsTo(
                Ensure.that(Text.ofEach(PageElement.withCSSClass("inventory_item_name")))
                                 .containsElementsFrom(expectedItems));
    }

    @Then("the shopping cart should contain:")
    public void shoppingCartShouldContain(List<CheckoutItem> expectedItems) {
        Collection<CheckoutItem> itemsInCart = OnStage.theActorInTheSpotlight().asksFor(CartPage.items());
        assertThat(itemsInCart).containsExactlyElementsOf(expectedItems);
    }

    @Then("the shopping cart should be empty")
    public void shoppingCartShouldBeEmpty() {
        Collection<CheckoutItem> itemsInCart = OnStage.theActorInTheSpotlight().asksFor(CartPage.items());
        assertThat(itemsInCart).isEmpty();
    }

    @DataTableType
    public CustomerDetails customer(Map<String, String> customer) {
        return new CustomerDetails(customer.get("First Name"), customer.get("Last Name"), customer.get("Zip/Post Code"));
    }

    @When("{actor} provides the following personal details:")
    public void heProvidesTheFollowingDetails(Actor actor, CustomerDetails customerDetails) {
        actor.attemptsTo(
                ProvidePersonalDetails.of(customerDetails)
        );
    }
}

