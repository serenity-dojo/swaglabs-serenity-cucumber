package swaglabs.stepdefinitions;

import com.google.common.base.Splitter;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.PageElement;
import swaglabs.actions.cart.AddToCart;
import swaglabs.actions.cart.RemoveItemFromCart;
import swaglabs.actions.cart.AShoppingCart;
import swaglabs.actions.catalog.CatalogPage;
import swaglabs.actions.checkout.ProvidePersonalDetails;
import swaglabs.actions.navigation.Navigate;
import swaglabs.model.CustomerDetails;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CartStepDefinitions {

    /**
     * Add an item on the catalog page to the cart
     */
    @When("{actor} adds {string} to the cart")
    public void colinAddsToTheCart(Actor actor, String item) {
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
        actor.has(AShoppingCart.containing(items));
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
     * Navigate to the shopping cart via the shopping cart badge
     */
    @When("{actor} opens the shopping cart")
    public void opensCart(Actor actor) {
        actor.attemptsTo(Click.on(PageElement.called("shopping_cart_badge")));
    }

    @When("{actor} continues shopping")
    public void continuesShopping(Actor actor) {
        actor.attemptsTo(Click.on(Button.called("CONTINUE SHOPPING")));
    }

    @Then("{actor} should see the following items:")
    public void shouldSeeTheFollowingItems(Actor actor, List<String> expectedItems) {
        actor.attemptsTo(
                Ensure.that(Text.ofEach(PageElement.called("inventory_item_name")))
                                 .containsElementsFrom(expectedItems));
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

