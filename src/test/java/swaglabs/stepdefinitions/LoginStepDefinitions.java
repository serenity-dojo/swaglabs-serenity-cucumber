package swaglabs.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.InTheBrowser;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.ui.PageElement;
import net.thucydides.core.annotations.WithDriver;
import net.thucydides.core.reports.html.HtmlAggregateStoryReporter;
import net.thucydides.core.steps.StepEventBus;
import net.thucydides.core.util.EnvironmentVariables;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import swaglabs.actions.authentication.ApplicationPage;
import swaglabs.actions.authentication.Login;
import swaglabs.actions.errors.ErrorMessages;
import swaglabs.actions.state.Reset;
import swaglabs.actions.ui.PageHeader;
import swaglabs.model.Customer;
import swaglabs.model.UserCredentials;

import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class LoginStepDefinitions {

    @DataTableType
    public UserCredentials userCredentials(Map<String, String> entry) {
        return new UserCredentials(entry.get("username"), entry.get("password"));
    }

    EnvironmentVariables environmentVariables;
    @Given("{actor} is on the login page")
    public void onTheLoginPage(Actor actor) {
        System.out.println("RUNNING ON THREAD " + Thread.currentThread());
        actor.attemptsTo(Open.url("https://www.saucedemo.com/"));
    }

    /**
     * Logon to the application if the user is not already logged on
     */
    @Given("{actor} has logged onto the application")
    public void aRegisteredUser(Actor actor) {
        System.out.println("RUNNING IN THREAD: " + Thread.currentThread());
        if (!ApplicationPage.PRIMARY_HEADER.isVisibleFor(actor)) {
            actor.attemptsTo(
                    Open.url("https://www.saucedemo.com"),
                    Login.as(actor.getName())
            );
        }
    }

    @When("{actor} logs in with valid credentials")
    public void logsInWithValidCredentials(Actor actor) {
        Customer customer = Customer.valueOf(actor.getName());
        actor.attemptsTo(
                Login.withCredentials(customer.getUsername(), customer.getPassword())
        );
    }

    @Then("there should be no download page source button")
    public void fail() {
        Assertions.assertThat(true).isFalse();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginStepDefinitions.class);

    SoftAssertions softly = new SoftAssertions();

    @Then("{actor} should be presented the product catalog")
    public void shouldBeOnHomePage(Actor actor) {
        actor.attemptsTo(
                Ensure.that(Text.of(".title")).isEqualTo("Products")
        );
        //softly.assertThat(true).isFalse();
    }

    @After
    public void assertAll() {
        softly.assertAll();
    }

    @When("{actor} attempts to login with the following credentials:")
    public void attemptsToLoginWithTheFollowingCredentials(Actor actor, UserCredentials userCredentials) {
        actor.attemptsTo(
                Login.withCredentials(userCredentials.username(), userCredentials.password())
        );
    }

    @Then("{actor} should be presented with the error message {}")
    public void heShouldBePresentedWithTheErrorMessageMessage(Actor actor, String errorMessage) {
        actor.attemptsTo(
                Ensure.that(Text.of(ErrorMessages.CURRENTLY_VISIBLE)).contains(errorMessage)
        );
    }
}
