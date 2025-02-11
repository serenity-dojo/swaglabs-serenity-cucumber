package swaglabs.stepdefinitions;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.DoubleClick;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.PageElement;
import net.serenitybdd.screenplay.ui.Select;
import net.serenitybdd.screenplay.waits.Wait;
import net.thucydides.core.webdriver.shadow.ByShadow;
import org.assertj.core.api.Assertions;
import swaglabs.actions.authentication.Login;
import swaglabs.actions.catalog.CatalogPage;
import swaglabs.actions.catalog.ProductDetailsPage;
import swaglabs.actions.catalog.ViewInventoryItem;
import swaglabs.actions.navigation.Navigate;
import swaglabs.model.Product;
import swaglabs.model.ProductDetails;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CatalogStepDefinitions {


    @When("{actor} has logged on to the application")
    public void logged_on_to_the_application(Actor actor) {
        actor.attemptsTo(
                Navigate.toTheLoginPage(),
                Login.withCredentials("standard_user", "secret_sauce")
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
                    Ensure.that(PageElement.locatedBy(".inventory_item").containingText(product.title())).textContent().contains(product.price())
            );
        }
    }

    /**
     * Open the inventory page
     */
    @When("{actor} is browsing the product catalog")
    public void browsingTheProductCatalog(Actor actor) {
        actor.attemptsTo(Navigate.toTheCatalogPage());
    }

    @When("{actor} opens the browser extension")
    public void openBrowserExtensionOptions(Actor actor) throws InterruptedException {
        actor.attemptsTo(
                Open.url("chrome-extension://mgijmajocgfcbeboacabfgobmjgjcoja/browser_action.html"),
                Enter.theValue("cat").into("#query-field"),
                Click.on("#define-btn")
        );
        Thread.sleep(1000);
    }

    @When("{actor} displays the definition of a term")
    public void displaysTheDefinitionOfATerm(Actor actor) throws InterruptedException {
        actor.attemptsTo(
                DoubleClick.on(".login_logo")
        );
        Thread.sleep(1000);
    }


    @Then("the head word should be displayed")
    public void the_head_word_should_be_displayed() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
                Ensure.that(Target.the("headword").locatedBy(".headword")).isDisplayed()
        );
        String headwordValue = Text.of(Target.the("headword").locatedBy(".headword")).answeredBy(actor);
        Assertions.assertThat(headwordValue).isEqualTo("cat");
    }

    @Then("the dictionary definition should be displayed")
    public void the_definition_should_be_displayed() {
        String dictionaryDefinition
                = OnStage.theActorInTheSpotlight().asksFor(Text.of(ByShadow.cssSelector("#gdx-bubble-host")));

        Assertions.assertThat(dictionaryDefinition).contains("A laboratory");
    }

    //
    // Here we use a regular expression for more flexibility
    //
    @When("^(.*) (?:opens|has opened) the product details for \"(.*)\"")
    public void viewProductDetails(String actorName, String productName) {
        Actor actor = OnStage.theActorCalled(actorName);
        actor.attemptsTo(ViewInventoryItem.called(productName));
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