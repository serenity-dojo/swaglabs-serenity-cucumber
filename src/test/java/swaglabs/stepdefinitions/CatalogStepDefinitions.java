package swaglabs.stepdefinitions;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.ui.*;
import net.thucydides.core.steps.StepEventBus;
import swaglabs.actions.catalog.CatalogPage;
import swaglabs.actions.catalog.ProductDetailsPage;
import swaglabs.model.Product;
import swaglabs.model.ProductDetails;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CatalogStepDefinitions {


    @When("{actor} has logged on to the application")
    public void logged_on_to_the_application(Actor actor) {
        actor.attemptsTo(
                Open.url("https://www.saucedemo.com"),
                Enter.theValue("standard_user").into(InputField.withPlaceholder("Username")),
                Enter.theValue("secret_sauce").into(InputField.withPlaceholder("Password")),
                Click.on(Button.called("Login"))
        );
    }

    @DataTableType
    public Product product(Map<String, String> productData) {
        return new Product(productData.get("Title"), productData.get("Price"));
    }

    @Then("{actor} should be presented with the following products:")
    public void she_should_be_presented_with_the_following_products(Actor actor, List<Product> expectedProducts) {

        for (Product product : expectedProducts) {
            actor.attemptsTo(
                    Ensure.that(PageElement.called("inventory_item").containingText(product.title())).isDisplayed(),
                    Ensure.that(PageElement.called("inventory_item").containingText(product.title())).textContent().contains(product.price())
            );
        }
    }

    /**
     * Open the inventory page
     */
    @When("{actor} is browsing the product catalog")
    public void browsingTheProductCatalog(Actor actor) {
        actor.attemptsTo(
                Open.url("https://www.saucedemo.com/inventory.html")
        );
    }

    //
    // Here we use a regular expression for more flexibility
    //
    @When("^(.*) (?:opens|has opened) the product details for \"(.*)\"")
    public void viewProductDetails(String actorName, String productName) {
        Actor actor = OnStage.theActorCalled(actorName);
        actor.attemptsTo(
                Click.on(Link.called(productName))
        );
    }


    @Then("{actor} should be presented with {int} products")
    public void shouldBePresentedWith(Actor actor, int productCount) {
        actor.attemptsTo(
                Ensure.thatTheListOf(CatalogPage.INVENTORY_ITEM_NAME).hasSize(productCount)
        );
    }

    @Then("each product should include a name, price, image and description")
    public void eachProductShouldIncludeANamePriceAndDescription() {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(CatalogPage.INVENTORY_ITEMS).allMatch("name, price, image and description should not be empty",
                        item -> !item.name().isEmpty() && !item.description().isEmpty() && !item.price().isEmpty() && !item.image().isEmpty()
                )
        );
    }

    @DataTableType
    public ProductDetails productDetails(Map<String, String> productData) {
        return new ProductDetails(productData.get("Name"), productData.get("Price"), productData.get("Description"));
    }

    @Then("{actor} should see a product with the following details:")
    public void heShouldSeeAProductWithDetails(Actor actor, ProductDetails productDetails) {
        actor.attemptsTo(
                Ensure.that(ProductDetailsPage.NAME).text().isEqualToIgnoringCase(productDetails.name()),
                Ensure.that(ProductDetailsPage.PRICE).hasTextContent(productDetails.price()),
                Ensure.that(ProductDetailsPage.DESCRIPTION).text().contains(productDetails.description())
        );
    }

    @When("{actor} filters the products by {}")
    public void filtersTheProductsBySortOrder(Actor actor, String sortOrder) {
        actor.attemptsTo(
                Select.option(sortOrder).from(CatalogPage.PRODUCT_SORT)
        );
    }

    @Then("the first product displayed should be {}")
    public void theFirstProductDisplayedShouldBeFirstProduct(String expectedFirstItem) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(Text.of(CatalogPage.INVENTORY_ITEM_NAME)).isEqualTo(expectedFirstItem)
        );
    }
}