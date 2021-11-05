package swaglabs.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.ui.PageElement;
import net.thucydides.core.steps.StepEventBus;
import swaglabs.actions.authentication.ApplicationPage;
import swaglabs.actions.authentication.Login;
import swaglabs.actions.errors.ErrorMessages;
import swaglabs.actions.state.Reset;
import swaglabs.model.Customer;
import swaglabs.model.UserCredentials;

import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class LoginStepDefinitions {

    @DataTableType
    public UserCredentials userCredentials(Map<String, String> entry) {
        return new UserCredentials(entry.get("username"), entry.get("password"));
    }

    @Given("{actor} is on the login page")
    public void onTheLoginPage(Actor actor) {
        actor.attemptsTo(Open.url("https://www.saucedemo.com/"));
    }

    @Given("{actor} has logged onto the application")
    public void aRegisteredUser(Actor actor) {
        System.out.println("  -> Running in thread: " + Thread.currentThread());
        if (!ApplicationPage.PRIMARY_HEADER.isVisibleFor(actor)) {
            actor.attemptsTo(
                    Open.url("https://www.saucedemo.com"),
                    Login.as(actor.getName())
            );
        }
    }

    @When("{actor} logs in with valid credentials")
    public void logsInWithValidCredentials(Actor actor) {
        System.out.println("  -> Running in thread: " + Thread.currentThread().getId());
        Customer customer = Customer.valueOf(actor.getName());
        actor.attemptsTo(
                Login.withCredentials(customer.getUsername(), customer.getPassword())
        );
    }

    @Then("{actor} should be taken to the application home page")
    public void shouldBeOnHomePage(Actor actor) {
        actor.attemptsTo(
                Ensure.that(PageElement.locatedBy(".title").containingText("Products")).isDisplayed()
        );
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

    @After("@resetappstate")
    public void clearSession() {
        theActorInTheSpotlight().attemptsTo(
                Reset.applicationState()
        );
    }
}